package towerDefense.dialogs.groups;

import towerDefense.dialogs.components.ButtonComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class ButtonGroup {

    private static final HashMap<String, ButtonGroup> buttonGroups = new HashMap<>();

    public static ButtonGroup getButtonGroup(String name) {
        if (buttonGroups.containsKey(name)) {
            return buttonGroups.get(name);
        }

        ButtonGroup group = new ButtonGroup(name);
        buttonGroups.put(name, group);
        return group;
    }

    private final String name;
    private final ArrayList<ButtonComponent> buttons;

    private ButtonGroup(String name) {
        this.name = name;
        this.buttons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addButtons(ButtonComponent... buttons) {
        Collections.addAll(this.buttons, buttons);
    }

    public void removeButtons(ButtonComponent... buttons) {
        this.buttons.removeAll(Arrays.asList(buttons));
    }

    public ArrayList<ButtonComponent> getButtons() {
        return buttons;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ButtonGroup) {
            ButtonGroup group = (ButtonGroup) obj;

            return group.name.equals(name);
        }
        return false;
    }
}
