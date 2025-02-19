import javax.swing.*;
import java.awt.*;

public class GameOverScreen extends JPanel {
    public static final int WIDTH = GameWindow.WIDTH;
    public static final int HEIGHT = GameWindow.HEIGHT;

    private JButton tryAgain;
    private JButton toLevelSelect;

    private GameWindow gameWindow;

    public GameOverScreen(GameWindow gameWindow) {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        this.gameWindow = gameWindow;

        tryAgain = new JButton("Try Again");
        tryAgain.addActionListener(e -> {
            gameWindow.showMainGame();
        });
        add(tryAgain);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Game Over", WIDTH / 2 - 150, HEIGHT / 2);
    }

}
