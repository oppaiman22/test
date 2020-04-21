import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

public class FileTransferClient {
    public static void main(String[] args) throws Exception {
        //Initialize socket
        InetAddress internetAddress = InetAddress.getLocalHost();
        Socket socket = new Socket(internetAddress, 5000);
        byte[] contents = new byte[10000];

        //Initialize the FileOutputStream to the output file's full path.
        FileOutputStream fileOutputStream = new FileOutputStream("input/tes1.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        InputStream inputStream = socket.getInputStream();

        //No of bytes read in one read() call
        int bytesRead = 0;

        while ((bytesRead = inputStream.read(contents)) != -1) {
            bufferedOutputStream.write(contents, 0, bytesRead);
        }

        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        socket.close();
        System.out.println("File saved successfully!");
    }
}
