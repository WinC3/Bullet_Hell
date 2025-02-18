
public enum Direction { // remember swing coordinates start from top left corner and y increases
                        // downwards
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),

    UPLEFT(-Math.sqrt(2) / 2, -Math.sqrt(2) / 2),
    UPRIGHT(Math.sqrt(2) / 2, -Math.sqrt(2) / 2),
    DOWNLEFT(-Math.sqrt(2) / 2, Math.sqrt(2) / 2),
    DOWNRIGHT(Math.sqrt(2) / 2, Math.sqrt(2) / 2);

    private double x;
    private double y;

    Direction(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
