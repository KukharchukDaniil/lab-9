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

    private static boolean checkField() {
        boolean bSuccess = true;
        for (int i = 0; i < nFieldSize; i++) {
                 bSuccess = true;
            for (int j = 0; j < nFieldSize-1; j++) {
                if(field[i][j]== 3 || ((field[i][j] != field[i][j + 1]) )) {
                    bSuccess = false;
                }
            }
            if(bSuccess) return true;
            for (int j = 0; j < nFieldSize-1; j++) {
                bSuccess = true;
                if(field[i][j]==3 || (field[i][j] != field[j + 1][i]))
                    bSuccess =  false;
            }
            if(bSuccess) return true;
        }
//                    if ((field[i][0] == field[i][1] && field[i][1] == field[i][2]) && field[i][0] != 3) {
//                        return true;
//                    }
        bSuccess = true;
        for (int i = 0; i < nFieldSize - 1; i++) {
            if(field[i][nFieldSize - 1 - i] == 3 || field[i][nFieldSize - 1-i] != field[i+1][nFieldSize - 2 - i])
                bSuccess = false;
        }
            if(bSuccess)return true;

        bSuccess = true;
        for (int i = 0; i < nFieldSize-1; i++) {
            if(field[i][i] == 3 || field[i][i] != field[i+1][i+1])
                bSuccess = false;
        }
        return bSuccess;
    }
    private static void initField() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        nFieldSize = Integer.parseInt( bufferedReader.readLine());
        field = new byte[nFieldSize][nFieldSize];
        for (byte[] s : field
        ) {

            Arrays.fill(s, (byte) 3);
        }
    }
    private static void getConnections() throws IOException {
            serverSocket = new ServerSocket(PORT, 2);
        while (true) {
            Socket client = serverSocket.accept();
            connectionStreams.add(new ConnectionStream(client,counter));
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
        while (!serverSocket.isClosed()) {
            synchronized (connections) {
                if (checkField()) {
                    for (int i = 0; i < connectionStreams.size(); i++) {
                        connectionStreams.get(i).oos.writeObject(new Message(connections.get(i).ID == lastPlayer ? 15 : 16, lastPlayer));
                        connectionStreams.get(i).oos.flush();
                    }

                    System.out.println("GAME OVER!");
                    break;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        try {
            initField();
            getConnections();
            try {
                gameProc();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } finally {
            serverSocket.close();
        }

    }
}
