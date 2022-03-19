package towerDefense.dialogs.components;

import java.awt.*;

public class ComponentBorder {

    private int width;
    private Color color;

    public ComponentBorder() {
        this(2);
    }

    public ComponentBorder(int width) {
        this(width, Color.BLACK);
    }

    public ComponentBorder(int width, Color color) {
        this.width = width;
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
