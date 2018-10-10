import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.stream.IntStream;

/**
 *  
 */
public class Server {

    /**
     * Runs the server.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        System.out.println("Simple TCP Server is runnig...");
        try {
            InputStream inStream;
            FileOutputStream outputFile;
            while (true) {
                Socket socket = listener.accept();
                try {
                    inStream = socket.getInputStream();

                    //Create the file
                    outputFile = new FileOutputStream("CopiedFile.jpg");
                    

                    byte[] bytes = new byte[1024];

                    int count;
                    while ((count = inStream.read(bytes)) > 0) {
                        outputFile.write(bytes, 0, count);
                    }
                    outputFile.close();
                    inStream.close();
                   
                } finally {
                    //Always executes when try block exits
                    socket.close();                    
                }
            }
        }
        finally {
            //Always executes when try block exits
            listener.close();
            
        }
    }
}