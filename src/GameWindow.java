import java.awt.*;
import javax.swing.*;

class GameWindow extends JFrame {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    private JPanel curPanel;

    public GameWindow() {
        super("Game Window");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        curPanel = new MainGame();
        add(curPanel);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow gameWindow = new GameWindow();
        });
    }
}