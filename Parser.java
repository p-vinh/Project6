
// Reads and parses each line of the input file

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

public class Parser {
    /**
     * Writes to a Hack .hack file
     * @param fileName the name of the file to write to
     * @return true if the file was written to successfully, false otherwise
     */
    public boolean writeFile(String fileName) {
        return false;
    }

    /**
     * Reads from a Hack .asm file and returns a list of strings
     * @param args the name of the file to read from
     * 
     * @return a list of strings representing the lines of the file
     */
    public List<String> readFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return null;
    }
}
