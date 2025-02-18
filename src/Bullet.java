import java.awt.Graphics;
import java.awt.Color;

public class Bullet {
    public static final int NORMAL_BULLET = 0;
    public static final int LASER_BULLET = 1;

    private int x;
    private int y;
    private Direction direction;
    private int speed = 10;
    private int damage = 10;
    private int length = 10;
    private int width = 5;

    public Bullet(int x, int y, Direction direction, int damage, int length, int width) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.damage = damage;
        this.length = length;
        this.width = width;
    }

    public void move() {
        x += direction.getX() * speed;
        y += direction.getY() * speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, length);
    }

    public int getDamage() {
        return damage;
    }

    public boolean isColliding(Entity entity) {
        return x < entity.getX() + entity.getSize() && x > entity.getX() && y < entity.getY() + entity.getSize()
                && y > entity.getY();
    }

    public boolean isOffScreen() {
        return x < 0 || x > MainGame.WIDTH || y < 0 || y > MainGame.HEIGHT;
    }
}
