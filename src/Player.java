import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Player extends Entity implements KeyListener {
    public static final int SIZE = 20; // in pixels
    private static final int MAX_HEALTH = 100;
    private static final int INITIAL_SPEED = 5;

    private boolean keysPressed[] = new boolean[4]; // up, down, left, right
    private Direction direction = Direction.UP;

    public Player(int x, int y) {
        super(x, y, MAX_HEALTH, INITIAL_SPEED, SIZE);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int) x, (int) y, SIZE, SIZE);
    }

    public void move() {
        if (keysPressed[0] && !keysPressed[1]) {
            if (keysPressed[2] && !keysPressed[3]) {
                direction = Direction.UP_LEFT;
            } else if (keysPressed[3] && !keysPressed[2]) {
                direction = Direction.UP_RIGHT;
            } else {
                direction = Direction.UP;
            }
        } else if (keysPressed[1] && !keysPressed[0]) {
            if (keysPressed[2] && !keysPressed[3]) {
                direction = Direction.DOWN_LEFT;
            } else if (keysPressed[3] && !keysPressed[2]) {
                direction = Direction.DOWN_RIGHT;
            } else {
                direction = Direction.DOWN;
            }
        } else if (keysPressed[2] && !keysPressed[3]) {
            direction = Direction.LEFT;
        } else if (keysPressed[3] && !keysPressed[2]) {
            direction = Direction.RIGHT;
        } else {
            direction = Direction.NONE;
        } // up down left right and diagonal checks

        x += direction.getX() * speed;
        y += direction.getY() * speed;

        if (x < 0) {
            x = 0;
        } else if (x > MainGame.WIDTH - SIZE) {
            x = MainGame.WIDTH - SIZE;
        }
        if (y < 0) {
            y = 0;
        } else if (y > MainGame.HEIGHT - SIZE) {
            y = MainGame.HEIGHT - SIZE;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keysPressed[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                keysPressed[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                keysPressed[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                keysPressed[3] = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keysPressed[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                keysPressed[1] = false;
                break;
            case KeyEvent.VK_LEFT:
                keysPressed[2] = false;
                break;
            case KeyEvent.VK_RIGHT:
                keysPressed[3] = false;
                break;
            default:
                break;
        }
    }
}
