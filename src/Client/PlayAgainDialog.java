package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class PlayAgainDialog extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    public boolean flag = false;

    public PlayAgainDialog() {


        setLocationRelativeTo(Client.nSize== 3? Client.crosses: Client.extendedCrosses);
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

            Client.bos.writeObject(25);
            Client.bos.flush();
            System.out.println("Play again code sent");
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        dispose();
    }

}
