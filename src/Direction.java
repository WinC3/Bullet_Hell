
public enum Direction { // remember swing coordinates start from top left corner and y increases
                        // downwards
    NONE(0, 0),

    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),

    UP_LEFT(-Math.sqrt(2) / 2, -Math.sqrt(2) / 2),
    UP_RIGHT(Math.sqrt(2) / 2, -Math.sqrt(2) / 2),
    DOWN_LEFT(-Math.sqrt(2) / 2, Math.sqrt(2) / 2),
    DOWN_RIGHT(Math.sqrt(2) / 2, Math.sqrt(2) / 2);

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
