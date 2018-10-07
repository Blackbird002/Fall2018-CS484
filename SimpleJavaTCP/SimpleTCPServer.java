import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * A Simple TCP server that runs on port 9090.  When a client connects, it
 * sends the client the current date and time, then closes the
 * connection with that client.  
 */
public class SimpleTCPServer {

    /**
     * Runs the server.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        System.out.println("Simple TCP Server is runnig...");
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Hello, this is the Simple TCP Server! Today's date is: " + new Date().toString());
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