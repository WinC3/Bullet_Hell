import javax.swing.*;
import java.util.concurrent.*;

public class EndlessGame extends MainGame {

    private int enemySpawnTimer = 0;

    private ScheduledExecutorService executor;

    public EndlessGame(GameWindow gameWindow) {
        super(gameWindow);

        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            SwingUtilities.invokeLater(() -> {
                update();
                if (enemySpawnTimer == 0) {
                    enemies.add(
                            new TeleportingEnemy((WIDTH - Enemy.SIZE) / 2, (int) (100 + Math.random() * 100), 100, this,
                                    AttackPattern.BIGGER_SUPER));
                    enemySpawnTimer++;
                }
                enemySpawnTimer += Math.random() * 3; // expected to add 1 overall; add a little randomness to spawn
                                                      // intervals
                enemySpawnTimer %= 5 * 1000 / 10; // average wait to spawn in seconds
            });
        }, 0, 10, TimeUnit.MILLISECONDS);

        SwingUtilities.invokeLater(() -> requestFocusInWindow());
    }

    public void stopUpdates() {
        super.stopUpdates();
        executor.shutdown();
    }
}
