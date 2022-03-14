package towerDefense;

import towerDefense.map.Tile;
import towerDefense.drawing.DrawManager;
import towerDefense.drawing.ZeichenPanel;
import towerDefense.enemies.EnemyController;
import towerDefense.enemies.pathfinding.ManhattanHeuristic;
import towerDefense.enemies.pathfinding.Pathfinder;
import towerDefense.game.GameController;
import towerDefense.towers.BasicTower;
import towerDefense.towers.Tower;
import towerDefense.towers.TowerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TowerDefenseGame extends javax.swing.JFrame {

    private Tile.TileType tileTypeDragged;
    private Tile.TileType oldTileTypeDragged;

    private static final int size_x = 13;
    private static final int size_y = 13;
    private static final int difficulty = 1;

    private static final double maxHp = 5.;

    public static final GameController gameController = new GameController(maxHp, 20, size_x, size_y, difficulty, 40);

    public static ZeichenPanel zeichenPanel;

    private final ScheduledExecutorService scheduledExecutorService;

    private ScheduledFuture<?> mainLoop;

    private final TimerTask mainTask;

    private int speedMultiplier;

    public TowerDefenseGame() {
        initComponents();

        speedMultiplier = 1;

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        EnemyController enemyController = gameController.getEnemyController();
        TowerController towerController = gameController.getTowerController();

        gameController.setPathfinder(new Pathfinder(gameController.getTileMap(), new ManhattanHeuristic()));

        mainTask = new TimerTask() {
            private int level = 0;

            @Override
            public void run() {
                if (enemyController.getWave() > 50) {
                    level++;
                    gameController.generateNewMap(size_x, size_y, level);
                    gameController.setCoins(20);
                    gameController.setPathfinder(new Pathfinder(gameController.getTileMap(), new ManhattanHeuristic()));
                    enemyController.setWave(0);
                    enemyController.clearEnemies();
                    towerController.clearTowers();
                    gameController.setAutoNextWave(false);
                    toggleAutoWaveButton.setBackground(Color.RED);
                }

                enemyController.runEnemyLogic();
                towerController.runTowerLogic();

                jLabel1.setText("Wave: " + enemyController.getWave());
                jLabel2.setText("Coins: " + gameController.getCoins());
                jLabel3.setText("Enemies remaining: " + enemyController.getEnemiesRemaining());
                jLabel4.setText("HP: " + (int) gameController.getHp());

                if (gameController.getHp() <= 0) {
                    System.exit(0);
                }
            }
        };

        mainLoop = scheduledExecutorService.scheduleAtFixedRate(mainTask, 0L, 1000 / gameController.getFps(), TimeUnit.MILLISECONDS);

        zeichenPanel = zeichenPanel1;
    }

    //GEN-BEGIN:initComponents
    private void initComponents() {

        speedUpButton = new JButton();
        zeichenPanel1 = new ZeichenPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();

        nextWaveButton = new JButton();
        toggleAutoWaveButton = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        speedUpButton.setText("Toggle Yeet-Mode");
        speedUpButton.setBackground(Color.RED);
        speedUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                speedUpButtonClicked(evt);
            }
        });

        zeichenPanel1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                zeichenPanel1MouseDragged(evt);
            }
        });

        zeichenPanel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                zeichenPanel1MouseClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt) {
                zeichenPanel1MousePressed(evt);
            }
        });

        nextWaveButton.setText("Next Wave");
        nextWaveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                nextWaveClicked(evt);
            }
        });

        toggleAutoWaveButton.setText("Toggle Auto-Wave");
        toggleAutoWaveButton.setBackground(Color.RED);
        toggleAutoWaveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                toggleAutoWaveClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 24));
        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel1");

        jLabel3.setText("jLabel1");

        jLabel4.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 24));
        jLabel4.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                    .addContainerGap(0, Short.MAX_VALUE)
                    .addComponent(nextWaveButton)
                    .addComponent(toggleAutoWaveButton)
                    .addComponent(speedUpButton)
                    .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(zeichenPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(zeichenPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nextWaveButton)
                                    .addComponent(toggleAutoWaveButton)
                                    .addComponent(speedUpButton))
                    .addContainerGap())
        );

        pack();
    }//GEN-END:initComponents

    private void speedUpButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_endeButtonMouseClicked
        if (speedMultiplier == 1) {
            speedMultiplier = 2;
            speedUpButton.setBackground(Color.GREEN);
        } else {
            speedMultiplier = 1;
            speedUpButton.setBackground(Color.RED);
        }

        mainLoop.cancel(false);
        mainLoop = scheduledExecutorService.scheduleAtFixedRate(mainTask, 0L, 1000 / ((long) gameController.getFps() * speedMultiplier), TimeUnit.MILLISECONDS);
    }//GEN-LAST:event_endeButtonMouseClicked

    private void zeichenPanel1MouseClicked(MouseEvent evt) {//GEN-FIRST:event_zeichenPanel1MouseClicked
        int x = evt.getX();
        int y = evt.getY();

        if (x / 22 < 0 || x / 22 >= size_x) {
            System.out.println(x / 22);
            return;
        }
        if (y / 22 < 0 || y / 22 >= size_y) {
            System.out.println(y / 22);
            return;
        }

        if (MouseEvent.BUTTON1 == evt.getButton()) {
            DrawManager drawManager = gameController.getTileMap().getDrawManager();

            Tile.TileType tileType = gameController.getTileMap().getTile(x / 22, y / 22).getType();

            Tile.TileType oldTileType = tileType;

            if (tileType != Tile.TileType.FLOOR && tileType != Tile.TileType.WALL) {
                System.out.println(tileType);
                return;
            }

            if (tileType != Tile.TileType.WALL)
                tileType = Tile.TileType.WALL;
            else
                tileType = Tile.TileType.FLOOR;

            drawManager.drawPoint(x, y, tileType);

            Tile tile = gameController.getTileMap().getTile(x / 22, y / 22);
            if (tile.hasTower()) {
                Tower tower = tile.getTower();

                tile.setTower(null);
                gameController.getTowerController().removeTower(tower);
            }

            Point spawn = gameController.getTileMap().getSpawn();
            Point castle = gameController.getTileMap().getCastle();

            if (gameController.getPathfinder().findPath(spawn.x, spawn.y, castle.x, castle.y) == null && tileType != Tile.TileType.FLOOR) {
                drawManager.drawPoint(x, y, oldTileType);
            }
        } else if (MouseEvent.BUTTON3 == evt.getButton()) {
            x = x / 22;
            y = y / 22;

            if (gameController.getTileMap().getTile(x, y).getType().equals(Tile.TileType.WALL) && !gameController.getTileMap().getTile(x, y).hasTower()) {
                BasicTower tower = new BasicTower(x, y);

                if (gameController.getCoins() >= tower.getCost()) {
                    gameController.getTowerController().addTower(tower);
                    gameController.getTileMap().getTile(x, y).setTower(tower);

                    gameController.removeCoins(tower.getCost());
                }
            } else if (gameController.getTileMap().getTile(x, y).hasTower()) {
                Tower tower =  gameController.getTileMap().getTile(x, y).getTower();

                gameController.getTowerController().removeTower(tower);
                gameController.getTileMap().getTile(x, y).setTower(null);

                gameController.addCoins(tower.getCost() / 2);
            }
        }
    }//GEN-LAST:event_zeichenPanel1MouseClicked

    private void zeichenPanel1MouseDragged(MouseEvent evt) {//GEN-FIRST:event_zeichenPanel1MouseDragged
        int x = evt.getX();
        int y = evt.getY();

        if (x / 22 < 0 || x / 22 >= size_x) return;
        if (y / 22 < 0 || y / 22 >= size_y) return;

        DrawManager drawManager = gameController.getTileMap().getDrawManager();

        if ((evt.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) == MouseEvent.BUTTON1_DOWN_MASK) {
            if (gameController.getTileMap().getTile(x / 22, y / 22).getType() != Tile.TileType.FLOOR && gameController.getTileMap().getTile(x / 22, y / 22).getType() != Tile.TileType.WALL) return;
            if (gameController.getTileMap().getTile(x / 22, y / 22) != null) {
                if (gameController.getTileMap().getTile(x / 22, y / 22).hasTower() && tileTypeDragged == Tile.TileType.FLOOR) {
                    Tower tower = gameController.getTileMap().getTile(x / 22, y / 22).getTower();

                    gameController.getTowerController().removeTower(tower);
                    gameController.getTileMap().getTile(x / 22, y / 22).setTower(null);

                    gameController.addCoins(tower.getCost() / 2);
                }

                drawManager.drawPoint(x, y, tileTypeDragged);

                Point spawn = gameController.getTileMap().getSpawn();
                Point castle = gameController.getTileMap().getCastle();

                if (gameController.getPathfinder().findPath(spawn.x, spawn.y, castle.x, castle.y) == null && tileTypeDragged != Tile.TileType.FLOOR) {
                    drawManager.drawPoint(x, y, oldTileTypeDragged);
                }
            }
        }
    }//GEN-LAST:event_zeichenPanel1MouseDragged

    private void zeichenPanel1MousePressed(MouseEvent evt) {//GEN-FIRST:event_zeichenPanel1MousePressed
        if (MouseEvent.BUTTON1 == evt.getButton()) {
            int x = evt.getX();
            int y = evt.getY();

            Tile.TileType tileType = gameController.getTileMap().getTile(x / 22, y / 22).getType();

            oldTileTypeDragged = tileType;

            if (tileType != Tile.TileType.WALL)
                tileTypeDragged = Tile.TileType.WALL;
            else
                tileTypeDragged = Tile.TileType.FLOOR;
        }
    }//GEN-LAST:event_zeichenPanel1MousePressed

    private void nextWaveClicked(MouseEvent evt) {
        gameController.spawnNextWave(true);
    }

    private void toggleAutoWaveClicked(MouseEvent evt) {
        if (gameController.getAutoSpawnNextWave()) {
            toggleAutoWaveButton.setBackground(Color.RED);
        } else {
            toggleAutoWaveButton.setBackground(Color.GREEN);
        }
        gameController.setAutoNextWave(!gameController.getAutoSpawnNextWave());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
             * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TowerDefenseGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            TowerDefenseGame frame = new TowerDefenseGame();

            frame.setName("Towerdefense Game");
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton speedUpButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private ZeichenPanel zeichenPanel1;
    // End of variables declaration//GEN-END:variables
    private JButton nextWaveButton;
    private JButton toggleAutoWaveButton;
}
