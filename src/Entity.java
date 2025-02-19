import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Entity {

    protected int size;
    protected double x;
    protected double y;
    protected int health = 100;
    protected double speed = 5;

    public Entity(int x, int y, int health, int speed, int size) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.speed = speed;
        this.size = size;
    }

    public void move(Direction direction) {
        x += direction.getX() * speed;
        y += direction.getY() * speed;
    }

    public List<Bullet> shoot(int speed, int damage, int bulletType) {
        // IMPLEMENT LATERRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
        return new ArrayList<>();
    }

    public void draw(Graphics g) {
        // TO BE OVERRIDEN
    }

    public void stopUpdates() {
        // TO BE OVERRIDEN
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public int takeDamage(int damage) {
        health -= damage;
        return health;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

}
