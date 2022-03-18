package towerDefense.dialogs.components;

import towerDefense.dialogs.events.EventListener;
import towerDefense.dialogs.events.GenericEvent;
import towerDefense.util.Checks;

import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class GenericComponent implements Component {
    private Point pos;
    private Color backgroundColor;
    private Image image;
    private ComponentBorder border;
    private boolean visible;

    private final CopyOnWriteArrayList<EventListener> listeners = new CopyOnWriteArrayList<>();

    public final int WIDTH;
    public final int HEIGHT;

    public GenericComponent(int pos_x, int pos_y, int width, int height) {
        WIDTH = width;
        HEIGHT = height;

        backgroundColor = Color.GRAY;

        pos = new Point(pos_x, pos_y);
        border = null;
        visible = true;
    }

    @Override
    public Image render(Image image) {

        if (visible) {
            if (border != null) {
                Graphics2D g = (Graphics2D) image.getGraphics();

                g.setColor(border.getColor());
                g.setStroke(new BasicStroke(border.getWidth()));
                g.drawRect(0, 0, WIDTH, HEIGHT);
            }
        }

        this.image = image;

        return image;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void onEvent(GenericEvent event) {
        listeners.forEach(listener -> listener.onEvent(event));
    }

    @Override
    public void addEventListeners(Object... listeners) {
        Checks.noneNull(listeners, "listeners");

        Arrays.stream(listeners).forEach(this::registerEventListener);
    }

    private void registerEventListener(Object listener) {
        if (!(listener instanceof EventListener)) {
            throw new IllegalArgumentException("Listener Object does not implement EventListener!");
        }

        listeners.add((EventListener) listener);
    }

    @Override
    public final void removeEventListeners(Object... listeners) {
        Checks.noneNull(listeners, "listeners");

        Arrays.stream(listeners).forEach(this::unregisterEventListener);
    }

    @Override
    public void setBorder(ComponentBorder border) {
        this.border = border;
    }

    @Override
    public ComponentBorder getBorder() {
        return border;
    }

    @Override
    public void setVisibility(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    private void unregisterEventListener(Object listener) {
        if (!(listener instanceof EventListener)) {
            throw new IllegalArgumentException("Tried to unregister listener that does not implement EventListener!");
        }

        listeners.remove((EventListener) listener);
    }

    @Override
    public int getPos_x() {
        return pos.x;
    }

    @Override
    public void setPos_x(int new_pos_x) {
        pos = new Point(new_pos_x, pos.y);
    }

    @Override
    public int getPos_y() {
        return pos.y;
    }

    @Override
    public void setPos_y(int new_pos_y) {
        pos = new Point(pos.x, new_pos_y);
    }

    @Override
    public void setPos(Point new_pos) {
        pos = new_pos;
    }

    @Override
    public Point getPos() {
        return pos;
    }

    @Override
    public void setBackgroundColor(Color new_backgroundColor) {
        this.backgroundColor = new_backgroundColor;
    }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }
}
