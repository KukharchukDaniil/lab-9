
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerUI extends JFrame {
    public JTextArea textArea1;
    private JPanel panel1;

    ServerUI()
    {
        setContentPane(panel1);
        panel1.setForeground(Color.RED);
        textArea1.setEditable(false);
        setVisible(true);
        setSize(500,300);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }

            private void onCancel() {
                System.err.println("Server is closed");
                System.exit(2);
            }
        });
//        textArea1.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                textArea1.append("\n");
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                textArea1.append("\n");
//            }
//        });
    }

    public static void main(String[] args) {

    }
}
