package towerDefense.towers;

import java.awt.*;

public class LightingTower extends Tower {
    public LightingTower(int x, int y) {
        super(x, y, 2, 2, 80, 3, getTowerColor(), 30);
    }

    public static Color getTowerColor() {
        return new Color(0x1CE1CA);
    }
}
