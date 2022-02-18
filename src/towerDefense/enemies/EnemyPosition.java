package towerDefense.enemies;

public class EnemyPosition {

    private double x;
    private double y;

    public EnemyPosition() {
        x = 0;
        y = 0;
    }

    public EnemyPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int) x;
    }

    public double getXDouble() {
        return x;
    }

    public int getY() {
        return (int) y;
    }

    public double getYDouble() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addX(double x) {
        this.x += x;
    }

    public void addY(double y) {
        this.y += y;
    }
}
