import javax.swing.*;
import java.awt.*;

public class LevelSelect extends JPanel {
    public static final int WIDTH = GameWindow.WIDTH;
    public static final int HEIGHT = GameWindow.HEIGHT;

    private JButton toMainMenu;

    public LevelSelect(GameWindow gameWindow) {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        setLayout(null);

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
    }
}
