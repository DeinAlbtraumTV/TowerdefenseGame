package towerDefense.enemies;

import java.awt.*;

import static towerDefense.TowerDefenseGame.gameController;

public class RunnerEnemy extends Enemy {
    public RunnerEnemy() {
        super(5D, 2000F / gameController.getFps(), Color.ORANGE);
    }
}
