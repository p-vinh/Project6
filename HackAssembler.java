
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;


public class HackAssembler {

    private File file;
    private Parser parser;
    private Code code;
    private SymbolTable symbolTable;

    public HackAssembler(File file) {
        this.file = file;
        this.parser = new Parser(file);
        this.code = new Code();
        this.symbolTable = new SymbolTable();
    }


    public void convertToBinary() {
        try {
            PrintWriter writer = new PrintWriter("Prog.hack");

            
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

            this.parser = new Parser(file); // Go back to the beginning of the file

            // Second pass
            int ramAddress = 16;

            while (parser.hasMoreCommands()) {
                parser.advance();
                StringBuilder command = new StringBuilder();
                if (parser.commandType() == Parser.CommandType.A_COMMAND) {
                    String symbol = parser.symbol();
                    if (!symbol.matches("[0-9]+") && symbolTable.contains(symbol)) {
                        /*  @Xxx where Xxx is a symbol and not a number,
                        look up Xxx in the symbol table. If the symbol is found in the table, replace it with its numeric meaning and 
                        complete the command’s translation. */
                        command.append("0");
                        // Convert integer to binary
                        String binary = Integer.toBinaryString(symbolTable.GetAddress(symbol));
                        command.append(binary);
                        writer.print(command.toString());
                    

                    } else if (symbol.matches("[0-9]+")) {
                        /* @Xxx where Xxx is a number */
                        command.append("0");
                        int binary = symbolTable.GetAddress(symbol);
                        String binaryString = Integer.toBinaryString(binary);

                        // Add padding if binaryString is less than 16 bits
                        int padding = 15 - binaryString.length();
                        for (int i = 0; i < padding; i++)
                            command.append("0");
                        command.append(binary);
                        writer.print(command.toString());
                    }
                } else if (parser.commandType() == Parser.CommandType.C_COMMAND) {
                    command.append("111");
                    String comp = parser.comp();
                    String dest = parser.dest();
                    String jump = parser.jump();
                    command.append(code.comp(comp));
                    command.append(code.dest(dest));
                    command.append(code.jump(jump));
                    writer.print(command.toString());
                }
            }


            writer.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }
    }




    public static void main(String[] args) {
        File file = new File(args[0]);
        HackAssembler assembler = new HackAssembler(file);
        assembler.convertToBinary();
    }
}



