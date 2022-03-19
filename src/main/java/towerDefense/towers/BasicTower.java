package towerDefense.towers;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BasicTower extends Tower {
    public BasicTower(int x, int y) {
        super(x, y, 1, 1D, 40, 1, getDefaultImagePath(), 10);
    }

    public static String getDefaultImagePath() {
        return "/tower/Basic";
    }

    public static Image getImage() {
        URL url = BasicTower.class.getResource(getDefaultImagePath() + ".png");
        return new ImageIcon(url).getImage();
    }
}
