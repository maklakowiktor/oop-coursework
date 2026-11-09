package coursework.ui;

import coursework.task4.StreamTasks;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class StreamPanel extends JPanel {
    private static final String[] METHODS = {
            "average", "upper with prefix", "squares of unique", "last element", "sum of even", "to map"
    };
    private static final String[] EXAMPLES = {
            "1 2 2 3 4 4 5", "apple banana cherry", "1 2 2 3 4 4 5", "red green blue", "1 2 3 4 5 6", "apple banana avocado"
    };

    private final JComboBox<String> methods = new JComboBox<>(METHODS);
    private final JTextField input = new JTextField(EXAMPLES[0], 22);
    private final JLabel hint = new JLabel();
    private final OutputArea output = new OutputArea();

    public StreamPanel() {
        super(new BorderLayout());

        JButton run = new JButton("run");
        JButton clear = new JButton("clear");
        methods.addActionListener(e -> selectMethod());
        run.addActionListener(e -> run());
        input.addActionListener(e -> run());
        clear.addActionListener(e -> output.setText(""));

        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(new JLabel("method:"));
        row.add(methods);
        row.add(new JLabel("input:"));
        row.add(input);
        row.add(run);
        row.add(clear);

        JPanel hintRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hintRow.add(hint);

        JPanel controls = new JPanel(new GridLayout(2, 1));
        controls.add(row);
        controls.add(hintRow);

        add(controls, BorderLayout.NORTH);
        add(new JScrollPane(output), BorderLayout.CENTER);
        selectMethod();
    }

    private void selectMethod() {
        int index = methods.getSelectedIndex();
        input.setText(EXAMPLES[index]);
        hint.setText(switch (index) {
            case 0, 2, 4 -> "integers separated by spaces or commas";
            default -> "strings separated by spaces or commas";
        });
    }

    private void run() {
        List<String> tokens = tokens(input.getText());
        try {
            Object result = switch (methods.getSelectedIndex()) {
                case 0 -> StreamTasks.average(integers(tokens));
                case 1 -> StreamTasks.toUpperWithPrefix(tokens);
                case 2 -> StreamTasks.squaresOfUnique(integers(tokens));
                case 3 -> StreamTasks.last(tokens);
                case 4 -> StreamTasks.sumOfEven(integers(tokens).stream().mapToInt(Integer::intValue).toArray());
                default -> StreamTasks.toMap(tokens);
            };
            output.println(methods.getSelectedItem() + " " + tokens + " -> " + result);
        } catch (NumberFormatException e) {
            output.println("error: input must contain integers only");
        } catch (NoSuchElementException e) {
            output.println("error: " + e.getMessage());
        }
    }

    private static List<String> tokens(String text) {
        return Arrays.stream(text.split("[,\\s]+"))
                .filter(token -> !token.isEmpty())
                .toList();
    }

    private static List<Integer> integers(List<String> tokens) {
        return tokens.stream()
                .map(Integer::parseInt)
                .toList();
    }
}
