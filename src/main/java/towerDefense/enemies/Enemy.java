package towerDefense.enemies;

import towerDefense.TowerDefenseGame;
import towerDefense.game.GameController;
import towerDefense.map.Tile;
import towerDefense.map.TileMap;

import java.awt.*;

import static towerDefense.TowerDefenseGame.gameController;

public class Enemy {

    private final EnemyPosition position;
    private double hp;
    private final double maxHP;
    private double speed;
    private final double startingSpeed;
    private final String imagePath;
    private final int framesUntilMove;
    private int framesSinceLastMove;
    private int stepsRemaining;
    private final int loot;

    public Enemy() {
        this(5., 1000F / gameController.getFps(), "/enemy/Default", 2);
    }

    public Enemy(String imagePath, int loot) {
        this(5., 1000F / gameController.getFps(), imagePath, loot);
    }

    public Enemy(double hp, double speed, int loot) {
        this(hp, speed, "/enemy/Default", loot);
    }

    public Enemy(double hp, double speed, String imagePath, int loot) {
        this(hp, speed, gameController.getTileMap().getSpawn().x, gameController.getTileMap().getSpawn().y,imagePath, loot);
    }

    public Enemy(int pos_x, int pos_y, int loot) {
        this(5., 1000F / gameController.getFps(), pos_x, pos_y, "/enemy/Default", loot);
    }

    public Enemy(int pos_x, int pos_y, String imagePath, int loot) {
        this(5., 1000F / gameController.getFps(), pos_x, pos_y, imagePath, loot);
    }

    public Enemy(double hp, double speed, int pos_x, int pos_y, int loot) {
        this(hp, speed, pos_x, pos_y, "/enemy/Default", loot);
    }

    public Enemy(double hp, double speed, int pos_x, int pos_y, String imagePath, int loot) {
        this.hp = hp;
        this.maxHP = hp;
        this.speed = speed;
        this.startingSpeed = speed;

        position = new EnemyPosition(pos_x, pos_y);

        this.imagePath = imagePath;

        framesUntilMove = (int) (1000 / speed) * (40 / gameController.getFps());
        framesSinceLastMove = 0;

        Point castle = gameController.getTileMap().getCastle();

        stepsRemaining = gameController.getPathfinder().findPath(pos_x, pos_y, castle.x, castle.y).getLength();

        this.loot = loot;
    }

    public void moveTo(double x, double y) {
        position.setX(x);
        position.setY(y);
    }

    public void moveBy(double x, double y) {
        position.addX(x);
        position.addY(y);
    }

    public void damage(double damage) {
        hp -= damage;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setStepsRemaining(int stepsRemaining) {
        this.stepsRemaining = stepsRemaining;
    }

    public int getStepsRemaining() {
        return stepsRemaining;
    }

    public int getPosX() {
        return position.getX();
    }

    public double getPosXDouble() {
        return position.getXDouble();
    }

    public int getPosY() {
        return position.getY();
    }

    public double getPosYDouble() {
        return position.getYDouble();
    }

    public EnemyPosition getPosition() {
        return position;
    }

    public double getHp() {
        return hp;
    }

    public double getSpeed() {
        return speed;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void checkForCastle() {
        GameController gameController = TowerDefenseGame.gameController;

        TileMap map = gameController.getTileMap();

        if (map.getTile(position).getType().equals(Tile.TileType.CASTLE)) {
            gameController.damage(1.);
            this.damage(9999999);
        }
    }
    
    public void setFramesSinceLastMove(int frames) {
	framesSinceLastMove = frames;
    }
    
    public int getFramesSinceLastMove() {
	return framesSinceLastMove;
    }
    
    public int getFramesUntilMove() {
	return framesUntilMove;
    }

    public void reset() {
        this.hp = maxHP;
        this.speed = startingSpeed;
    }

    public int getLoot() {
        return loot;
    }
}
