package towerDefense.map;

import towerDefense.enemies.Enemy;
import towerDefense.towers.Tower;

import java.awt.*;

public class Tile {

    public enum TileType {
        FLOOR (Color.WHITE),
        WALL (Color.BLACK),
        ENEMY (Color.CYAN),
        ROCK (Color.PINK),
        SPAWN (Color.RED),
        CASTLE (Color.GREEN);

        private final Color defaultColor;

        TileType(Color color) {
            this.defaultColor = color;
        }

        private Color getDefaultColor() {
            return defaultColor;
        }
    }

    private TileType type;
    private Enemy enemy = null;
    private Tower tower = null;

    public Tile() {
        type = TileType.FLOOR;
    }

    public void setType(TileType type) {
        if (this.type != TileType.CASTLE && this.type != TileType.SPAWN && this.type != TileType.ENEMY)
            this.type = type;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public Tower getTower() {
        return tower;
    }

    public Color getColor() {
        if (enemy != null)
            return enemy.getColor();
        else if (tower != null)
            return tower.getColor();
        else
            return type.getDefaultColor();
    }

    public TileType getType() {
        return type;
    }

    public boolean hasEnemy() {
        return enemy != null;
    }

    public boolean hasTower() {return tower != null;}

    @Override
    public String toString() {
        return "Tile{" + "type=" + type + '}';
    }
}
