import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ExtendedCrosses extends JFrame{
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JButton button4;
    public JButton button5;
    public JButton button6;
    public JButton button7;
    public JButton button8;
    public JButton button9;
    public JButton button10;
    public JButton button11;
    public JButton button12;
    public JButton button13;
    public JButton button14;
    public JButton button15;
    public JButton button16;
    public JPanel panel1;
    public JLabel label1;
    private JButton CLOSEButton;
    private Point initialClick;
    public void lockInput()
    {
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);
        button7.setEnabled(false);
        button8.setEnabled(false);
        button9.setEnabled(false);
        button10.setEnabled(false);
        button11.setEnabled(false);
        button12.setEnabled(false);
        button13.setEnabled(false);
        button14.setEnabled(false);
        button15.setEnabled(false);
        button16.setEnabled(false);
    }
    ExtendedCrosses()
    {
        setUndecorated(true);
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setVisible(true);
        panel1.setVisible(true);
        UIManager.getDefaults().put("Button.disabledText", Color.white);
        button1.addActionListener(new MyButtonActionListener(1));
        button2.addActionListener(new MyButtonActionListener(2));
        button3.addActionListener(new MyButtonActionListener(3));
        button4.addActionListener(new MyButtonActionListener(4));
        button5.addActionListener(new MyButtonActionListener(5));
        button6.addActionListener(new MyButtonActionListener(6));
        button7.addActionListener(new MyButtonActionListener(7));
        button8.addActionListener(new MyButtonActionListener(8));
        button9.addActionListener(new MyButtonActionListener(9));
        button10.addActionListener(new MyButtonActionListener(10));
        button11.addActionListener(new MyButtonActionListener(11));
        button12.addActionListener(new MyButtonActionListener(12));
        button13.addActionListener(new MyButtonActionListener(13));
        button14.addActionListener(new MyButtonActionListener(14));
        button15.addActionListener(new MyButtonActionListener(15));
        button16.addActionListener(new MyButtonActionListener(16));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        CLOSEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client.socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.exit(0);
            }
        });
    }
}
