import java.awt.*;

public class UFOEnemy extends Enemy {
    public static final int WIDTH = Enemy.SIZE;
    public static final int LENGTH = 3 * Enemy.SIZE / 4;

    private int pivotX;
    private int pivotY;
    private int period = 600; // arbitrary units indicating how fast the UFO moves
    private int tFromPeriod = (int) (Math.random() * period); // start at random part of path

    public UFOEnemy(int x, int y, int health, MainGame mainGame, AttackPattern attackPattern) {
        super(x, y, health, mainGame, attackPattern);
        pivotX = x;
        pivotY = y;

        move();
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int) x, (int) y, WIDTH, LENGTH); // enemy

        g.fillRect((int) x, (int) y - 6, SIZE, 3);
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y - 6, SIZE * health / 100, 3); // health bar

        g.setColor(Color.WHITE);
        g.drawOval((int) x, (int) y, WIDTH - 1, LENGTH - 1); // enemy outline
    }

    public void move() {
        x = pivotX + Math.cos(2 * Math.PI * tFromPeriod / period) * ((MainGame.WIDTH - WIDTH) / 2 - 30);
        y = pivotY + Math.sin(2 * Math.PI * tFromPeriod / period) * 100;
        tFromPeriod += 1;
        tFromPeriod %= period;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getLength() {
        return LENGTH;
    }
}
