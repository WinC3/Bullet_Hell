import java.awt.*;
import javax.swing.*;

class GameWindow extends JFrame {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    private JPanel curPanel;
    private Timer timer;

    private int score;

    public GameWindow() {
        super("Game Window");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        curPanel = new MainMenu(this);
        add(curPanel, BorderLayout.CENTER);

        timer = new Timer(10, e -> { // 100 fps
            SwingUtilities.invokeLater(() -> {
                curPanel.repaint();
            });
        });
        timer.start();

        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameWindow();
        });
    }

    public void showMainMenu() {
        SwingUtilities.invokeLater(() -> {
            this.remove(curPanel);
            curPanel = new MainMenu(this);
            this.add(curPanel, BorderLayout.CENTER);
            this.revalidate();
            this.pack();
        });
    }

    public void showEndlessGame() {
        SwingUtilities.invokeLater(() -> {
            this.remove(curPanel);
            curPanel = new EndlessGame(this);
            this.add(curPanel, BorderLayout.CENTER);
            this.revalidate();
            this.pack();
        });
    }

    public void showSettings() {
        SwingUtilities.invokeLater(() -> {
            this.remove(curPanel);
            curPanel = new Settings(this);
            this.add(curPanel, BorderLayout.CENTER);
            this.revalidate();
            this.pack();
        });
    }

    public void showGameOverScreen(MainGame mainGame) {
        SwingUtilities.invokeLater(() -> {
            score = ((MainGame) curPanel).getScore();
            ((MainGame) curPanel).stopUpdates();
            this.remove(curPanel);
            if (mainGame instanceof EndlessGame) {
                curPanel = new EndlessGameOverScreen(this, score);
            } else if (mainGame instanceof MainGame) {
                curPanel = new GameOverScreen(this, score);
            }
            this.add(curPanel, BorderLayout.CENTER);
            this.revalidate();
            this.pack();
        });
    }
}