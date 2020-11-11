import com.sun.org.apache.xml.internal.security.algorithms.MessageDigestAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client {
    public static Socket socket;
    public static ObjectOutputStream bos;
    public static ObjectInputStream bis;
    public static String address;
    public static int nSize;
    static String port;
    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
        while(!login.bOkFlag)
        {
            System.out.print("");
        }
        try {
            socket = new Socket(address,Integer.parseInt( port));
        } catch (IOException e) {
            System.err.println("Can't connect to the server");
            System.exit(4);
        }
        String str;
        try {



            System.out.println("Success");
            bos = new ObjectOutputStream(socket.getOutputStream());
            bis = new ObjectInputStream(socket.getInputStream());
            int size = bis.readInt();
            switch (size)
            {
                case 3:
                    CrossesProc();
                    break;
                case 4:
                    ExtendedCrossesProc();
                    break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void CrossesProc() throws IOException, ClassNotFoundException {
        Integer point = bis.readInt();
        Crosses s =  new Crosses();
        s.label1.setText(point == 1? "X":"0");
        Object obj;
        Message msg;
        s.pack();
        int flag = -1;
        while(!socket.isClosed()&& flag!=0) {
            flag--;
            obj = bis.readObject();
            msg = (Message)obj;
            System.out.println(msg.CODE);

            switch (msg.CODE)
            {

                case 1:
                    s.button1.setText(msg.user == 1? "X":"0");
                    s.button1.setEnabled(false);
                    s.button1.updateUI();
                    break;
                case 2:
                    s.button2.setText(msg.user == 1? "X":"0");
                    s.button2.setEnabled(false);
                    s.button1.updateUI();
                    break;
                case 3:
                    s.button3.setText(msg.user == 1? "X":"0");
                    s.button3.setEnabled(false);
                    break;
                case 4:
                    s.button4.setText(msg.user == 1? "X":"0");
                    s.button4.setEnabled(false);
                    break;
                case 5:
                    s.button5.setText(msg.user == 1? "X":"0");
                    s.button5.setEnabled(false);
                    break;
                case 6:
                    s.button6.setText(msg.user == 1? "X":"0");
                    s.button6.setEnabled(false);
                    break;
                case 7:
                    s.button7.setText(msg.user == 1? "X":"0");
                    s.button7.setEnabled(false);
                    break;
                case 8:
                    s.button8.setText(msg.user == 1? "X":"0");
                    s.button8.setEnabled(false);
                    break;
                case 9:
                    s.button9.setText(msg.user == 1? "X":"0");
                    s.button9.setEnabled(false);
                    break;
                case 17:

                    s.label1.setText("Victory");
                    flag = 1;
//                        JDialog jd1 = new JDialog(s,"You have won!");
//                        jd1.setSize(200,200);
//                        jd1.setVisible(true);
//                        s.add(jd1);
                    break;
                case 18:
                    s.label1.setText("Defeat");
                    flag = 1;
//                        JDialog jd2 = new JDialog(s,"You have lose!");
//                        jd2.setSize(200,200);
//                        jd2.setVisible(true);
//                        s.add(jd2);
                    break;
                case -1:System.out.println("S");
                    break;
            }
        }
    }
    public static void ExtendedCrossesProc() throws IOException, ClassNotFoundException {
        int point = bis.readInt();
        ExtendedCrosses s =  new ExtendedCrosses();
        s.label1.setText(point == 1? "X":"0");
        s.pack();
        Message msg;
        Object obj;
        int flag = -1;
        while(!socket.isClosed()&& flag!=0) {
            flag--;
            obj = bis.readObject();
            msg = (Message)obj;
            System.out.println(msg.CODE);

            switch (msg.CODE)
            {
                case 1:
                    s.button1.setText(msg.user == 1? "X":"0");
                    s.button1.setEnabled(false);
                    s.button1.updateUI();
                    break;
                case 2:
                    s.button2.setText(msg.user == 1? "X":"0");
                    s.button2.setEnabled(false);
                    s.button1.updateUI();
                    break;
                case 3:
                    s.button3.setText(msg.user == 1? "X":"0");
                    s.button3.setEnabled(false);
                    break;
                case 4:
                    s.button4.setText(msg.user == 1? "X":"0");
                    s.button4.setEnabled(false);
                    break;
                case 5:
                    s.button5.setText(msg.user == 1? "X":"0");
                    s.button5.setEnabled(false);
                    break;
                case 6:
                    s.button6.setText(msg.user == 1? "X":"0");
                    s.button6.setEnabled(false);
                    break;
                case 7:
                    s.button7.setText(msg.user == 1? "X":"0");
                    s.button7.setEnabled(false);
                    break;
                case 8:
                    s.button8.setText(msg.user == 1? "X":"0");
                    s.button8.setEnabled(false);
                    break;
                case 9:
                    s.button9.setText(msg.user == 1? "X":"0");
                    s.button9.setEnabled(false);
                    break;
                case 10:
                    s.button10.setText(msg.user == 1? "X":"0");
                    s.button10.setEnabled(false);
                    break;
                case 11:
                    s.button11.setText(msg.user == 1? "X":"0");
                    s.button11.setEnabled(false);
                    break;
                case 12:
                    s.button12.setText(msg.user == 1? "X":"0");
                    s.button12.setEnabled(false);
                    break;
                case 13:
                    s.button13.setText(msg.user == 1? "X":"0");
                    s.button13.setEnabled(false);
                    break;
                case 14:
                    s.button14.setText(msg.user == 1? "X":"0");
                    s.button14.setEnabled(false);
                    break;
                case 15:
                    s.button15.setText(msg.user == 1? "X":"0");
                    s.button15.setEnabled(false);
                    break;
                case 16:
                    s.button16.setText(msg.user == 1? "X":"0");
                    s.button16.setEnabled(false);
                    break;
                case 18:

                    s.label1.setText("Defeat");
                    flag = 1;
//                        JDialog jd1 = new JDialog(s,"You have won!");
//                        jd1.setSize(200,200);
//                        jd1.setVisible(true);
//                        s.add(jd1);
                    break;
                case 17:
                    s.label1.setText("Victory");
                    flag = 1;
//                        JDialog jd2 = new JDialog(s,"You have lose!");
//                        jd2.setSize(200,200);
//                        jd2.setVisible(true);
//                        s.add(jd2);
                    break;
                case -1:System.out.println("S");
                    break;
            }
        }
    }
}
