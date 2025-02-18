import java.util.List;

public class Bullet {
    public static final int NORMAL_BULLET = 0;
    public static final int LASER_BULLET = 1;

    private int x;
    private int y;
    private Direction direction;
    private int speed = 10;
    private int damage = 10;
    private List<Entity> entities;

    public Bullet(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void move() {
        x += direction.getX() * speed;
        y += direction.getY() * speed;
    }

    public void getDamage() {

    }
}
