package towerDefense.enemies;

import java.awt.*;

import static towerDefense.TowerDefenseGame.gameController;

public class TankEnemy extends Enemy {
    public TankEnemy() {
        super(15D, 750F / gameController.getFps(), Color.DARK_GRAY);
    }
}
