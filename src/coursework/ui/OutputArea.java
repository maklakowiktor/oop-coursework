package coursework.ui;

import javax.swing.JTextArea;
import java.awt.Font;

public class OutputArea extends JTextArea {
    public OutputArea() {
        setEditable(false);
        setLineWrap(true);
        setWrapStyleWord(true);
        setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
    }

    public void println(String line) {
        append(line + "\n");
        setCaretPosition(getDocument().getLength());
    }
}
