import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class MainGame extends JPanel {
    public static final int WIDTH = GameWindow.WIDTH;
    public static final int HEIGHT = GameWindow.HEIGHT;

    protected Player player;
    protected List<Bullet> playerBullets;
    protected List<Enemy> enemies;
    protected List<Bullet> enemyBullets;
    protected int score = 0;

    private GameWindow gameWindow;

    public MainGame(GameWindow gameWindow) {
        super();
        this.gameWindow = gameWindow;
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        player = new Player((WIDTH - Player.SIZE) / 2, HEIGHT - HEIGHT / 8, this);
        addKeyListener(player);

        enemies = new ArrayList<>();

        playerBullets = new ArrayList<>();
        enemyBullets = new ArrayList<>();

        SwingUtilities.invokeLater(() -> requestFocusInWindow());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        for (Bullet bullet : playerBullets) {
            bullet.draw(g, Color.BLUE);
        }

        for (Bullet bullet : enemyBullets) {
            bullet.draw(g, Color.RED);
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

        // draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, WIDTH - 100, 30);
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
                i = Math.max(0, i);
                i = Math.min(playerBullets.size(), i);
                if (bullet.isColliding(enemy)) {
                    playerBullets.remove(i);
                    i--;
                    j = Math.max(0, j);
                    j = Math.min(enemies.size(), j);
                    if (enemy.takeDamage(bullet.getDamage()) <= 0) { // enemy is dead
                        score++;
                        enemy.stopUpdates();
                        enemies.remove(j);
                        if (enemies.size() == 0) {
                            nextAction();
                        }
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
                    gameWindow.showGameOverScreen(this);
                }
            }
        }
    }

    protected void nextAction() {
        // override
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

        this.removeKeyListener(player);
    }

    public int getScore() {
        return score;
    }

}
