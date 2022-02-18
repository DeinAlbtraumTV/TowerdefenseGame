package towerDefense.enemies;

import towerDefense.map.Tile;
import towerDefense.map.TileMap;
import towerDefense.TowerDefenseGame;
import towerDefense.game.GameController;

import java.awt.*;
import java.util.Random;
import static towerDefense.TowerDefenseGame.gameController;

public class Enemy {

    private final EnemyPosition position;
    private double hp;
    private final double maxHP;
    private double speed;
    private final double startingSpeed;
    private final Color color;
    private final int framesUntilMove;
    private int framesSinceLastMove;
    private int stepsRemaining;

    public Enemy() {
        this.hp = 5.;
        this.maxHP = hp;
        this.speed = 1000F / gameController.getFps();
        this.startingSpeed = speed;

        position = new EnemyPosition();

        Random rand = new Random();

        color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	
        framesUntilMove = (int) (1000 / speed) * (40 / gameController.getFps());
        framesSinceLastMove = 0;

        stepsRemaining = 0;
    }

    public Enemy(Color color) {
        this.hp = 5.;
        this.maxHP = hp;
        this.speed = 1000F / gameController.getFps();
        this.startingSpeed = speed;

        position = new EnemyPosition();

        this.color = color;

        framesUntilMove = (int) (1000 / speed) * (40 / gameController.getFps());
        framesSinceLastMove = 0;

        stepsRemaining = 0;
    }

    public Enemy(double hp, double speed) {
        this.hp = hp;
        this.maxHP = hp;
        this.speed = speed;
        this.startingSpeed = speed;

        position = new EnemyPosition();

        Random rand = new Random();

        color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	
        framesUntilMove = (int) (1000 / speed) * (40 / gameController.getFps());
        framesSinceLastMove = 0;

        stepsRemaining = 0;
    }

    public Enemy(double hp, double speed, Color color) {
        this.hp = hp;
        this.maxHP = hp;
        this.speed = speed;
        this.startingSpeed = speed;

        position = new EnemyPosition();

        this.color = color;

        framesUntilMove = (int) (1000 / speed) * (40 / gameController.getFps());
        framesSinceLastMove = 0;

        stepsRemaining = 0;
    }

    public Enemy(int pos_x, int pos_y) {
        this.hp = 5.;
        this.maxHP = hp;
        this.speed = 0.025;
        this.startingSpeed = speed;

        position = new EnemyPosition(pos_x, pos_y);

        Random rand = new Random();

        color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	
        framesUntilMove = (int) (1000 / speed) * (40 / gameController.getFps());
        framesSinceLastMove = 0;

        stepsRemaining = 0;
    }

    public Enemy(double hp, double speed, int pos_x, int pos_y) {
        this.hp = hp;
        this.maxHP = hp;
        this.speed = speed;
        this.startingSpeed = speed;

        position = new EnemyPosition(pos_x, pos_y);

        Random rand = new Random();

        color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	
        framesUntilMove = (int) (1000 / speed) * (40 / gameController.getFps());
        framesSinceLastMove = 0;

        stepsRemaining = 0;
    }

    public Enemy(double hp, double speed, int pos_x, int pos_y, Color color) {
        this.hp = hp;
        this.maxHP = hp;
        this.speed = speed;
        this.startingSpeed = speed;

        position = new EnemyPosition(pos_x, pos_y);

        this.color = color;

        framesUntilMove = (int) (1000 / speed) * (40 / gameController.getFps());
        framesSinceLastMove = 0;

        stepsRemaining = 0;
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

    public Color getColor() {
        return color;
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
}
