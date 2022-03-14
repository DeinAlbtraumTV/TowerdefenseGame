package towerDefense.towers;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class TowerController {

    private final ArrayList<Tower> towers;

    private final ReentrantLock lock = new ReentrantLock();

    public TowerController() {
        towers = new ArrayList<>();
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public void addTower(Tower tower) {
        lock.lock();
        try {
            towers.add(tower);
        } finally {
            lock.unlock();
        }
    }

    public void removeTower(Tower tower) {
        lock.lock();
        try {
            towers.remove(tower);
        } finally {
            lock.unlock();
        }
    }

    public void runTowerLogic() {
        lock.lock();
        try {
            for (Tower tower : towers) {
                tower.attack();
            }
        } finally {
            lock.unlock();
        }
    }

    public void clearTowers() {
        towers.clear();
    }
}
