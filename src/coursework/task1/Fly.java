package coursework.task1;

public class Fly implements MoveStrategy {
    @Override
    public String move(Point from, Point to) {
        return String.format("flies from %s to %s (distance: %.1f)", from, to, from.distanceTo(to));
    }
}
