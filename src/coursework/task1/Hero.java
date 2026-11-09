package coursework.task1;

public class Hero {
    private final String name;
    private Point position;
    private MoveStrategy strategy;

    public Hero(String name, Point start, MoveStrategy strategy) {
        this.name = name;
        this.position = start;
        this.strategy = strategy;
    }

    public void setStrategy(MoveStrategy strategy) {
        this.strategy = strategy;
    }

    public String move(Point target) {
        String action = name + ": " + strategy.move(position, target);
        position = target;
        return action;
    }

    public Point getPosition() {
        return position;
    }
}
