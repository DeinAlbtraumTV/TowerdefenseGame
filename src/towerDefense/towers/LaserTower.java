package towerDefense.towers;

import java.awt.*;

public class LaserTower extends Tower {
    public LaserTower(int x, int y) {
        super(x, y, 1, 0.1D, 2, 1, getTowerColor(), 20);
    }

    public static Color getTowerColor() {
        return new Color(0xE11C4F);
    }
}
