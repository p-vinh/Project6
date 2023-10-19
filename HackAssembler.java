import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class HackAssembler {

    private Parser parser;
    private Code code;
    private SymbolTable symbolTable;

    public HackAssembler(File file) {
        this.parser = new Parser(file);
        this.code = new Code();
        this.symbolTable = new SymbolTable();
    }


    public void convertToBinary() {
        try {
            FileWriter writer = new FileWriter("Prog.hack");

            
            int lineNum = 1;
            int numCInstruct = 0;
            int numAInstruct = 0;

            // First pass
            while (parser.hasMoreCommands()) {
                parser.advance();
                if (parser.commandType() == Parser.CommandType.C_COMMAND) {
                    numCInstruct++;
                } else if (parser.commandType() == Parser.CommandType.A_COMMAND) {
                    numAInstruct++;
                } else if (parser.commandType() == Parser.CommandType.L_COMMAND) {
                    symbolTable.addEntry(parser.symbol(), lineNum);
                }
                lineNum++;
            }

            parser.reset(); // Go back to the beginning of the file

            // Second pass
            int ramAddress = 16;

            while (parser.hasMoreCommands()) {
                parser.advance();
                if (parser.commandType() == Parser.CommandType.A_COMMAND) {
                    String symbol = parser.symbol();
                    if (!symbol.matches("[0-9]+") && !symbolTable.contains(symbol)) {
                        // 
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        File file = new File(args[0]);
        HackAssembler assembler = new HackAssembler(file);
        assembler.convertToBinary();
    }
}



