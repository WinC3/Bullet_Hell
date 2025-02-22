import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;

public class EndlessGame extends MainGame {
    private double avgTimeBetweenSpawns = 5; // in seconds

    private double enemySpawnTimer = 0;

    private ScheduledExecutorService executor;

    private int scoreUntilDifIncrease = 10;
    private int nextIncrease = 10;

    public EndlessGame(GameWindow gameWindow) {
        super(gameWindow);

        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            SwingUtilities.invokeLater(() -> {
                update();
                if (score >= scoreUntilDifIncrease) {
                    increaseDifficulty();
                    scoreUntilDifIncrease += nextIncrease;
                    nextIncrease *= 1.3;
                }
                if (enemySpawnTimer <= 2) {
                    spawnRandomEnemy();
                    enemySpawnTimer = 3;
                }
                enemySpawnTimer += Math.random() * 3; // expected to add 1 overall; add a little randomness to spawn
                                                      // intervals (0, 1, 2)
                enemySpawnTimer %= avgTimeBetweenSpawns * 1000 / 10;
            });
        }, 0, 10, TimeUnit.MILLISECONDS);

        SwingUtilities.invokeLater(() -> requestFocusInWindow());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString("Average Spawn Interval: " + avgTimeBetweenSpawns, 10, HEIGHT - 10);
    }

    private void spawnRandomEnemy() {
        AttackPattern a;
        switch ((int) (Math.random() * (6))) { // # enemy attack patterns
            case 0:
                a = AttackPattern.NORMAL;
                break;
            case 1:
                a = AttackPattern.FASTER;
                break;
            case 2:
                a = AttackPattern.SUPER;
                break;
            case 3:
                a = AttackPattern.BIGGER;
                break;
            case 4:
                a = AttackPattern.BIGGER_FASTER;
                break;
            case 5:
                a = AttackPattern.BIGGER_SUPER;
                break;
            default:
                a = AttackPattern.NORMAL;
                break;
        }
        Enemy e;
        switch ((int) (Math.random() * (3))) { // # enemy types
            case 0:
                e = new UFOEnemy((WIDTH - Enemy.SIZE) / 2, (int) (100 + Math.random() * 100), 100, this,
                        a);
                break;
            case 1:
                e = new TeleportingEnemy((WIDTH - Enemy.SIZE) / 2, (int) (100 + Math.random() * 100), 100, this,
                        a);
                break;
            default:
                e = new Enemy((WIDTH - Enemy.SIZE) / 2, (int) (100 + Math.random() * 100), 100, this,
                        a);
        }
        enemies.add(e);
    }

    public void increaseDifficulty() {
        avgTimeBetweenSpawns = Math.max(avgTimeBetweenSpawns - 0.5, 1);
    }

    protected void nextAction() {
        enemySpawnTimer = 0;
    }

    public void stopUpdates() {
        super.stopUpdates();
        executor.shutdown();
    }
}
