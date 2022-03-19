package towerDefense.enemies;

import towerDefense.map.TileMap;

import java.awt.*;
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

        Point spawn = gameController.getTileMap().getSpawn();

        if (wave % 5 == 0) {
            Enemy enemy = new Enemy((wave / 5D) * 10, 1000F / gameController.getFps(), spawn.x, spawn.y, (wave / 5) * 10);
            enemies.add(enemy);
        }

        for (int i = 0; i < basicEnemies; i++) {
            BasicEnemy enemy = new BasicEnemy(wave / 5, spawn.x, spawn.y);
            enemies.add(enemy);
        }

        for (int i = 0; i < tankEnemies; i++) {
            TankEnemy enemy = new TankEnemy(wave / 5, spawn.x, spawn.y);
            enemies.add(enemy);
        }

        for (int i = 0; i < runnerEnemies; i++) {
            RunnerEnemy enemy = new RunnerEnemy(wave / 5, spawn.x, spawn.y);
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
            gameController.addCoins(deadEnemy.getLoot());
        }

        if (enemies.isEmpty()) {
            if (framesSinceLastWave == 0) {
                gameController.addCoins(wave * 5);
            }
            framesSinceLastWave++;
        }

        if (framesSinceLastWave >= 200 && gameController.canSpawnNextWave()) {
            spawnNextWave();
            framesSinceLastWave = 0;
            gameController.spawnNextWave(false);
        }
    }

    public int getWave() {
        return wave;
    }

    public int getEnemiesRemaining() {
        return enemies.size();
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public void clearEnemies() {
        enemies.clear();
    }
}
