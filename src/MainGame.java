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

    public MainGame() {
        super();
        setFocusable(true);
        requestFocus();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        player = new Player(WIDTH / 2, HEIGHT - HEIGHT / 8);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

        });
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

}
