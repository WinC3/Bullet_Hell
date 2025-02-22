public class TeleportingEnemy extends Enemy {
    private static final int DOMAIN = MainGame.WIDTH;
    private static final int RANGE = MainGame.HEIGHT / 3;

    private int period = 250; // arbitrary units
    private int tFromTeleport = 0;

    public TeleportingEnemy(int x, int y, int health, MainGame mainGame, AttackPattern attackPattern) {
        super(x, y, health, mainGame, attackPattern);
        move();
    }

    public void move() {
        if (tFromTeleport == 0) {
            x = Math.random() * DOMAIN;
            y = Math.random() * RANGE;
        }
        tFromTeleport++;
        tFromTeleport %= period;
    }
}
