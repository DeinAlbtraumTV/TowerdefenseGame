package towerDefense.enemies.pathfinding;

import java.util.Objects;

public class PathNode {
    private final int x;
    private final int y;

    public PathNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "PathNode{" + "x=" + x + ", y=" + y + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathNode pathNode = (PathNode) o;
        return x == pathNode.x && y == pathNode.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
