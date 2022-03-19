package towerDefense.map;

import towerDefense.enemies.Enemy;
import towerDefense.towers.Tower;

public class Tile {

    public enum TileType {
        FLOOR ("/floor/Floor", 2),
        WALL ("/wall/Wall"),
        ENEMY ("/enemy/Enemy"),
        ROCK ("/rocks/Rock"),
        SPAWN ("/misc/Spawn"),
        CASTLE ("/misc/Castle");

        private final String basePath;
        private final int variants;

        TileType(String path) {this(path, 1);}

        TileType(String path, int variants) {
            this.basePath = path;
            this.variants = variants;
        }

        private String getBasePath() {
            return basePath;
        }

        public boolean hasVariants() {
            return variants > 1;
        }

        public int getVariants() {
            return variants;
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

    public String getImagePath() {
        if (tower != null)
            return tower.getImagePath();
        else
            return type.getBasePath();
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
