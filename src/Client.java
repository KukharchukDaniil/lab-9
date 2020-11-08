import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Client {
    public static Socket socket;
    public static ObjectOutputStream bos;
    public static ObjectInputStream bis;
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        try {
//            str = bufferedReader.readLine();
            socket = new Socket("localhost",5523);
            System.out.println("Success");
            bos = new ObjectOutputStream(socket.getOutputStream());
            bis = new ObjectInputStream(socket.getInputStream());
            Object obj = bis.readObject();
            Message msg = (Message)obj;
            Crosses s =  new Crosses();
            s.label1.setText(msg.user == 1? "X":"0");
            s.pack();

            while(!socket.isClosed()) {
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
                    case 16:

                        s.label1.setText("Defeat");
//                        JDialog jd1 = new JDialog(s,"You have won!");
//                        jd1.setSize(200,200);
//                        jd1.setVisible(true);
//                        s.add(jd1);
                        break;
                    case 15:
                        s.label1.setText("Victory");
//                        JDialog jd2 = new JDialog(s,"You have lose!");
//                        jd2.setSize(200,200);
//                        jd2.setVisible(true);
//                        s.add(jd2);
                        break;
                    case -1:System.out.println("S");
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
