package coursework.task1;

public class Walk implements MoveStrategy {
    @Override
    public String move(Point from, Point to) {
        return String.format("walks from %s to %s (distance: %.1f)", from, to, from.distanceTo(to));
    }
}
