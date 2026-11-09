package coursework.ui;

import coursework.task1.Fly;
import coursework.task1.Hero;
import coursework.task1.HorseRide;
import coursework.task1.MoveStrategy;
import coursework.task1.Point;
import coursework.task1.Walk;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class StrategyPanel extends JPanel {
    private final Hero hero = new Hero("Jason Statham", new Point(0, 0), new Walk());
    private final JComboBox<String> strategies = new JComboBox<>(new String[]{"walk", "horse ride", "fly"});
    private final JTextField x = new JTextField("3", 6);
    private final JTextField y = new JTextField("4", 6);
    private final OutputArea output = new OutputArea();

    public StrategyPanel() {
        super(new BorderLayout());

        JButton move = new JButton("move");
        strategies.addActionListener(e -> changeStrategy());
        move.addActionListener(e -> move());

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controls.add(new JLabel("strategy:"));
        controls.add(strategies);
        controls.add(new JLabel("x:"));
        controls.add(x);
        controls.add(new JLabel("y:"));
        controls.add(y);
        controls.add(move);

        add(controls, BorderLayout.NORTH);
        add(new JScrollPane(output), BorderLayout.CENTER);
        output.println("hero starts at " + hero.getPosition());
    }

    private void changeStrategy() {
        MoveStrategy strategy = switch (strategies.getSelectedIndex()) {
            case 1 -> new HorseRide();
            case 2 -> new Fly();
            default -> new Walk();
        };
        hero.setStrategy(strategy);
        output.println("strategy: " + strategies.getSelectedItem());
    }

    private void move() {
        try {
            Point target = new Point(Double.parseDouble(x.getText().trim()), Double.parseDouble(y.getText().trim()));
            output.println(hero.move(target));
        } catch (NumberFormatException e) {
            output.println("error: x and y must be numbers");
        }
    }
}
