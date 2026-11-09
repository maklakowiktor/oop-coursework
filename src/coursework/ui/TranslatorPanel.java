package coursework.ui;

import coursework.task3.Dictionary;
import coursework.task3.FileReadException;
import coursework.task3.InvalidFileFormatException;
import coursework.task3.TextFile;
import coursework.task3.Translator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class TranslatorPanel extends JPanel {
    private final JLabel dictionaryLabel = new JLabel("dictionary: not loaded");
    private final JTextArea input = new JTextArea();
    private final OutputArea output = new OutputArea();
    private Dictionary dictionary = new Dictionary();

    public TranslatorPanel() {
        super(new BorderLayout());

        JButton openDictionary = new JButton("open dictionary");
        JButton openText = new JButton("open text");
        JButton translate = new JButton("translate");
        openDictionary.addActionListener(e -> chooseFile().ifPresent(this::loadDictionary));
        openText.addActionListener(e -> chooseFile().ifPresent(this::loadText));
        translate.addActionListener(e -> translate());

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controls.add(openDictionary);
        controls.add(openText);
        controls.add(translate);
        controls.add(dictionaryLabel);

        input.setLineWrap(true);
        input.setWrapStyleWord(true);
        input.setFont(output.getFont());
        JScrollPane inputPane = new JScrollPane(input);
        JScrollPane outputPane = new JScrollPane(output);
        inputPane.setBorder(BorderFactory.createTitledBorder("text"));
        outputPane.setBorder(BorderFactory.createTitledBorder("translation"));

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputPane, outputPane);
        split.setResizeWeight(0.5);

        add(controls, BorderLayout.NORTH);
        add(split, BorderLayout.CENTER);

        Path defaultDictionary = Path.of("dictionary.txt");
        if (Files.exists(defaultDictionary)) {
            loadDictionary(defaultDictionary);
        }
    }

    private Optional<Path> chooseFile() {
        JFileChooser chooser = new JFileChooser(Path.of("").toAbsolutePath().toFile());
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return Optional.of(chooser.getSelectedFile().toPath());
        }
        return Optional.empty();
    }

    private void loadDictionary(Path path) {
        try {
            dictionary = Dictionary.load(path);
            dictionaryLabel.setText("dictionary: " + path.getFileName() + " (" + dictionary.size() + " entries)");
            output.println("dictionary loaded: " + path);
        } catch (FileReadException | InvalidFileFormatException e) {
            output.println("error: " + e.getMessage());
        }
    }

    private void loadText(Path path) {
        try {
            input.setText(TextFile.read(path));
        } catch (FileReadException e) {
            output.println("error: " + e.getMessage());
        }
    }

    private void translate() {
        if (dictionary.size() == 0) {
            output.println("error: dictionary is not loaded");
            return;
        }
        output.setText("");
        output.println(new Translator(dictionary).translate(input.getText()));
    }
}
