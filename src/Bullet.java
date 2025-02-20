import java.awt.Graphics;
import java.awt.Color;

public class Bullet {
    public static final int NORMAL_BULLET = 0;
    public static final int BIGGER_BULLET = 1;
    public static final int P_NORMAL_BULLET = 2;
    public static final int P_BIGGER_BULLET = 3;

    private double x;
    private double y;
    private Direction direction;
    private int speed = 10;
    private int damage = 10;
    private int length = 10;
    private int width = 5;

    public Bullet(double entityXMid, double entityYMid, Direction direction, int bulletType) {
        switch (bulletType) {
            case NORMAL_BULLET:
                this.speed = 2;
                this.damage = 5;
                this.length = 5;
                this.width = 3;
                break;
            case BIGGER_BULLET:
                this.speed = 4;
                this.damage = 10;
                this.length = 8;
                this.width = 5;
                break;
            case P_NORMAL_BULLET:
                this.speed = 4;
                this.damage = 10;
                this.length = 7;
                this.width = 4;
                break;
            case P_BIGGER_BULLET:
                this.speed = 2;
                this.damage = 20;
                this.length = 15;
                this.width = 6;
                break;
            default:
                break;
        }
        this.x = entityXMid - this.width; // spawn in middle of entity
        this.y = entityYMid;
        this.direction = direction;
    }

    public void move() {
        x += direction.getX() * speed;
        y += direction.getY() * speed;
    }

    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, length);
        g.setColor(Color.BLACK);
        g.drawRect((int) x, (int) y, width - 1, length - 1);
    }

    public int getDamage() {
        return damage;
    }

    public boolean isColliding(Entity entity) {
        return x < entity.getX() + entity.getWidth() && x + width > entity.getX()
                && y < entity.getY() + entity.getLength()
                && y + length > entity.getY();
    }

    public boolean isOffScreen() {
        return x < 0 || x > MainGame.WIDTH || y < 0 || y > MainGame.HEIGHT;
    }
}
