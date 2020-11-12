import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class PlayAgainDialog extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    public boolean flag = false;

    public PlayAgainDialog() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setVisible(true);
        setSize(300,200);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

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
        try {
            Client.bos.writeObject(22);
            Client.bos.flush();
            System.out.println("Play again code sent");
            flag = true;
            switch (Client.nSize)
            {
                case 3:
                    Client.crosses.dispose();
                    break;
                case 4:
                    Client.extendedCrosses.dispose();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        dispose();
    }

    private void onCancel() {
        try {

            Client.bos.writeObject(-1);
            Client.bos.flush();
            flag = true;
            Client.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dispose();
    }

}
