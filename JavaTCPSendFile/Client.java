import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

/**
 * A simple TCP client for recieving a file.
 */
public class Client {

    /**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the date server, then connects to it and displays the date that
     * it serves.
     */
    public static void main(String[] args) throws IOException {
        String serverAddress = JOptionPane.showInputDialog(
            "Enter IP Address of the machine to send file to: ");

        //Creates a sockets that uses port 9090
        Socket mysocket = new Socket(serverAddress, 9090);

        String sourceDirectory = null;

        // Uses Java's JFileChooser to select a file to copy
        JFileChooser fileChooser = new JFileChooser();

        // Sets the title of the file dialog window
        fileChooser.setDialogTitle("Select a file to copy");

        int result = fileChooser.showOpenDialog(null);
        // Checks if the user pressed OK/yes
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null, "Selected file: " + selectedFile.getAbsolutePath());
            sourceDirectory = selectedFile.getAbsolutePath();
        } else {
            // Exit the function and end program
            return;
        }

        byte[] bytes = new byte[1024];

        //Create the input and output streams
        InputStream inStream = new FileInputStream(sourceDirectory);
        OutputStream outStream = mysocket.getOutputStream();

        int count;
        while ((count = inStream.read(bytes)) > 0) {
            outStream.write(bytes, 0, count);
        }

        outStream.close();
        inStream.close();
        mysocket.close();

        JOptionPane.showMessageDialog(null, "File is sent!");
        System.exit(0);
    }
}