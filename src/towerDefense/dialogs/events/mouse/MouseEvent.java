package towerDefense.dialogs.events.mouse;

import towerDefense.dialogs.components.GenericComponent;
import towerDefense.dialogs.events.GenericEvent;

public class MouseEvent extends GenericEvent {

    private final java.awt.event.MouseEvent eventRef;
    private final GenericComponent component;

    public MouseEvent(java.awt.event.MouseEvent event, GenericComponent component) {
        this.eventRef = event;
        this.component = component;
    }

    public java.awt.event.MouseEvent getEventRef() {
        return eventRef;
    }

    public GenericComponent getComponent() {
        return component;
    }
}
