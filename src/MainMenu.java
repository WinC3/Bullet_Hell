import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    public static final int WIDTH = GameWindow.WIDTH;
    public static final int HEIGHT = GameWindow.HEIGHT;

    private JButton levelSelect;
    private JButton endless;
    private JButton settings;

    public MainMenu(GameWindow gameWindow) {
        super();
        setVisible(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);

        setLayout(null);
        endless = new JButton("Endless");
        endless.setBounds(WIDTH / 2 - 75, HEIGHT / 2, 150, 40);
        endless.addActionListener(e -> {
            gameWindow.showMainGame();
        });
        add(endless);

        settings = new JButton("Settings");
        settings.setBounds(WIDTH / 2 - 75, HEIGHT / 2 + 50, 150, 40);
        settings.addActionListener(e -> {
            gameWindow.showSettings();
        });
        add(settings);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Some Bullet Hell?", WIDTH / 2 - 230, 150);
    }
}
