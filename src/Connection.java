import java.io.*;
import java.net.Socket;
import java.util.Date;

public  class Connection extends Thread{
    public  byte ID;
    public Socket socket;
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
        try{
            super.run();
            while(!socket.isClosed())
            {
                    int code = (int) Server.connectionStreams.get(ID).ois.readObject();
                    if (ID != Server.lastPlayer) {
                        System.out.println(ID + ":" + code);

                        Server.lastPlayer = ID;
                        Server.messages.add(new Message(code, ID));
                    } else {
                    }
                        if (!Server.messages.isEmpty()) {
                            Message msg = Server.messages.get(Server.messages.size()-1);
                            Server.field[(msg.CODE - 1) / 3][(msg.CODE - 1) % 3] = msg.user;
                            synchronized (Server.connections) {
                                for (Connection d :
                                        Server.connections) {
                                    Server.connectionStreams.get(d.ID).oos.writeObject(msg);
                                    Server.connectionStreams.get(d.ID).oos.flush();
                                }
                            }

                }
            }


            } catch (Exception e) {
            System.out.println(socket.getInetAddress().getCanonicalHostName() + " has disconnected");
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}