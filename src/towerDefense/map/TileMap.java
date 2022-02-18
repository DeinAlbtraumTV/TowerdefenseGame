package towerDefense.map;

import towerDefense.map.Tile.TileType;
import towerDefense.drawing.DrawManager;
import towerDefense.enemies.EnemyPosition;

import java.util.Arrays;

public class TileMap {

    private final int size_x;
    private final int size_y;

    private final Tile[][] tiles;

    private final DrawManager drawManager;

    public TileMap(int size_x, int size_y) {
        this.size_x = size_x;
        this.size_y = size_y;

        tiles = new Tile[size_x][size_y];

        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                tiles[i][j] = new Tile();
            }
        }

        drawManager = new DrawManager(this);
    }

    public synchronized Tile getTile(int x, int y) {
        if (x < size_x && x >= 0 && y < size_y && y >= 0)
            return tiles[x][y];
        else {
            return null;
        }
    }

    public Tile getTile(EnemyPosition pos) {
        return tiles[pos.getX()][pos.getY()];
    }

    public boolean isTileBlocked(int x, int y) {
        TileType[] validTypes = {TileType.FLOOR, TileType.SPAWN, TileType.CASTLE};

        return Arrays.stream(validTypes).noneMatch(type -> type.equals(getTile(x, y).getType()));
    }

    public int getWidth() {
        return size_x;
    }

    public int getHeight() {
        return size_y;
    }

    public DrawManager getDrawManager() {
        return drawManager;
    }
}
