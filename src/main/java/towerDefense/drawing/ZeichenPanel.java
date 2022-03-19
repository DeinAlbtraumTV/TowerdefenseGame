package towerDefense.drawing;

import towerDefense.enemies.Enemy;
import towerDefense.enemies.pathfinding.Path;
import towerDefense.enemies.pathfinding.PathNode;
import towerDefense.map.Tile;
import towerDefense.map.TileMap;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

import static towerDefense.TowerDefenseGame.gameController;

/**
 * @author michael
 */
public class ZeichenPanel extends JPanel {

    private Image image;
    private Graphics2D g2d;

    public static final int GRID_SIZE = 32;

    public ZeichenPanel() {
        super();

        TileMap tileMap = gameController.getTileMap();

        this.setPreferredSize(new Dimension(GRID_SIZE * tileMap.getWidth(), GRID_SIZE * tileMap.getHeight()));
        this.setLayout(null);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        if (image != null) {
            size.width = image.getWidth(this);
            size.height = image.getHeight(this);
        }
        return size;
    }

    public void setPaintColor(final Color color) {
        g2d.setColor(color);
    }

    public void clearPaint() {
        g2d.clearRect(0, 0, getWidth(), getHeight());
        repaint();
        g2d.setColor(Color.BLACK);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        if (image == null || image.getWidth(this) < getSize().width || image.getHeight(this) < getSize().height) {
            resetImage();
        }
        Graphics2D g2 = (Graphics2D) g;
        Rectangle r = g.getClipBounds();
        this.updateImage();
        g2.drawImage(image, r.x, r.y, r.width + r.x, r.height + r.y, r.x, r.y, r.width + r.x, r.height + r.y, this);
    }

    public void resetImage() {
        Image saveImage = image;
        Graphics2D saveG2d = g2d;

        image = createImage(getWidth(), getHeight());

        g2d = (Graphics2D) image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));

        if (saveG2d != null) {
            g2d.setColor(saveG2d.getColor());
            g2d.drawImage(saveImage, 0, 0, this);
            saveG2d.dispose();
        }
    }

    public Graphics2D getG2d() {
        return g2d;
    }

    ReentrantLock lock = new ReentrantLock();

    ArrayList<DamageLine> damageLines = new ArrayList<>();

    public void updateImage() {
        lock.lock();
        try {
            clearPaint();

            TileMap tileMap = gameController.getTileMap();

            Path path = gameController.getPathfinder().findPath(tileMap.getSpawn().x, tileMap.getSpawn().y, tileMap.getCastle().x, tileMap.getCastle().y);

            for (int i = 0; i < tileMap.getWidth(); i++) {
                for (int j = 0; j < tileMap.getHeight(); j++) {
                    Tile tile = tileMap.getTile(i, j);
                    String imagePath = tile.getImagePath();

                    if ((tile.getType().equals(Tile.TileType.FLOOR) && path.getPathNodes().contains(new PathNode(i, j))) || (tile.getType().equals(Tile.TileType.WALL) && !tile.hasTower())) {
                        if (0 <= j - 1
                                && (tileMap.getTile(i, j - 1).getType().equals(tile.getType())
                                    || (tile.getType().equals(Tile.TileType.FLOOR)
                                        && (tileMap.getTile(i, j - 1).getType().equals(Tile.TileType.CASTLE)
                                            || tileMap.getTile(i, j - 1).getType().equals(Tile.TileType.SPAWN))))
                                && ((path.getPathNodes().contains(new PathNode(i, j - 1))
                                        || tileMap.getTile(i, j - 1).getType().equals(Tile.TileType.SPAWN))
                                    || tile.getType().equals(Tile.TileType.WALL))) {
                            imagePath += "1";
                        } else {
                            imagePath += "0";
                        }
                        if (0 <= i - 1
                                && (tileMap.getTile(i - 1, j).getType().equals(tile.getType())
                                    || (tile.getType().equals(Tile.TileType.FLOOR)
                                        && (tileMap.getTile(i - 1, j).getType().equals(Tile.TileType.CASTLE)
                                            || tileMap.getTile(i - 1, j).getType().equals(Tile.TileType.SPAWN))))
                                && ((path.getPathNodes().contains(new PathNode(i - 1, j))
                                        || tileMap.getTile(i - 1, j).getType().equals(Tile.TileType.SPAWN))
                                    || tile.getType().equals(Tile.TileType.WALL))) {
                            imagePath += "1";
                        } else {
                            imagePath += "0";
                        }
                        if (i + 1 < tileMap.getWidth()
                                && (tileMap.getTile(i + 1, j).getType().equals(tile.getType())
                                    || (tile.getType().equals(Tile.TileType.FLOOR)
                                        && (tileMap.getTile(i + 1, j).getType().equals(Tile.TileType.CASTLE)
                                            || tileMap.getTile(i + 1, j).getType().equals(Tile.TileType.SPAWN))))
                                && ((path.getPathNodes().contains(new PathNode(i + 1, j))
                                        || tileMap.getTile(i + 1, j).getType().equals(Tile.TileType.SPAWN))
                                    || tile.getType().equals(Tile.TileType.WALL))) {
                            imagePath += "1";
                        } else {
                            imagePath += "0";
                        }
                        if (j + 1 < tileMap.getHeight()
                                && (tileMap.getTile(i, j + 1).getType().equals(tile.getType())
                                    || (tile.getType().equals(Tile.TileType.FLOOR)
                                        && (tileMap.getTile(i, j + 1).getType().equals(Tile.TileType.CASTLE)
                                            || tileMap.getTile(i, j + 1).getType().equals(Tile.TileType.SPAWN))))
                                && ((path.getPathNodes().contains(new PathNode(i, j + 1))
                                        || tileMap.getTile(i, j + 1).getType().equals(Tile.TileType.SPAWN))
                                    || tile.getType().equals(Tile.TileType.WALL))) {
                            imagePath += "1";
                        } else {
                            imagePath += "0";
                        }
                    }

                    if (tile.getType().hasVariants()) {
                        imagePath += "_" + (i * j + i + j) % tile.getType().getVariants();
                    }

                    imagePath += ".png";

                    URL url = getClass().getResource(imagePath);

                    if (url == null) {
                        url = getClass().getResource("/misc/Unknown.png");
                        System.out.println(imagePath);
                    }

                    Image image = new ImageIcon(url).getImage();
                    g2d.drawImage(image, i * GRID_SIZE, j * GRID_SIZE, this);

                    if (tile.hasEnemy()) {
                        Enemy enemy = tile.getEnemy();

                        imagePath = enemy.getImagePath() + ".png";

                        url = getClass().getResource(imagePath);

                        if (url == null) {
                            url = getClass().getResource("/misc/Unknown.png");
                        }

                        image = new ImageIcon(url).getImage();
                        g2d.drawImage(image, i * GRID_SIZE, j * GRID_SIZE, this);
                    }
                }
            }

            g2d.setColor(new Color(50, 50, 50));

            for (int i = 0; i <= tileMap.getWidth(); i++) {
                g2d.drawLine(GRID_SIZE * i, 0, GRID_SIZE * i, GRID_SIZE * tileMap.getHeight());
            }

            g2d.setColor(new Color(50, 50, 50));

            for (int i = 0; i <= tileMap.getHeight(); i++) {
                g2d.drawLine(0, GRID_SIZE * i, GRID_SIZE * tileMap.getWidth(), GRID_SIZE * i);
            }

            g2d.setColor(Color.RED);

            for (Iterator<DamageLine> iter = damageLines.iterator(); iter.hasNext();) {
                DamageLine damageLine = iter.next();

                if (damageLine.getDrawUntil() - System.currentTimeMillis() < 0) {
                    iter.remove();
                } else {
                    g2d.drawLine(damageLine.getStart_x(), damageLine.getStart_y(), damageLine.getEnd_x(), damageLine.getEnd_y());
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void addDamageLine(DamageLine line) {
        lock.lock();
        try {
            damageLines.add(line);
        } finally {
            lock.unlock();
        }
    }
}
