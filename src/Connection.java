import java.io.*;
import java.net.Socket;
import java.util.Date;

public  class Connection extends Thread{
    public  byte ID;
    public Socket socket;
    public static int plCounter = 0;
    public Connection(Socket s)
    {
        System.out.println(s.getInetAddress().getHostName() + " connected to the server");
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
            while (!socket.isClosed()) {
                int code = (int) Server.connectionStreams.get(ID).ois.readObject();
                if (ID != Server.lastPlayer) {
                    System.out.println(ID + ":" + code);
                    Server.lastPlayer = ID;
                    Server.messages.add(new Message(code, ID));
                }

                if (!Server.messages.isEmpty()) {
                    Message msg = Server.messages.get(Server.messages.size()-1);
                   if(msg.CODE <= Server.nFieldSize*Server.nFieldSize) {
                        Server.field[(msg.CODE - 1) / Server.nFieldSize][(msg.CODE - 1) % Server.nFieldSize] = msg.user;
                        synchronized (Server.connections) {
                            for (Connection d :
                                    Server.connections) {
                                Server.connectionStreams.get(d.ID).oos.writeObject(msg);
                                Server.connectionStreams.get(d.ID).oos.flush();
                            }
                        }
                  }else
                   {

                           Server.playAgain += msg.CODE;
                           plCounter++;
                           if (Server.playAgain == 44)
                               for (ConnectionStream c : Server.connectionStreams
                               ) {
                                   c.oos.writeInt(44);
                                   c.oos.flush();
                                   System.out.println("check");
                               }
                           if(plCounter==2){
                               Server.bPlayAgainSet = true;
                               plCounter = 0;
                           }
                       }
                   }

                }

        } catch (Exception e) {
            System.err.println(socket.getInetAddress().getHostName() + " has disconnected");
            Server.bSomeoneDisconnected = true;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}