package towerDefense.enemies.pathfinding;

public class PathNodeWithPriority implements Comparable<PathNodeWithPriority> {

    private final PathNode pathNode;

    private final int priority;

    public PathNodeWithPriority(PathNode pathNode, int priority) {
        this.pathNode = pathNode;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public PathNode getPathNode() {
        return pathNode;
    }

    @Override
    public int compareTo(PathNodeWithPriority other) {
        return Integer.compare(other.priority, this.priority);
    }
}
