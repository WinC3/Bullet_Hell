import java.awt.Graphics;
import java.awt.Color;

public class Enemy extends Entity {
    public static final int SIZE = 30; // in pixels
    private static final int INITIAL_SPEED = 5;

    private MovePattern movePattern = MovePattern.SIDE_TO_SIDE;
    private Direction direction = Direction.RIGHT;

    public Enemy(int x, int y, int health) {
        super(x, y, health, INITIAL_SPEED, SIZE);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, SIZE, SIZE);
    }

    public void move() {
        switch (movePattern) {
            case MovePattern.SIDE_TO_SIDE:
                if (x < 0 || x > MainGame.WIDTH - SIZE) {
                    direction = direction == Direction.RIGHT ? Direction.LEFT : Direction.RIGHT;
                }
                x += direction.getX() * speed;
                break;

            default:
                break;
        }
    }
}
