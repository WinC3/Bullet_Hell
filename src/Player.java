import java.awt.Graphics;
import java.awt.Color;

public class Player extends Entity {
    public static final int SIZE = 30; // in pixels
    private static final int MAX_HEALTH = 100;
    private static final int INITIAL_SPEED = 5;

    public Player(int x, int y) {
        super(x, y, MAX_HEALTH, INITIAL_SPEED, SIZE);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, SIZE, SIZE);
    }
}
