package towerDefense.enemies.pathfinding;

public class ManhattanHeuristic implements Heuristic {
    @Override
    public int getCost(int x, int y, int tx, int ty) {
        return Math.abs(x - tx) + Math.abs(y - ty);
    }
}
