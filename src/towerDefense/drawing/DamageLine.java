package towerDefense.drawing;

import static towerDefense.TowerDefenseGame.gameController;

public class DamageLine {

    private final int start_x;
    private final int start_y;
    private final int end_x;
    private final int end_y;

    private final long drawUntil;

    public DamageLine(int start_x, int start_y, int end_x, int end_y, int duration) {
        this.start_x = start_x;
        this.start_y = start_y;
        this.end_x = end_x;
        this.end_y = end_y;

        int fps = gameController.getFps();

        int durationInMillis = (1000 / fps) * duration;

        this.drawUntil = System.currentTimeMillis() + durationInMillis;
    }

    public int getStart_x() {
        return start_x;
    }

    public int getStart_y() {
        return start_y;
    }

    public int getEnd_x() {
        return end_x;
    }

    public int getEnd_y() {
        return end_y;
    }

    public long getDrawUntil() {
        return drawUntil;
    }
}
