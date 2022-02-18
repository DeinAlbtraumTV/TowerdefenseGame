package towerDefense.drawing;

import towerDefense.map.TileMap;
import towerDefense.TowerDefenseGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author michael
 */
public class ZeichenPanel extends JPanel {

    private Image image;
    private Graphics2D g2d;

    public ZeichenPanel() {
        super();

        TileMap tileMap = TowerDefenseGame.gameController.getTileMap();

        this.setPreferredSize(new Dimension(22 * tileMap.getWidth(), 22 * tileMap.getHeight()));
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
        g2d.setBackground(Color.WHITE);
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
        g2d.setBackground(Color.WHITE);
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

            TileMap tileMap = TowerDefenseGame.gameController.getTileMap();

            for (int i = 0; i < tileMap.getWidth(); i++) {
                for (int j = 0; j < tileMap.getHeight(); j++) {
                    g2d.setColor(tileMap.getTile(i, j).getColor());
                    g2d.fillRect(1 + 22 * i, 1 + 22 * j, 20, 20);
                }
            }

            g2d.setColor(new Color(50, 50, 50));

            for (int i = 0; i <= tileMap.getWidth(); i++) {
                g2d.drawLine(22 * i, 0, 22 * i, 22 * tileMap.getHeight());
            }

            g2d.setColor(new Color(50, 50, 50));

            for (int i = 0; i <= tileMap.getHeight(); i++) {
                g2d.drawLine(0, 22 * i, 22 * tileMap.getWidth(), 22 * i);
            }

            ArrayList<DamageLine> removeDamageLines = new ArrayList<>();

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
