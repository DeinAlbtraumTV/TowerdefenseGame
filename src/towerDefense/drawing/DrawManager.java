package towerDefense.drawing;

import towerDefense.map.Tile;
import towerDefense.map.TileMap;

import java.awt.*;

public class DrawManager {

    private final TileMap tileMap;

    public DrawManager(TileMap tileMap) {
        this.tileMap = tileMap;
    }

    public Color getColor(int x, int y) {
        return tileMap.getTile(x / 22, y / 22).getColor();
    }

    public Color getColorDirect(int x, int y) {
        return tileMap.getTile(x, y).getColor();
    }

    public void drawPoint(int x, int y, Tile.TileType tileType) {
        x = x / 22;
        y = y / 22;
        if (x < tileMap.getWidth() && y < tileMap.getHeight() && !tileMap.getTile(x, y).hasEnemy()) {
            tileMap.getTile(x, y).setType(tileType);
        }
    }
}
