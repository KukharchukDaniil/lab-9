import java.io.Serializable;

public class Message implements Serializable {
    public  int CODE;
    public  byte user;
    public Message(int code, byte user)
    {

        this.CODE = code;
        this.user = user;
    }
}
