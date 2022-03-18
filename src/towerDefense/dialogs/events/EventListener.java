package towerDefense.dialogs.events;

public interface EventListener {
    default void onEvent(GenericEvent event) {}
}
