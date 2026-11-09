package coursework.task2;

import java.util.function.Consumer;

public class Robot {
    private final String name;
    private final Consumer<String> output;

    public Robot(String name, Consumer<String> output) {
        this.name = name;
        this.output = output;
    }

    @Repeat(5)
    public void greet(String person) {
        output.accept(name + ": hello, " + person);
    }

    public int add(int a, int b) {
        return a + b;
    }

    public void report(String task, double progress, boolean done) {
        output.accept(String.format("%s: %s %.1f%% (done: %b)", name, task, progress, done));
    }

    @Repeat(2)
    protected void beep(int times) {
        output.accept(name + ": " + "beep ".repeat(times).trim());
    }

    @Repeat(3)
    protected void moveTo(double x, double y) {
        output.accept(name + ": moves to (" + x + ", " + y + ")");
    }

    protected void charge(int percent) {
        output.accept(name + ": charged to " + percent + "%");
    }

    @Repeat(1)
    private void scan(String area, long radius, char mode) {
        output.accept(name + ": scans " + area + " in radius " + radius + " (mode: " + mode + ")");
    }

    @Repeat(2)
    private void log(StringBuilder buffer) {
        buffer.append("log;");
        output.accept(name + ": writes log, buffer = " + buffer);
    }

    private void selfDestruct() {
        output.accept(name + ": self destruct");
    }
}
