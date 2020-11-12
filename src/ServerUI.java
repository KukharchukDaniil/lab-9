
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class ServerUI extends JFrame {
    public JTextArea textArea1;
    private JPanel panel1;
    private Point initialClick;
    ServerUI()
    {
        setUndecorated(true);
        setContentPane(panel1);
        panel1.setForeground(Color.RED);
        textArea1.setEditable(false);
        setVisible(true);
        setSize(500,300);
        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });
        panel1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                setLocation(X, Y);

            }
        });
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
