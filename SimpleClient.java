import java.net.*;
import java.io.*;

public class SimpleClient {

    public static void main(String args[]) {
        try {
            Socket socket = new Socket("localhost", 2002);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            TestObject testObject = new TestObject(1, "object from client");
            objectOutputStream.writeObject(testObject);
            objectOutputStream.writeObject(new String("another object from the client"));
            objectOutputStream.close();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
