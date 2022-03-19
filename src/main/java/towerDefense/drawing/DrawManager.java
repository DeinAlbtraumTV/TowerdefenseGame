package towerDefense.drawing;

import towerDefense.map.Tile;
import towerDefense.map.TileMap;

public class DrawManager {

    private final TileMap tileMap;

    public DrawManager(TileMap tileMap) {
        this.tileMap = tileMap;
    }

    public String getImageBasePath(int x, int y) {
        return tileMap.getTile(x / ZeichenPanel.GRID_SIZE, y / ZeichenPanel.GRID_SIZE).getImagePath();
    }

    public String getImageBasePathDirect(int x, int y) {
        return tileMap.getTile(x, y).getImagePath();
    }

    public void drawPoint(int x, int y, Tile.TileType tileType) {
        x = x / ZeichenPanel.GRID_SIZE;
        y = y / ZeichenPanel.GRID_SIZE;
        if (x < tileMap.getWidth() && y < tileMap.getHeight() && !tileMap.getTile(x, y).hasEnemy()) {
            tileMap.getTile(x, y).setType(tileType);
        }
    }
}
