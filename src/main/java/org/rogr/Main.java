package org.rogr;

import java.io.IOException;
import java.io.Writer;

public class Main {
    private static final String art = " _    _ _______      _______  ______ _______ __   _ _______        _______ _______  _____   ______\n" +
            "  \\  /  |  |  |         |    |_____/ |_____| | \\  | |______ |      |_____|    |    |     | |_____/\n" +
            "   \\/   |  |  |         |    |    \\_ |     | |  \\_| ______| |_____ |     |    |    |_____| |    \\_\n" +
            "                                                                                                  ";

    public static void main(String[] args) {
        if(args.length != 1){
            throw new IllegalArgumentException("Usage: java -jar <jarfile> <inputfile.vm> ");
        }
        String inputFilePath = args[0];
        if (!inputFilePath.contains(".vm")){
            throw new IllegalArgumentException("Input file must have .vm extension ");
        }
        System.out.println(art);
        Main program = new Main();
        try {
            program.run(inputFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(String inputFile) throws IOException {
        Parser parser = null;
        CodeWriter writer = null;
        try {
            parser = new Parser(inputFile);
            String outputFilePath = parser.getInputFilePath().replaceAll(".vm", ".asm");
            writer = new CodeWriter(outputFilePath);
            while (parser.hasMoreCommands()){
                parser.advance();
                if(parser.commandType().equals(CommandType.C_ARITHMETIC)){
                    writer.writeArithmetic(parser.getCurrentCommand());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (parser != null){
                parser.close();
            }
            if (writer != null){
                writer.close();
            }
        }
    }
}