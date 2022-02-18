package towerDefense.towers;

import java.util.ArrayList;

public class TowerController {

    private final ArrayList<Tower> towers;

    public TowerController() {
        towers = new ArrayList<>();
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public void removeTower(Tower tower) {
        towers.remove(tower);
    }

    public void runTowerLogic() {
        for ( Tower tower : towers ) {
            tower.attack();
        }
    }
}
