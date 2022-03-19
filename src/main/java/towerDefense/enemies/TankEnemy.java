package towerDefense.enemies;

import static towerDefense.TowerDefenseGame.gameController;

public class TankEnemy extends Enemy {
    public TankEnemy() {
        super(15D, 750F / gameController.getFps(), "/enemy/Tank", 4);
    }

    public TankEnemy(int hpMod, int x, int y) {
        super(15D * (hpMod + 1), 750F / gameController.getFps(), x, y, "/enemy/Tank", 4);
    }
}
