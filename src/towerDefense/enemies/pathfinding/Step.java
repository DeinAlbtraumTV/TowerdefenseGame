package towerDefense.enemies.pathfinding;

import java.util.Objects;

public class Step {

    private final int move_x;
    private final int move_y;

    public Step (int move_x, int move_y) {
        this.move_x = move_x;
        this.move_y = move_y;
    }

    public int getMove_x() {
        return move_x;
    }

    public int getMove_y() {
        return move_y;
    }

    @Override
    public String toString() {
        return "Step{" +
                "move_x=" + move_x +
                ", move_y=" + move_y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return move_x == step.move_x && move_y == step.move_y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(move_x, move_y);
    }
}
