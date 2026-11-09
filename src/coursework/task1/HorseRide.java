package coursework.task1;

public class HorseRide implements MoveStrategy {
    @Override
    public String move(Point from, Point to) {
        return String.format("rides horse from %s to %s (distance: %.1f)", from, to, from.distanceTo(to));
    }
}
