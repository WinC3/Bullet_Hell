import java.awt.Graphics;
import java.awt.Color;

public class Bullet {
    public static final int NORMAL_BULLET = 0;
    public static final int BIGGER_BULLET = 1;

    private double x;
    private double y;
    private Direction direction;
    private int speed = 10;
    private int damage = 10;
    private int length = 10;
    private int width = 5;

    public Bullet(double x, double y, Direction direction, int bulletType) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        switch (bulletType) {
            case NORMAL_BULLET:
                this.speed = 10;
                this.damage = 10;
                this.length = 10;
                this.width = 3;
                break;
            case BIGGER_BULLET:
                this.speed = 5;
                this.damage = 20;
                this.length = 15;
                this.width = 5;
                break;
            default:
                break;
        }
    }

    public void move() {
        x += direction.getX() * speed;
        y += direction.getY() * speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, width, length);
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
