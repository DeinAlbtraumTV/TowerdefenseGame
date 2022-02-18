package towerDefense.enemies.pathfinding;

import java.util.ArrayList;

public class Path {

    private final int from_x;
    private final int from_y;
    private final int to_x;
    private final int to_y;

    private final ArrayList<Step> steps;
    private ArrayList<PathNode> pathNodes;

    public Path(int from_x, int from_y, int to_x, int to_y) {
        this.from_x = from_x;
        this.from_y = from_y;
        this.to_x = to_x;
        this.to_y = to_y;

        steps = new ArrayList<>();
        pathNodes = new ArrayList<>();
    }

    public int getLength() {
        return steps.size();
    }

    public Step getStep(int index) {
        if (index < steps.size()) {
            return steps.get(index);
        }

        return null;
    }

    public void appendStep(int x, int y) {
        steps.add(new Step(x, y));
    }

    public void setPathNodes(ArrayList<PathNode> pathNodes) {
        this.pathNodes = pathNodes;
    }

    public boolean contains(int x, int y) {
        return steps.contains(new Step(x, y));
    }

    public int getFrom_x() {
        return from_x;
    }

    public int getFrom_y() {
        return from_y;
    }

    public int getTo_x() {
        return to_x;
    }

    public int getTo_y() {
        return to_y;
    }

    public ArrayList<PathNode> getPathNodes() {
        return pathNodes;
    }

    @Override
    public String toString() {
        return "Path{" +
                "from_x=" + from_x +
                ", from_y=" + from_y +
                ", to_x=" + to_x +
                ", to_y=" + to_y +
                ", steps=" + steps +
                ", pathNodes=" + pathNodes +
                '}';
    }
}
