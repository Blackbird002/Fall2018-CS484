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
            //Used to get the stream of data from client
            InputStream inStream;

            //Used to create the copied file on this machine
            FileOutputStream outputFile;
            while (true) {
                /*Listens for a connection to be made to this socket and accepts it. 
                The method blocks until a connection is made.
                */
                Socket socket = listener.accept();
                if(socket.isConnected()){
                    try {
                        System.out.println("Connected to client IP: " + socket.getRemoteSocketAddress().toString());
                        inStream = socket.getInputStream();
    
                        //Create the file
                        outputFile = new FileOutputStream("CopiedFile.jpg");
                        
                        byte[] bytes = new byte[1024];
    
                        int count;
                        
                        /*
                        Reads some number of bytes from the input stream and stores them into the buffer array. 
                        Writes bytes from the specified byte array starting at offset off to this file output stream.
                        */
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
        }
        finally {
            //Always executes when try block exits
            listener.close();
            
        }
    }
}