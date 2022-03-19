package towerDefense.enemies;

import static towerDefense.TowerDefenseGame.gameController;

public class RunnerEnemy extends Enemy {
    public RunnerEnemy() {
        super(5D, 2000F / gameController.getFps(), "/enemy/Runner", 6);
    }

    public RunnerEnemy(int hpMod, int x, int y) {
        super(5D * (hpMod + 1), 2000F / gameController.getFps(), x, y, "/enemy/Runner", 6);
    }
}
