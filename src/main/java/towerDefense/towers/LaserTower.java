package towerDefense.towers;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LaserTower extends Tower {
    public LaserTower(int x, int y) {
        super(x, y, 1, 0.1D, 2, 1, getDefaultImagePath(), 20);
    }

    public static String getDefaultImagePath() {
        return "/tower/Laser";
    }

    public static Image getImage() {
        URL url = LaserTower.class.getResource(getDefaultImagePath() + ".png");
        return new ImageIcon(url).getImage();
    }
}
