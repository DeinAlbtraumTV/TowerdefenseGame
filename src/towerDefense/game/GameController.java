package towerDefense.game;

import towerDefense.map.TileMap;
import towerDefense.enemies.EnemyController;
import towerDefense.enemies.pathfinding.Pathfinder;
import towerDefense.towers.TowerController;

public class GameController {

    private double hp;
    private final TileMap tileMap;
    private final EnemyController enemyController;
    private final TowerController towerController;
    private final int fps;
    private Pathfinder pathfinder = null;
    private int coins;

    public GameController(double maxHP, int coins, int size_x, int size_y, int difficulty, int fps) {
        hp = maxHP;
        this.tileMap = new TileMap(size_x, size_y);
        this.enemyController = new EnemyController(difficulty);
	    this.fps = fps;
        this.towerController = new TowerController();
        this.coins = coins;
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
}
