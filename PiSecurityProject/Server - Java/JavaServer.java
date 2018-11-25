import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import java.io.IOException;
 
class JavaServer {
    public static void main(String args[]) throws Exception {
        String fromClient;
        String toClient;
 
        ServerSocket server = new ServerSocket(23456);
 
        boolean run = true;
        while(run) {
            System.out.println("Waiting for connection on port 23456...");
            Socket client = server.accept();
            System.out.println("New connection on port 23456 ...");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(),true);
 
            fromClient = in.readLine();

            String titleBar = "New Message From Raspberry Pi Security System!";
            JavaServer.infoBox(fromClient, titleBar);

            System.out.println("Message Received: " + fromClient + " END OF LINE\n");
        }
        System.exit(0);
    }

    //Static becasue main function is static
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}