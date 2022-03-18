package towerDefense.towers;

import java.awt.*;

public class BasicTower extends Tower {
    public BasicTower(int x, int y) {
        super(x, y, 1, 1D, 40, 1, getTowerColor(), 10);
    }

    public static Color getTowerColor() {
        return new Color(50, 168, 82);
    }
}
