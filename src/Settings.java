import javax.swing.*;
import java.awt.*;

public class Settings extends JPanel {
    public static final int WIDTH = MainGame.WIDTH;
    public static final int HEIGHT = MainGame.HEIGHT;

    private JButton deleteEndlessScores;
    private JButton toMainMenu;

    private GameWindow gameWindow;

    public Settings(GameWindow gameWindow) {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
        setFocusable(true);

        this.gameWindow = gameWindow;

        setLayout(null);

        deleteEndlessScores = new JButton("Delete Endless Scores");
        deleteEndlessScores.setBounds(WIDTH / 2 - 100, HEIGHT / 2, 200, 40);
        deleteEndlessScores.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "This will delete all scores", "Are you sure?",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                new EndlessScores().deleteScores();
            }
        });
        add(deleteEndlessScores);

        toMainMenu = new JButton("Main Menu");
        toMainMenu.setBounds(WIDTH / 2 - 75, HEIGHT / 2 + 50, 150, 40);
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
        g.drawString("Settings", WIDTH / 2 - 100, 200);
    }
}
