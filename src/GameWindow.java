import java.awt.*;
import javax.swing.*;

class GameWindow extends JFrame {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    private JPanel curPanel;
    private Timer timer;

    public GameWindow() {
        super("Game Window");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        curPanel = new MainGame(this);
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
            GameWindow gameWindow = new GameWindow();
        });
    }

    public void showMainGame() {
        SwingUtilities.invokeLater(() -> {
            remove(curPanel);
            curPanel = new MainGame(this);
            add(curPanel, BorderLayout.CENTER);
            pack();
        });
    }

    public void showGameOverScreen() {
        SwingUtilities.invokeLater(() -> {
            ((MainGame) curPanel).stopUpdates();
            remove(curPanel);
            curPanel = new GameOverScreen(this);
            add(curPanel, BorderLayout.CENTER);
            this.revalidate();
            pack();
        });
    }
}