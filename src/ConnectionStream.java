import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionStream {
    public ObjectOutputStream oos;
    public ObjectInputStream ois;
    public int ID;
    public ConnectionStream(Socket s, int ID) throws IOException {
        oos = new ObjectOutputStream(s.getOutputStream());
        ois = new ObjectInputStream(s.getInputStream());
        this.ID = ID;
    }
}
