public class Player extends Entity {
    public static int size = 30; // in pixels

    private int x;
    private int y;
    private int health = 100;
    private int speed = 5;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(Direction direction) {
        x += direction.getX() * speed;
        y += direction.getY() * speed;
    }

    public void shoot(int speed, int damage, int bulletType) {

    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
