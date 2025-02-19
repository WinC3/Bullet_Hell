import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class MainGame extends JPanel {
    public static final int WIDTH = GameWindow.WIDTH;
    public static final int HEIGHT = GameWindow.HEIGHT;

    private Player player;
    private List<Bullet> playerBullets;
    private List<Enemy> enemies;
    private List<Bullet> enemyBullets;

    private Timer timer;

    private GameWindow gameWindow;

    public MainGame(GameWindow gameWindow) {
        super();
        this.gameWindow = gameWindow;
        setFocusable(true);
        requestFocus();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        player = new Player((WIDTH - Player.SIZE) / 2, HEIGHT - HEIGHT / 8);
        addKeyListener(player);

        enemies = new ArrayList<>();
        enemies.add(new Enemy((WIDTH - Enemy.SIZE) / 2, 100, 100, this)); // generic enemy for testing

        playerBullets = new ArrayList<>();
        enemyBullets = new ArrayList<>();

        timer = new Timer(10, e -> {
            update();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        for (Bullet bullet : playerBullets) {
            bullet.draw(g);
        }

        for (Bullet bullet : enemyBullets) {
            bullet.draw(g);
        }

        player.draw(g);

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        // draw health bar
        g.setColor(Color.RED);
        g.fillRect(0, 0, 3, HEIGHT);
        g.setColor(Color.GREEN);
        g.fillRect(0, HEIGHT - HEIGHT * player.getHealth() / Player.MAX_HEALTH, 3, HEIGHT);
    }

    public void update() {
        player.move();

        for (Enemy enemy : enemies) {
            enemy.move();
        }

        for (Bullet bullet : playerBullets) {
            bullet.move();
        }

        for (Bullet bullet : enemyBullets) {
            bullet.move();
        }

        for (int i = 0; i < playerBullets.size(); i++) {
            Bullet bullet = playerBullets.get(i);
            if (bullet.isOffScreen()) {
                playerBullets.remove(i);
                i--;
            }
        }

        for (int i = 0; i < enemyBullets.size(); i++) {
            Bullet bullet = enemyBullets.get(i);
            if (bullet.isOffScreen()) {
                enemyBullets.remove(i);
                i--;
            }
        }

        for (int i = 0; i < playerBullets.size(); i++) {
            Bullet bullet = playerBullets.get(i);
            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);
                if (bullet.isColliding(enemy)) {
                    playerBullets.remove(i);
                    i--;
                    if (enemy.takeDamage(bullet.getDamage()) <= 0) { // enemy is dead
                        enemies.remove(j);
                        j--;
                    }
                }
            }
        }

        for (int i = 0; i < enemyBullets.size(); i++) {
            Bullet bullet = enemyBullets.get(i);
            if (bullet.isColliding(player)) {
                enemyBullets.remove(i);
                i--;
                if (player.takeDamage(bullet.getDamage()) <= 0) { // player is dead
                    player.setDead(true);
                    gameWindow.showGameOverScreen();
                }
            }
        }
    }

    public void addPlayerBullet(Bullet bullet) {
        playerBullets.add(bullet);
    }

    public void addEnemyBullet(Bullet bullet) {
        enemyBullets.add(bullet);
    }

    public void stopUpdates() {
        player.stopUpdates();

        for (Enemy enemy : enemies) {
            enemy.stopUpdates();
        }

        playerBullets.removeAll(playerBullets);
        enemyBullets.removeAll(enemyBullets);

        timer.stop();
    }

}
