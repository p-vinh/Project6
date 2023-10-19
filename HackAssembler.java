import java.io.File;

public class HackAssembler {





    public static void main(String[] args) {
        File file = new File("C:\\Users\\vinhp\\OneDrive\\Documents\\GitHub\\nand2tetris\\projects\\06\\add\\Add.asm");
        Parser parser = new Parser(file);

        while (parser.hasMoreCommands()) {
            parser.advance();
            System.out.println(parser.commandType());
            System.out.println(parser.symbol());
            System.out.println(parser.dest());
            System.out.println(parser.comp());
            System.out.println(parser.jump());
        }
    }
}



