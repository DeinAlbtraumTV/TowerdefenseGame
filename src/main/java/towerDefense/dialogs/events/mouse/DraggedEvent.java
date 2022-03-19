package towerDefense.dialogs.events.mouse;

import towerDefense.dialogs.components.GenericComponent;

import java.awt.*;

public class DraggedEvent extends MouseEvent {

    private final Point pos;

    public DraggedEvent(int pos_x, int pos_y, java.awt.event.MouseEvent event, GenericComponent component) {
        super(event, component);
        this.pos = new Point(pos_x, pos_y);
    }

    public DraggedEvent(Point pos, java.awt.event.MouseEvent event, GenericComponent component) {
        super(event, component);
        this.pos = pos;
    }

    public Point getPos() {
        return pos;
    }
}
