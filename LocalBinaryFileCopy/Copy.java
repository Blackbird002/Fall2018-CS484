
/*
Riad Shash (Ray)
CSCI 484
Copy a binary file assignment

This program copies a binary file using Java FileInputStream
and FileOutputStream classes.
*/

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import javax.swing.JFileChooser;

public class Copy {

    public static void main(String[] args) {

        String sourceDirectory = null;
        String copyDirectory = null;

        // Uses Java's JFileChooser to select a file to copy
        JFileChooser fileChooser = new JFileChooser();

        // Sets the title of the file dialog window
        fileChooser.setDialogTitle("Select a file to copy");

        int result = fileChooser.showOpenDialog(null);

        // Checks if the user pressed OK/yes
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            sourceDirectory = selectedFile.getAbsolutePath();
        } else {
            // Exit the function and end program
            return;
        }

        fileChooser.setDialogTitle("Select a destination folder");

        // Checks if the user pressed OK/yes
        result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected destination: " + selectedFile.getAbsolutePath());
            copyDirectory = selectedFile.getAbsolutePath();
        } else {
            // Exit the function and end program
            return;
        }

        try {
            // create FileInputStream object for source file
            FileInputStream source = new FileInputStream(sourceDirectory);

            // create FileOutputStream object for destination file
            FileOutputStream newFile = new FileOutputStream(copyDirectory);

            // An array of 512 bytes (A byte block) - can be changed
            byte[] B = new byte[512];
            int numOfBytes = 0;

            System.out.println("Copying file...");

            // read bytes from source file
            // Check if no more data to write (-1)
            while ((numOfBytes = source.read(B)) != -1) {
                // Wrtie the array of bytes to the new file
                newFile.write(B, 0, numOfBytes);
            }

            System.out.println("File copied!");

            // close the file streams
            source.close();
            newFile.close();

        } catch (FileNotFoundException fnf) {
            System.out.println("Source file not found :" + fnf);
        } catch (IOException ioe) {
            System.out.println("Error while copying file :" + ioe);
        }
    }
}
