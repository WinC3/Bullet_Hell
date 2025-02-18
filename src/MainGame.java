import javax.swing.*;
import java.awt.*;

public class MainGame extends JPanel {
    public static final int WIDTH = GameWindow.WIDTH;
    public static final int HEIGHT = GameWindow.HEIGHT;

    public MainGame() {
        super();
        setFocusable(true);
        requestFocus();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

        });
    }

}
