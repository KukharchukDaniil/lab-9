package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ServerFieldInitializer extends JFrame {
    private JButton chooseFieldSizeButton;
    private JComboBox comboBox1;
    private JPanel panel1;
    public boolean flag = false;
    private Point initialClick;
    ServerFieldInitializer() {

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
        this.setUndecorated(true);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        getRootPane().setDefaultButton(chooseFieldSizeButton);
        setSize(145, 81);
        setContentPane(panel1);
        panel1.setVisible(true);
        setVisible(true);
        panel1.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        chooseFieldSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }
    private void onOK() {
        Server.nFieldSize = comboBox1.getSelectedIndex() == 0 ? 3 : 4;
        flag = true;
        dispose();
    }

    private void onCancel() {
        System.err.println("Game field initialization canceled");
        System.exit(3);
        dispose();
    }

}