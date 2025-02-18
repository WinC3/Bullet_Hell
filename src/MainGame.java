import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class MainGame extends JPanel {
    public static final int WIDTH = GameWindow.WIDTH;
    public static final int HEIGHT = GameWindow.HEIGHT;

    private Player player;
    private List<Enemy> enemies;
    private List<Bullet> bullets;

    private Timer timer;

    public MainGame() {
        super();
        setFocusable(true);
        requestFocus();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        player = new Player((WIDTH - Player.SIZE) / 2, HEIGHT - HEIGHT / 8);

        enemies = new ArrayList<>();
        enemies.add(new Enemy((WIDTH - Enemy.SIZE) / 2, 100, 100)); // generic enemy for testing

        bullets = new ArrayList<>();

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

        player.draw(g);

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    public void update() {

        for (Enemy enemy : enemies) {
            enemy.move();
        }

        for (Bullet bullet : bullets) {
            bullet.move();
        }

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (bullet.isOffScreen()) {
                bullets.remove(i);
                i--;
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);
                if (bullet.isColliding(enemy)) {
                    bullets.remove(i);
                    i--;
                    if (enemy.takeDamage(bullet.getDamage()) <= 0) { // enemy is dead
                        enemies.remove(j);
                        j--;
                    }
                }
            }
        }
    }

}
