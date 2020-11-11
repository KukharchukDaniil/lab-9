import javax.swing.*;
import java.awt.*;

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

    ExtendedCrosses()
    {
        setContentPane(panel1);
        setVisible(true);
        panel1.setVisible(true);
        label1.setFont(new Font("Comic Sans",Font.BOLD,20));
        label1.setForeground(Color.ORANGE);
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
    }
}
