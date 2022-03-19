package towerDefense.enemies.pathfinding;

public class PythagorasHeuristic implements Heuristic{
    public int getCost(int x, int y, int tx, int ty) {
        float dx = tx - x;
        float dy = ty - y;

        return (int) (Math.sqrt((dx*dx)+(dy*dy)));
    }
}
