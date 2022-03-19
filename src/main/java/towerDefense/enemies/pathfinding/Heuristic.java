package towerDefense.enemies.pathfinding;

public interface Heuristic {
    int getCost(int x, int y, int tx, int ty);
}
