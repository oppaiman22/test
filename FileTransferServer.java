import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FileTransferServer {
    public static void main(String[] args) throws Exception {
        //InitinternetAddresslize Sockets
        ServerSocket serverSocket = new ServerSocket(5000);
        Socket socket = serverSocket.accept();

        //Specify the file
        File fileContainer = new File("tes1.txt");
        FileInputStream fileInputStream = new FileInputStream(fileContainer);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        //Get socket's output stream
        OutputStream outputStream = socket.getOutputStream();

        //Read File Contents into contents array 
        byte[] contents;
        long fileLength = fileContainer.length();
        long current = 0;

        while (current != fileLength) {
            int size = 10000;
            if (fileLength - current >= size) {
                current += size;
            } else {
                size = (int) (fileLength - current);
                current = fileLength;
            }
            contents = new byte[size];
            bufferedInputStream.read(contents, 0, size);
            outputStream.write(contents);
            System.out.print("Sending file ... " + (current * 100) / fileLength + "% complete!");
        }

        outputStream.flush();
        //File transfer done. Close the socket connection!
        bufferedInputStream.close();
        serverSocket.close();
        socket.close();
        System.out.println("File sent succesfully!");
    }
}
