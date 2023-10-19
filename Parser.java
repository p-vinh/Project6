
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
     * Encapsulates access to the input code. Reads an assembly language command, parses it, and provides convenient access to the command’s components (fields and symbols).
     * In addition, removes all white space and comments.
     * @param fileName the name of the file to read from
     */
    private Scanner scanner;

    private enum CommandType {
        A_COMMAND, C_COMMAND, L_COMMAND
    }

    private CommandType currCommandType;

    public Parser(File fileName) {
        try {
            scanner = new Scanner(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        }
    }

    public boolean hasMoreCommands() {
        return scanner.hasNextLine();
    }

    /**
     * Reads the next command from the input and makes it the current command. Should be called only if hasMoreCommands() is true. Initially there is no current command.
     */
    public void advance() {
        String currLine = scanner.nextLine();
        currLine = currLine.replaceAll("\\s+", "");
        if (currLine.startsWith("//") || currLine.isEmpty()) {
            advance();
        } else if (currLine.startsWith("@")) {
            currCommandType = CommandType.A_COMMAND;
        } else if (currLine.startsWith("(")) {
            currCommandType = CommandType.L_COMMAND;
        } else {
            currCommandType = CommandType.C_COMMAND;
        }
    }

    /**
     * Returns the type of the current command:
     *          A_COMMAND for @Xxx where Xxx is either a symbol or a decimal number
     *          C_COMMAND for dest=comp;jump
     *          L_COMMAND (actually, pseudocommand) for (Xxx) where Xxx is a symbol.
     * @return the type of the current command
     */
    public CommandType commandType() {
        return currCommandType;
    }

    /**
     * Returns the symbol or decimal Xxx of the current command @Xxx or (Xxx). Should be called only when commandType() is A_COMMAND or L_COMMAND.
     * @return the symbol or decimal Xxx of the current command
     */
    public String symbol() {
        return null;
    }


    /**
     * Returns the dest mnemonic in the current C-command (8 possibilities). Should be called only when commandType() is C_COMMAND.
     * @return the dest mnemonic in the current C-command
     */
    public String dest() {
        return null;
    }

    /**
     * Returns the comp mnemonic in the current C-command (28 possibilities). Should be called only when commandType() is C_COMMAND.
     * @return the comp mnemonic in the current C-command
     */
    public String comp() {
        return null;
    }

    /**
     * Returns the jump mnemonic in the current C-command (8 possibilities). Should be called only when commandType() is C_COMMAND.
     * @return the jump mnemonic in the current C-command
     */
    public String jump() {
        return null;
    }


}
