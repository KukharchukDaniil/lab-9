import java.io.*;
import java.net.Socket;
import java.util.Date;

public  class Connection extends Thread{
    public  byte ID;
    public Socket socket;
    public static int plCounter = 0;
    public Connection(Socket s)
    {
        Server.serverUI.textArea1.append(s.getInetAddress().getHostName() + " connected to the server\n");
        for (byte i = 0; i < 2; i++) {
            if(!Server.connections.isEmpty())
            for (Connection d:Server.connections
            ) {
                if(d.ID!=i){
                    ID = i;
                    socket = s;
                    start();
                    return;
                };
            }
            else{
                ID = i;
                socket = s;
                start();
                break;
            }
        }

    }

    @Override
    public void run() {
        try {
            super.run();
            int prevCode = -1;
            while (!socket.isClosed()) {
                int code = (int) Server.connectionStreams.get(ID).ois.readObject();
                synchronized (Server.messages){
                     if(code>Server.nFieldSize*Server.nFieldSize){
                         Server.playAgain+=code;
                         System.out.println("check");
                         plCounter++;
                         if (plCounter == 2) {
                             System.out.println("check");
                            for (ConnectionStream c : Server.connectionStreams
                            ) {
                                c.oos.writeInt(Server.playAgain);
                                c.oos.flush();
                            }
                            Server.bPlayAgainSet = true;
                            plCounter = 0;
                         }
                    }
                    if (ID != Server.lastPlayer) {
                        System.out.println(ID + ":" + code);
                        Server.lastPlayer = ID;
                        Server.messages.add(new Message(code, ID));
                    }
                }

                synchronized (Server.connections){
                    if (!Server.messages.isEmpty()) {
                        Message msg = Server.messages.get(Server.messages.size() - 1);
                        if (msg.CODE != prevCode && msg.CODE <= Server.nFieldSize * Server.nFieldSize) {
                            prevCode = msg.CODE;
                            Server.field[(msg.CODE - 1) / Server.nFieldSize][(msg.CODE - 1) % Server.nFieldSize] = msg.user;
                            synchronized (Server.connections) {
                                for (Connection d :
                                        Server.connections) {
                                    Server.connectionStreams.get(d.ID).oos.writeObject(msg);
                                    Server.connectionStreams.get(d.ID).oos.flush();
                                }
                            }
                        }
                    }
                }

            }

        } catch (Exception e) {
            Server.serverUI.textArea1.append(socket.getInetAddress().getHostName() + " has disconnected\n");
            Server.bSomeoneDisconnected = true;
            Server.bPlayAgainSet = true;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}