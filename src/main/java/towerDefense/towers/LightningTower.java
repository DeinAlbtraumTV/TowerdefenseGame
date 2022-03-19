package towerDefense.towers;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LightningTower extends Tower {
    public LightningTower(int x, int y) {
        super(x, y, 2, 2, 80, 3, getDefaultImagePath(), 30);
    }

    public static String getDefaultImagePath() {
        return "/tower/Lightning";
    }

    public static Image getImage() {
        URL url = LightningTower.class.getResource(getDefaultImagePath() + ".png");
        return new ImageIcon(url).getImage();
    }
}

