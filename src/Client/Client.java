package Client;

import Server.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static Socket socket;
    public static ObjectOutputStream bos;
    public static ObjectInputStream bis;
    public static String address;
    public static Crosses crosses;
    public static ExtendedCrosses extendedCrosses;
    public static int nSize;
    static int point;
    static String port;
    public static void main(String[] args) {
        Login login = new Login();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        while(!login.bOkFlag)
        {
            System.out.print("");
        }
        try {
            socket = new Socket(address,Integer.parseInt( port));
        } catch (IOException e) {
            new WaitingDialog( "Can't connect to the server").setLocationRelativeTo(null);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.exit(4);
        }
        String str;
        try {



            System.out.println("Success");
            WaitingDialog dialog = new WaitingDialog("Connected. Waiting for second player");
            dialog.setLocationRelativeTo(null);
            bos = new ObjectOutputStream(socket.getOutputStream());
            bis = new ObjectInputStream(socket.getInputStream());
            nSize = bis.readInt();

            point = bis.readInt();
            dialog.dispose();
             do{
                 switch (nSize)
            {
                case 3:
                    CrossesProc();
                    break;
                case 4:
                    ExtendedCrossesProc();
                    break;
            }
             PlayAgainDialog playAgainDialog  = new PlayAgainDialog();
             playAgainDialog.setLocationRelativeTo(nSize == 3? crosses : extendedCrosses);
             playAgainDialog.setVisible(true);
             while (!playAgainDialog.flag){
                System.out.print("");
             };
             if(socket.isClosed())break;
             int check = bis.readInt();
             System.err.println(check);
             if (check != 44) {
              //   new WaitingDialog("Second player has disconnected");
                 System.exit(check);
             }
             System.out.println("Again");
             }while (!socket.isClosed());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Disconnected");
            System.exit(2);
        }
    }
    public static void CrossesProc() throws IOException, ClassNotFoundException {
        crosses =  new Crosses();
        crosses.setLocationRelativeTo(null);
        crosses.label1.setText(point == 1? "X":"0");
        Object obj;
        Message msg;
        crosses.pack();
        int flag = -1;
        while(!socket.isClosed()&& flag!=0) {
            flag--;
            obj = bis.readObject();
            msg = (Message)obj;
            System.out.println(msg.CODE);

            switch (msg.CODE)
            {

                case 1:
                    crosses.button1.setText(msg.user == 1? "X":"0");
                    crosses.button1.setEnabled(false);
                    crosses.button1.updateUI();
                    break;
                case 2:
                    crosses.button2.setText(msg.user == 1? "X":"0");
                    crosses.button2.setEnabled(false);
                    crosses.button1.updateUI();
                    break;
                case 3:
                    crosses.button3.setText(msg.user == 1? "X":"0");
                    crosses.button3.setEnabled(false);
                    break;
                case 4:
                    crosses.button4.setText(msg.user == 1? "X":"0");
                    crosses.button4.setEnabled(false);
                    break;
                case 5:
                    crosses.button5.setText(msg.user == 1? "X":"0");
                    crosses.button5.setEnabled(false);
                    break;
                case 6:
                    crosses.button6.setText(msg.user == 1? "X":"0");
                    crosses.button6.setEnabled(false);
                    break;
                case 7:
                    crosses.button7.setText(msg.user == 1? "X":"0");
                    crosses.button7.setEnabled(false);
                    break;
                case 8:
                    crosses.button8.setText(msg.user == 1? "X":"0");
                    crosses.button8.setEnabled(false);
                    break;
                case 9:
                    crosses.button9.setText(msg.user == 1? "X":"0");
                    crosses.button9.setEnabled(false);
                    break;
                case 17:

                    crosses.label1.setText("Victory");
                    flag = 0;
                    crosses.lockInput();
                    break;
                case 18:
                    crosses.label1.setText("Defeat");
                    flag = 0;
                    crosses.lockInput();
//                        JDialog jd2 = new JDialog(s,"You have lose!");
//                        jd2.setSize(200,200);
//                        jd2.setVisible(true);
//                        s.add(jd2);
                    break;
                case 19:
                    crosses.label1.setText("Draw");
                    flag = 0;
                    crosses.lockInput();
                    break;
                case -1:System.out.println("S");
                    break;
            }
        }
    }
    public static void ExtendedCrossesProc() throws IOException, ClassNotFoundException {
        extendedCrosses =  new ExtendedCrosses();
        extendedCrosses.setLocationRelativeTo(null);
        extendedCrosses.label1.setText(point == 1? "X":"0");
        extendedCrosses.pack();
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
                    extendedCrosses.button1.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button1.setEnabled(false);
                    extendedCrosses.button1.updateUI();
                    break;
                case 2:
                    extendedCrosses.button2.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button2.setEnabled(false);
                    extendedCrosses.button1.updateUI();
                    break;
                case 3:
                    extendedCrosses.button3.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button3.setEnabled(false);
                    break;
                case 4:
                    extendedCrosses.button4.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button4.setEnabled(false);
                    break;
                case 5:
                    extendedCrosses.button5.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button5.setEnabled(false);
                    break;
                case 6:
                    extendedCrosses.button6.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button6.setEnabled(false);
                    break;
                case 7:
                    extendedCrosses.button7.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button7.setEnabled(false);
                    break;
                case 8:
                    extendedCrosses.button8.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button8.setEnabled(false);
                    break;
                case 9:
                    extendedCrosses.button9.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button9.setEnabled(false);
                    break;
                case 10:
                    extendedCrosses.button10.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button10.setEnabled(false);
                    break;
                case 11:
                    extendedCrosses.button11.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button11.setEnabled(false);
                    break;
                case 12:
                    extendedCrosses.button12.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button12.setEnabled(false);
                    break;
                case 13:
                    extendedCrosses.button13.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button13.setEnabled(false);
                    break;
                case 14:
                    extendedCrosses.button14.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button14.setEnabled(false);
                    break;
                case 15:
                    extendedCrosses.button15.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button15.setEnabled(false);
                    break;
                case 16:
                    extendedCrosses.button16.setText(msg.user == 1? "X":"0");
                    extendedCrosses.button16.setEnabled(false);
                    break;
                case 18:

                    extendedCrosses.label1.setText("Defeat");
                    flag = 0;
                    extendedCrosses.lockInput();
//                        JDialog jd1 = new JDialog(extendedCrosses,"You have won!");
//                        jd1.setSize(200,200);
//                        jd1.setVisible(true);
//                        extendedCrosses.add(jd1);
                    break;
                case 17:
                    extendedCrosses.label1.setText("Victory");
                    flag = 0;
                    extendedCrosses.lockInput();
//                        JDialog jd2 = new JDialog(extendedCrosses,"You have lose!");
//                        jd2.setSize(200,200);
//                        jd2.setVisible(true);
//                        extendedCrosses.add(jd2);
                    break;
                case 19:
                    extendedCrosses.label1.setText("Draw");
                    flag = 0;
                    extendedCrosses.lockInput();
                    break;
                case -1:System.out.println("S");
                    break;
            }
        }
    }
}
