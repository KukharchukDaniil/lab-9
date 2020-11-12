import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WaitingDialog extends JFrame {
    private JPanel contentPane;
    private JLabel label1;
    private JButton buttonOK;
    private JButton buttonCancel;

    public WaitingDialog(String str) {
        this.setUndecorated(true);
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setVisible(true);
        label1.setText(str);
        setLocation(new Point(getWidth(),getHeight()));
        setSize(300, 46);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        dispose();
        System.exit(0);
    }


}
