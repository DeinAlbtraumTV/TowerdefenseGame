package towerDefense.dialogs.events.mouse;

import towerDefense.dialogs.components.GenericComponent;

public class ReleasedEvent extends MouseEvent {

    public ReleasedEvent(java.awt.event.MouseEvent event, GenericComponent component) {
        super(event, component);
    }
}
