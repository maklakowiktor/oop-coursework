package coursework.task1;

public class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point other) {
        return Math.hypot(other.x - x, other.y - y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
