import sun.awt.WindowClosingListener;

import javax.swing.*;
import java.awt.event.*;

public class ServerFieldInitializer extends JFrame {
    private JButton chooseFieldSizeButton;
    private JComboBox comboBox1;
    private JPanel panel1;
    public boolean flag = false;

    ServerFieldInitializer() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        getRootPane().setDefaultButton(chooseFieldSizeButton);
        setSize(300, 120);
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