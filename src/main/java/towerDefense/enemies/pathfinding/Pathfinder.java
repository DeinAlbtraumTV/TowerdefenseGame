package towerDefense.enemies.pathfinding;

import towerDefense.map.TileMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Pathfinder {

    private final PriorityQueue<PathNodeWithPriority> frontier = new PriorityQueue<>();

    private final TileMap tileMap;

    private PathNode[][] pathNodes;

    private final Heuristic heuristic;

    public Pathfinder(TileMap tileMap, Heuristic heuristic) {
        this.heuristic = heuristic;
        this.tileMap = tileMap;

        if (tileMap.getHeight() <= 0 || tileMap.getWidth() <= 0) {
            //Can´t search on empty map
            return;
        }

        pathNodes = new PathNode[tileMap.getWidth()][tileMap.getHeight()];
        for (int x = 0; x < tileMap.getWidth(); x++) {
            for (int y = 0; y < tileMap.getHeight(); y++) {
                pathNodes[x][y] = new PathNode(x, y);
            }
        }
    }

    public synchronized Path findPath(int start_x, int start_y, int to_x, int to_y) {
        frontier.clear();

        if (start_x >= tileMap.getWidth() || start_y >= tileMap.getHeight())
            return null;

        frontier.add(new PathNodeWithPriority(pathNodes[start_x][start_y], 0));

        HashMap<PathNode, PathNode> cameFrom = new HashMap<>();
        HashMap<PathNode, Integer> costSoFar = new HashMap<>();

        cameFrom.put(pathNodes[start_x][start_y], null);
        costSoFar.put(pathNodes[start_x][start_y], 0);

        int newCost;

        while (!frontier.isEmpty()) {
            PathNode current = frontier.poll().getPathNode();

            if (current.getX() == to_x && current.getY() == to_y) {
                break;
            }

            ArrayList<PathNode> neighbors = new ArrayList<>();

            if (current.getX() - 1 >= 0)
                neighbors.add(new PathNode(current.getX() - 1, current.getY()));

            if (current.getX() + 1 < tileMap.getWidth())
                neighbors.add(new PathNode(current.getX() + 1, current.getY()));

            if (current.getY() - 1 >= 0)
                neighbors.add(new PathNode(current.getX(), current.getY() - 1));

            if (current.getY() + 1 < tileMap.getHeight())
                neighbors.add(new PathNode(current.getX(), current.getY() + 1));

            for ( PathNode node : neighbors ) {
                if (tileMap.isTileBlocked(node.getX(), node.getY()))
                    continue;

                newCost = costSoFar.get(current) + 1;

                if (!costSoFar.containsKey(node) || newCost < costSoFar.get(node)) {
                    costSoFar.put(node, newCost);

                    //9999999 * heuristic for fixing the path going back and forth on small maps
                    //TODO find cause and fix
                    int priority = newCost + (9999999 * heuristic.getCost(node.getX(), node.getY(), to_x, to_y));

                    frontier.add(new PathNodeWithPriority(node, priority));

                    cameFrom.put(node, current);
                }
            }
        }

        ArrayList<PathNode> nodeList = new ArrayList<>();

        PathNode current = new PathNode(to_x, to_y);

        nodeList.add(current);

        while(!current.equals(new PathNode(start_x, start_y))) {
            current = cameFrom.get(current);

            if (current == null) {
                //No Path found
                return null;
            }

            nodeList.add(current);
        }

        nodeList.remove(new PathNode(start_x, start_y));

        Path out = new Path(start_x, start_y, to_x, to_y);

        PathNode prev = new PathNode(start_x, start_y);

        Collections.reverse(nodeList);

        for (PathNode pathNode : nodeList) {
            out.appendStep(pathNode.getX() - prev.getX(), pathNode.getY() - prev.getY());

            prev = pathNode;
        }

        out.setPathNodes(nodeList);

        return out;
    }
}
