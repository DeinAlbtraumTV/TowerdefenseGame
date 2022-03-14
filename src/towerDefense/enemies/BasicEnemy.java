package towerDefense.enemies;

import java.awt.*;

import static towerDefense.TowerDefenseGame.gameController;

public class BasicEnemy extends Enemy {
    public BasicEnemy() {
        super(Color.CYAN, 2);
    }

    public BasicEnemy(int hpMod, int x, int y) {
        super(5 * (hpMod + 1), 1000F / gameController.getFps(), x, y, Color.CYAN, 2);
    }
}
