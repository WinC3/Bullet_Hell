import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import javax.swing.*;
import java.awt.*;

public class Enemy extends Entity {
    public static final int SIZE = 30; // in pixels
    private static final int INITIAL_SPEED = 2;

    private MovePattern movePattern = MovePattern.SIDE_TO_SIDE;
    private List<AttackPattern> attackPatterns;
    private int procCounter = 0;
    private Direction direction = Direction.RIGHT;

    private ScheduledExecutorService executor;

    public Enemy(int x, int y, int health, MainGame mainGame) {
        super(x, y, health, INITIAL_SPEED, SIZE);
        attackPatterns = new ArrayList<>();
        attackPatterns.add(AttackPattern.NORMAL);

        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            SwingUtilities.invokeLater(() -> {
                for (AttackPattern attackPattern : new ArrayList<>(attackPatterns)) { // iterate on copy prevent concur
                                                                                      // mod excep
                    switch (attackPattern) {
                        case AttackPattern.NORMAL:
                            if (procCounter == 0) {
                                mainGame.addEnemyBullet(
                                        new Bullet(this.x, this.y, Direction.DOWN, Bullet.NORMAL_BULLET));
                            }
                            break;
                        case AttackPattern.FASTER:
                            if (procCounter % 2 == 0) {
                                mainGame.addEnemyBullet(
                                        new Bullet(this.x, this.y, Direction.DOWN, Bullet.NORMAL_BULLET));
                            }
                            break;
                        case AttackPattern.SUPER:
                            mainGame.addEnemyBullet(new Bullet(this.x, this.y, Direction.DOWN, Bullet.NORMAL_BULLET));
                            break;
                        case AttackPattern.BIGGER:
                            if (procCounter == 0) {
                                mainGame.addEnemyBullet(
                                        new Bullet(this.x, this.y, Direction.DOWN, Bullet.BIGGER_BULLET));
                            }
                            break;
                        case AttackPattern.BIGGER_FASTER:
                            if (procCounter % 2 == 0) {
                                mainGame.addEnemyBullet(
                                        new Bullet(this.x, this.y, Direction.DOWN, Bullet.BIGGER_BULLET));
                            }
                            break;
                        case AttackPattern.BIGGER_SUPER:
                            mainGame.addEnemyBullet(new Bullet(this.x, this.y, Direction.DOWN, Bullet.BIGGER_BULLET));
                            break;
                        default:
                            break;
                    }
                }
                procCounter = (procCounter + 1) % 4; // Cycle 0,1,2,3
            });
        }, 0, 500, TimeUnit.MILLISECONDS);

    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, SIZE, SIZE); // enemy

        g.fillRect((int) x, (int) y - 6, SIZE, 3);
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y - 6, SIZE * health / 100, 3); // health bar

        g.setColor(Color.WHITE);
        g.drawRect((int) x, (int) y, SIZE - 1, SIZE - 1); // enemy outline
    }

    public void move() {
        switch (movePattern) {
            case MovePattern.SIDE_TO_SIDE:
                if (x < 0 || x > MainGame.WIDTH - SIZE) {
                    direction = direction == Direction.RIGHT ? Direction.LEFT : Direction.RIGHT;
                }
                x += direction.getX() * speed;
                break;

            default:
                break;
        }
    }

    public void stopUpdates() {
        executor.shutdown();
    }
}
