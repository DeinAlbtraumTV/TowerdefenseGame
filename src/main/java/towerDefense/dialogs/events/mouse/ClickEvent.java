package towerDefense.dialogs.events.mouse;

import towerDefense.dialogs.components.GenericComponent;

import java.awt.*;

public class ClickEvent extends MouseEvent {

    private final Point pos;
    private final int mouseButton;

    public ClickEvent(Point pos, int mouseButton, java.awt.event.MouseEvent event, GenericComponent component) {
        super(event, component);
        this.pos = pos;
        this.mouseButton = mouseButton;
    }

    public ClickEvent(int pos_x, int pos_y, int mouseButton, java.awt.event.MouseEvent event, GenericComponent component) {
        super(event, component);
        this.pos = new Point(pos_x, pos_y);
        this.mouseButton = mouseButton;
    }

    public int getMouseButton() {
        return mouseButton;
    }

    public Point getPos() {
        return pos;
    }
}
