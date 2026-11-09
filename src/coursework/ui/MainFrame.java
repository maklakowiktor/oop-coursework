package coursework.ui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import java.awt.CardLayout;

public class MainFrame extends JFrame {
    private static final String[] TASKS = {"1 strategy", "2 annotations", "3 translator", "4 stream api"};

    public MainFrame() {
        super("oop coursework");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        CardLayout cards = new CardLayout();
        JPanel content = new JPanel(cards);
        content.add(new StrategyPanel(), TASKS[0]);
        content.add(new AnnotationPanel(), TASKS[1]);
        content.add(new TranslatorPanel(), TASKS[2]);
        content.add(new StreamPanel(), TASKS[3]);

        JList<String> list = new JList<>(TASKS);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        list.addListSelectionListener(e -> cards.show(content, list.getSelectedValue()));
        list.setSelectedIndex(0);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(list), content);
        split.setDividerLocation(160);
        add(split);

        setSize(900, 560);
        setLocationRelativeTo(null);
    }
}
