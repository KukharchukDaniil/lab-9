import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Server {
    private static ServerSocket serverSocket;
    public static int PORT = 5523;
    private static int counter = 0;
    static final byte[][] field = new byte[3][3];
    public static ArrayList<Message> messages = new ArrayList<Message>();
    public static ArrayList<ConnectionStream> connectionStreams = new ArrayList<ConnectionStream>();
    public static byte lastPlayer = -1;
    public static ArrayList<Connection> connections = new ArrayList<Connection>();

    private static boolean checkField() {
        for (int i = 0; i < 3; i++) {
            if ((field[i][0] == field[i][1] && field[i][1] == field[i][2]) && field[i][0] != 3) {
                return true;
            }
            ;
            if ((field[0][i] == field[1][i] && field[1][i] == field[2][i]) && field[0][i] != 3) {
                return true;
            }
            ;
        }
        if (((field[0][0] == field[1][1] && field[1][1] == field[2][2]) || (field[0][2] == field[1][1] && field[1][1] == field[2][0])) && field[1][1] != 3) {
            return true;
        }
        ;
        return false;
    }

    public static void main(String[] args) throws IOException {
        try {
            for (byte[] s : field
            ) {

                Arrays.fill(s, (byte) 3);
            }
            serverSocket = new ServerSocket(PORT, 2);
            try {
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
                while (true) {
                    synchronized (connections) {
                        if (checkField()) {
                            for (int i = 0; i < connectionStreams.size(); i++) {
                                connectionStreams.get(i).oos.writeObject(new Message(connections.get(i).ID == lastPlayer ? 15 : 16, lastPlayer));
                                connectionStreams.get(i).oos.flush();
                            }
                            break;
                        }
                    }
//
                }
            } catch (Exception e) {
            }
        } finally {
            serverSocket.close();
        }

    }
}
