public class Entity {

    protected int size;
    protected int x;
    protected int y;
    protected int health = 100;
    protected int speed = 5;

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

    public void shoot(int speed, int damage, int bulletType) {
        // IMPLEMENT LATERRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
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
