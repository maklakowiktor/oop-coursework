package coursework.ui;

import coursework.task2.AnnotationInvoker;
import coursework.task2.Robot;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class AnnotationPanel extends JPanel {
    private final JTextField name = new JTextField("r2d2", 12);
    private final OutputArea output = new OutputArea();

    public AnnotationPanel() {
        super(new BorderLayout());

        JButton run = new JButton("invoke annotated");
        JButton clear = new JButton("clear");
        run.addActionListener(e -> run());
        clear.addActionListener(e -> output.setText(""));

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controls.add(new JLabel("robot name:"));
        controls.add(name);
        controls.add(run);
        controls.add(clear);

        add(controls, BorderLayout.NORTH);
        add(new JScrollPane(output), BorderLayout.CENTER);
    }

    private void run() {
        String robotName = name.getText().isBlank() ? "robot" : name.getText().trim();
        try {
            new AnnotationInvoker(output::println).invokeAnnotated(new Robot(robotName, output::println));
        } catch (ReflectiveOperationException e) {
            output.println("error: " + e.getMessage());
        }
        output.println("");
    }
}
