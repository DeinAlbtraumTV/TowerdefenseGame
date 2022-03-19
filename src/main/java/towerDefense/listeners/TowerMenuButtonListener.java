package towerDefense.listeners;

import towerDefense.TowerDefenseGame;
import towerDefense.dialogs.components.ButtonComponent;
import towerDefense.dialogs.events.ListenerAdapter;
import towerDefense.dialogs.events.mouse.ClickEvent;
import towerDefense.dialogs.groups.ButtonGroup;

import java.awt.*;

public class TowerMenuButtonListener extends ListenerAdapter {

    @Override
    public void onClickEvent(ClickEvent event) {
        super.onClickEvent(event);

        ButtonComponent component = (ButtonComponent) event.getComponent();

        TowerDefenseGame.componentContainer.getAddedComponents().forEach(comp -> {
            if (comp instanceof ButtonComponent) {
                ButtonComponent buttonComp = (ButtonComponent) comp;

                if (buttonComp.getContent().equals("Upgrades")) {
                    buttonComp.setBackgroundColor(Color.RED);
                }
            }
        });

        component.setBackgroundColor(Color.GREEN);

        ButtonGroup.getButtonGroup("towerButtons").getButtons().forEach(button -> button.setVisibility(true));
        ButtonGroup.getButtonGroup("upgradeButtons").getButtons().forEach(button -> button.setVisibility(false));
    }
}
