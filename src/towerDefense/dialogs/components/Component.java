package towerDefense.dialogs.components;


import towerDefense.dialogs.events.GenericEvent;

import java.awt.*;

public interface Component {

    Image render(Image image);

    Image getImage();

    int getPos_x();
    void setPos_x(int new_pos_x);

    int getPos_y();
    void setPos_y(int new_pos_y);

    void setPos(Point new_pos);
    Point getPos();

    void setBackgroundColor(Color new_backgroundColor);
    Color getBackgroundColor();

    void addEventListeners(Object... listeners);
    void removeEventListeners(Object... listeners);

    void setBorder(ComponentBorder border);
    ComponentBorder getBorder();

    void setVisibility(boolean visible);
    boolean isVisible();

    default void onEvent(GenericEvent event) {}
}
