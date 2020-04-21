import java.io.*;
import java.net.*;

public class SimpleServer {

    public static void main(String args[]) {
        int port = 2002;
        
        try {
            ServerSocket serverSocket= new ServerSocket(port);
            Socket socket = serverSocket.accept();
            InputStream inputStream= socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            TestObject testobject = (TestObject) objectInputStream.readObject();

            if (testobject != null) {
                System.out.println(testobject.id);
            }

            System.out.println((String) objectInputStream.readObject());
            inputStream.close();
            socket.close();
            serverSocket.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
