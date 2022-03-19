package towerDefense.enemies;

import towerDefense.enemies.pathfinding.Path;
import towerDefense.enemies.pathfinding.Step;
import towerDefense.map.TileMap;

import java.awt.*;

import static towerDefense.TowerDefenseGame.gameController;

public class MovementController {
    public static synchronized void moveEnemy(Enemy enemy, TileMap map) {
		map.getTile(enemy.getPosX(), enemy.getPosY()).setEnemy(null);
	
		enemy.setFramesSinceLastMove(enemy.getFramesSinceLastMove() + 1);

		if (enemy.getFramesSinceLastMove() > enemy.getFramesUntilMove()) {
			Point castle = gameController.getTileMap().getCastle();
			Point spawn = gameController.getTileMap().getSpawn();

			Path path = gameController.getPathfinder().findPath(enemy.getPosX(), enemy.getPosY(), castle.x, castle.y);

			if (path == null) {
				if (!map.isTileBlocked(spawn.x, spawn.y) && !map.getTile(spawn.x, spawn.y).hasEnemy()) {
					enemy.moveTo(spawn.x, spawn.y);

					enemy.setFramesSinceLastMove(0);

					enemy.reset();
				}
			} else {
				Step step = path.getStep(0);

				if (!map.isTileBlocked(enemy.getPosX() + step.getMove_x(), enemy.getPosY() + step.getMove_y()) && !map.getTile(enemy.getPosX() + step.getMove_x(), enemy.getPosY() + step.getMove_y()).hasEnemy()) {
					enemy.moveBy(step.getMove_x(), step.getMove_y());

					enemy.setFramesSinceLastMove(0);
					enemy.setStepsRemaining(path.getLength() - 1);
				}
			}
		}

		map.getTile(enemy.getPosX(), enemy.getPosY()).setEnemy(enemy);
	}
}
