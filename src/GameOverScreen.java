import javax.swing.*;
import java.awt.*;

public class GameOverScreen extends JPanel {
    public static final int WIDTH = GameWindow.WIDTH;
    public static final int HEIGHT = GameWindow.HEIGHT;

    private int score;

    private JButton tryAgain;
    private JButton toMainMenu;
    private JButton toLevelSelect;

    private EndlessScores EndlessScores;

    private GameWindow gameWindow;

    public GameOverScreen(GameWindow gameWindow, int score) {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        this.score = score;
        this.gameWindow = gameWindow;

        setLayout(null);

        tryAgain = new JButton("Try Again");
        tryAgain.setBounds(WIDTH / 2 - 75, HEIGHT - 200, 150, 40);
        tryAgain.addActionListener(e -> {
            gameWindow.showMainGame();
        });
        add(tryAgain);

        toMainMenu = new JButton("Main Menu");
        toMainMenu.setBounds(WIDTH / 2 - 75, HEIGHT - 100, 150, 40);
        toMainMenu.addActionListener(e -> {
            gameWindow.showMainMenu();
        });
        add(toMainMenu);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Game Over", WIDTH / 2 - 150, HEIGHT / 2);

        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Score: " + score, WIDTH / 2 - 150, HEIGHT / 2 + 50);
    }

}
