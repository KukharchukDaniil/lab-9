import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Server {
    private static ServerSocket serverSocket;
    public static int PORT = 5523;
    private static int counter = 0;
    public static Integer nFieldSize;
    static byte[][] field;
    public static ArrayList<Message> messages = new ArrayList<Message>();
    public static ArrayList<ConnectionStream> connectionStreams = new ArrayList<ConnectionStream>();
    public static byte lastPlayer = -1;
    public static ArrayList<Connection> connections = new ArrayList<Connection>();
    public static Integer playAgain = 0;
    public static boolean bPlayAgainSet= false;
    public static boolean bSomeoneDisconnected = false;
    public static ServerUI serverUI;
    private static boolean checkField() {
        boolean bSuccess = true;
        for (int i = 0; i < nFieldSize; i++) {
                 bSuccess = true;
            for (int j = 0; j < nFieldSize-1; j++) { // проверка по строке
                if(field[i][j]== 3 || ((field[i][j] != field[i][j + 1]) )) {
                    bSuccess = false;
                }
            }
            if(bSuccess) return true;
            bSuccess = true;
            for (int j = 0; j < nFieldSize-1; j++) { // проверка по столбцу
                if(field[j][i]==3 || (field[j][i] != field[j + 1][i]))
                    bSuccess =  false;
            }
            if(bSuccess) return true;
        }
        bSuccess = true;
        for (int i = 0; i < nFieldSize - 1; i++) { // проверка по побочной диагонали
            if(field[i][nFieldSize - 1 - i] == 3 || field[i][nFieldSize - 1-i] != field[i+1][nFieldSize - 2 - i])
                bSuccess = false;
        }
            if(bSuccess)return true;

        bSuccess = true;
        for (int i = 0; i < nFieldSize-1; i++) { // проверка по главной диагонали
            if(field[i][i] == 3 || field[i][i] != field[i+1][i+1])
                bSuccess = false;
        }
        return bSuccess;
    }
    private static void initField() throws IOException {
        for (byte[] s : field
        ) {

            Arrays.fill(s, (byte) 3);
        }
        System.err.println("Field is initialized");
    }
    private static void getConnections() throws IOException {
            serverSocket = new ServerSocket(PORT, 2);
            serverUI.textArea1.append("Waiting for players\n");
        while (true) {
            Socket client = serverSocket.accept();
            connectionStreams.add(new ConnectionStream(client,counter));
            connectionStreams.get(counter).oos.writeInt(nFieldSize);
            connectionStreams.get(counter).oos.writeInt(counter);
            connections.add(new Connection(client));
            counter++;
            if (counter == 2) {
                break;
            }
        }
        for (ConnectionStream st :connectionStreams
        ) {
            st.oos.writeObject(new Message(0,(byte)st.ID));
        }
    }
    private static void gameProc() throws IOException {
        boolean flag;
        int msgs;
        while (!serverSocket.isClosed() && !bSomeoneDisconnected) {
            synchronized (connections) {
                flag = checkField();
                if ((msgs = messages.size()) == messages.size() && flag ) {
                    Server.serverUI.textArea1.append(connections.get(lastPlayer).ID + " have won\n");
                    for (int i = 0; i < connectionStreams.size(); i++) {
                        connectionStreams.get(i).oos.writeObject(new Message(connections.get(i).ID == lastPlayer ? 17 : 18, lastPlayer));
                        connectionStreams.get(i).oos.flush();
                    }
                    serverUI.textArea1.append("GAME OVER!\n");
                    lastPlayer = -1;
                    break;

                }else if(msgs == messages.size() && msgs >= nFieldSize*nFieldSize)
                    {
                        flag = true;
                        for (byte[] b:field) {
                            for (byte d:b) {
                                if(d==3){
                                    flag = false;
                                    break;
                                }
                            }
                        }
                        if(flag && !checkField()) {
                            for (int i = 0; i < connectionStreams.size(); i++) {
                                connectionStreams.get(i).oos.writeObject(new Message(connections.get(i).ID == lastPlayer ? 19 : 19, lastPlayer));
                                connectionStreams.get(i).oos.flush();
                            }
                            serverUI.textArea1.append("GAME OVER2!\n");
                            lastPlayer = -1;
                            break;
                        }
                    }

                }
            }

        System.out.println(playAgain);
    }
    public static void main(String[] args) throws IOException {
        ServerFieldInitializer serverFieldInitializer = new ServerFieldInitializer();
        serverFieldInitializer.setLocationRelativeTo(null);
        while(true)
        {
            System.out.print("");
            if(serverFieldInitializer.flag)
                break;
        }
        serverUI = new ServerUI();
        serverUI.setLocationRelativeTo(null);
        try {

            field = new byte[nFieldSize][nFieldSize];
            serverUI.textArea1.append("Field size is " +nFieldSize + "\n");
            getConnections();
            do {
                bPlayAgainSet = false;
                playAgain = 0;
                lastPlayer = -1;
                messages.clear();
                serverUI.textArea1.append("Game is started\n");
                initField();
                try {
                    gameProc();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (bSomeoneDisconnected)break;
                while(!bPlayAgainSet)System.out.print("");
            }while(playAgain == 44);
        } finally {
            serverSocket.close();
            serverUI.textArea1.append("Server is closed\n");
        }
    }
}
