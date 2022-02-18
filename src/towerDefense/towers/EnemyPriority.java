package towerDefense.towers;

import towerDefense.enemies.Enemy;

public class EnemyPriority implements Comparable<EnemyPriority> {
    private final Enemy enemy;

    private final int priority;

    public EnemyPriority(Enemy enemy, int priority) {
        this.enemy = enemy;

        this.priority = priority;
    }

    @Override
    public int compareTo(EnemyPriority other) {
        return Integer.compare(this.priority, other.priority);
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public int getPriority() {
        return priority;
    }
}
