package towerDefense.towers;

import towerDefense.drawing.DamageLine;
import towerDefense.drawing.ZeichenPanel;
import towerDefense.enemies.Enemy;
import towerDefense.enemies.EnemyPriority;
import towerDefense.map.Tile;

import java.util.PriorityQueue;

import static towerDefense.TowerDefenseGame.gameController;
import static towerDefense.TowerDefenseGame.zeichenPanel;

public class Tower {

    public static Tower createTower(int x, int y, TowerType type) {
        switch (type) {
            case BASIC:
            default:
                return new BasicTower(x,y);
            case LASER:
                return new LaserTower(x,y);
            case LIGHTING:
                return new LightningTower(x,y);
        }
    }

    private final String imagePath;

    private final int x;
    private final int y;

    private final int range;
    private final double damage;

    private final int attackRate;
    private int framesSinceLastAttack;

    private final int targets;

    private final int cost;

    public Tower(int x, int y, int range, double damage, int attackRate, int targets, String imagePath, int cost) {
        this.x = x;
        this.y = y;

        this.range = range;
        this.damage = damage;

        this.attackRate = attackRate;
        this.framesSinceLastAttack = 0;

        this.targets = targets;

        this.imagePath = imagePath;

        this.cost = cost;
    }

    public synchronized void attack() {
        int targetsAttacked = 0;

        framesSinceLastAttack++;

        if (framesSinceLastAttack > attackRate) {
            PriorityQueue<EnemyPriority> enemiesToAttack = new PriorityQueue<>();

            for (int i = -1 * range; i < range + 1; i++) {
                for (int j = -1 * range; j < range + 1; j++) {
                    if (i == 0 && j == 0) continue;

                    Tile tile = gameController.getTileMap().getTile(x + i, y + j);

                    if (tile == null) continue;

                    Enemy enemy = tile.getEnemy();

                    if (enemy != null) {
                        enemiesToAttack.add(new EnemyPriority(enemy, enemy.getStepsRemaining()));
                    }
                }
            }

            for ( EnemyPriority enemyPriority : enemiesToAttack ) {
                if (targetsAttacked >= targets) break;

                damageEnemy(enemyPriority.getEnemy());
                targetsAttacked++;
            }

            framesSinceLastAttack = 0;
        }
    }

    //Override for special effects on hit like slowness or something, idk
    public void damageEnemy(Enemy enemy) {
        enemy.damage(damage);

        zeichenPanel.addDamageLine(new DamageLine((this.x * ZeichenPanel.GRID_SIZE) + ZeichenPanel.GRID_SIZE / 2, (this.y * ZeichenPanel.GRID_SIZE) + ZeichenPanel.GRID_SIZE / 2, (enemy.getPosX() * ZeichenPanel.GRID_SIZE) + ZeichenPanel.GRID_SIZE / 2, (enemy.getPosY() * ZeichenPanel.GRID_SIZE) + ZeichenPanel.GRID_SIZE / 2, (attackRate / 2) + 2));
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRange() {
        return range;
    }

    public double getDamage() {
        return damage;
    }

    public int getAttackRate() {
        return attackRate;
    }

    public int getTargets() {
        return targets;
    }

    public int getCost() {
        return cost;
    }
}
