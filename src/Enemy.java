public class Enemy extends Entity {
    public static final int SIZE = 30; // in pixels
    private static final int INITIAL_SPEED = 5;

    public Enemy(int x, int y, int health) {
        super(x, y, health, INITIAL_SPEED, SIZE);
    }
}
