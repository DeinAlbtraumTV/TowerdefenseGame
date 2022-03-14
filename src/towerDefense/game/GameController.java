package towerDefense.game;

import towerDefense.map.Tile;
import towerDefense.map.TileMap;
import towerDefense.enemies.EnemyController;
import towerDefense.enemies.pathfinding.Pathfinder;
import towerDefense.towers.TowerController;

import java.util.Random;

public class GameController {

    private double hp;
    private TileMap tileMap;
    private final EnemyController enemyController;
    private final TowerController towerController;
    private final int fps;
    private Pathfinder pathfinder = null;
    private int coins;
    private boolean spawnNextWave = false;
    private boolean autoSpawnNextWave = false;

    public GameController(double maxHP, int coins, int size_x, int size_y, int difficulty, int fps) {
        hp = maxHP;

        this.enemyController = new EnemyController(difficulty);
	    this.fps = fps;
        this.towerController = new TowerController();
        this.coins = coins;

        generateNewMap(size_x, size_y, 0);
    }

    public double getHp() {
        return hp;
    }

    public void damage(double damage) {
        hp -= damage;
    }

    public TileMap getTileMap() {
        return tileMap;
    }

    public EnemyController getEnemyController() {
        return enemyController;
    }
    
    public int getFps() {
	return fps;
    }
    
    public void setPathfinder(Pathfinder pathfinder) {
	this.pathfinder = pathfinder;
    }
    
    public Pathfinder getPathfinder() {
	return pathfinder;
    }

    public TowerController getTowerController() {
        return towerController;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }

    public void removeCoins(int coins) {
        this.coins -= coins;
    }

    public void generateNewMap(int size_x, int size_y, int rocks) {
        this.tileMap = new TileMap(size_x, size_y);

        Random random = new Random();

        int spawn_x = random.nextInt(this.tileMap.getWidth());
        int spawn_y = random.nextInt(this.tileMap.getHeight());

        int finish_x = random.nextInt(this.tileMap.getWidth());
        int finish_y = random.nextInt(this.tileMap.getHeight());

        while (Math.abs(spawn_x - finish_x) <= 1) {
            finish_x = random.nextInt(this.tileMap.getWidth());
        }

        while (Math.abs(spawn_y - finish_y) <= 1) {
            finish_y = random.nextInt(this.tileMap.getHeight());
        }

        int rocksPlaced = 0;

        while (rocksPlaced < rocks) {
            int x = random.nextInt(this.tileMap.getWidth());
            int y = random.nextInt(this.tileMap.getHeight());

            if (!tileMap.getTile(x, y).getType().equals(Tile.TileType.ROCK)) {
                tileMap.getTile(x, y).setType(Tile.TileType.ROCK);
                rocksPlaced++;
            }
        }

        tileMap.setSpawn(spawn_x, spawn_y);
        tileMap.setCastle(finish_x, finish_y);

        enemyController.clearEnemies();
        enemyController.setWave(0);
        towerController.clearTowers();
    }

    public void spawnNextWave(boolean val) {
        spawnNextWave = val;
    }

    public void setAutoNextWave(boolean val) {
        autoSpawnNextWave = val;
    }

    public boolean canSpawnNextWave() {
        return spawnNextWave || autoSpawnNextWave;
    }

    public boolean getAutoSpawnNextWave() {
        return autoSpawnNextWave;
    }
}
