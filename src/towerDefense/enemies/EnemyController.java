package towerDefense.enemies;

import towerDefense.map.TileMap;
import towerDefense.TowerDefenseGame;

import java.util.ArrayList;

import static towerDefense.TowerDefenseGame.gameController;

public class EnemyController {
    private int wave = 0;
    private final int difficulty;

    private final ArrayList<Enemy> enemies;

    private long framesSinceLastWave = 0;

    public EnemyController(int difficulty) {
        if (difficulty < 1 || difficulty > 5)
            difficulty = 1;
        this.difficulty = difficulty;

        this.enemies = new ArrayList<>();
    }

    public void spawnNextWave() {
        wave++;
        System.out.println("Spawning wave: " + wave);
        spawnWave(wave);
    }

    public void spawnWave(int wave) {
        if (wave < 0) return;

        int waveBorderModBasic = (int) (0.125 * Math.pow(difficulty, 4) - 1.75 * Math.pow(difficulty, 3) + 8.875 * Math.pow(difficulty, 2) + - 15.25 * difficulty + 8);

        int basicEnemies = (int) (wave < (58 - waveBorderModBasic) ? 40 - (40 * Math.exp(-0.02 * difficulty * wave)) + 1 : 0.00005 * difficulty * Math.pow(wave, 3) + 0.3 * wave + 1);
        int tankEnemies = (wave < 5 ? 0 : wave * (((wave - 6) * difficulty) / 20) + 2);
        int runnerEnemies = (wave < 10 ? 0 : (int) (Math.pow(wave, 2) * ((wave + (difficulty * wave)) / 1500.) + 1));

        for (int i = 0; i < basicEnemies; i++) {
            BasicEnemy enemy = new BasicEnemy();
            enemies.add(enemy);
        }

        for (int i = 0; i < tankEnemies; i++) {
            TankEnemy enemy = new TankEnemy();
            enemies.add(enemy);
        }

        for (int i = 0; i < runnerEnemies; i++) {
            RunnerEnemy enemy = new RunnerEnemy();
            enemies.add(enemy);
        }
    }

    public void spawnEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void runEnemyLogic() {
        TileMap map = gameController.getTileMap();

        ArrayList<Enemy> deadEnemies = new ArrayList<>();

        for ( Enemy enemy : enemies ) {
            if (enemy.getHp() <= 0) {
                deadEnemies.add(enemy);
            } else {
                MovementController.moveEnemy(enemy, map);
                enemy.checkForCastle();

                if (enemy.getHp() <= 0) {
                    deadEnemies.add(enemy);
                }
            }
        }

        for ( Enemy deadEnemy : deadEnemies ) {
            map.getTile(deadEnemy.getPosition()).setEnemy(null);
            enemies.remove(deadEnemy);
            gameController.addCoins(5);
        }

        if (enemies.isEmpty()) {
            framesSinceLastWave++;
        }

        if (framesSinceLastWave >= 200) {
            spawnNextWave();
            framesSinceLastWave = 0;
        }
    }

    public int getWave() {
        return wave;
    }

    public int getEnemiesRemaining() {
        return enemies.size();
    }
}
