package org.rogr;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    private static final String art = " _    _ _______      _______  ______ _______ __   _ _______        _______ _______  _____   ______\n" +
            "  \\  /  |  |  |         |    |_____/ |_____| | \\  | |______ |      |_____|    |    |     | |_____/\n" +
            "   \\/   |  |  |         |    |    \\_ |     | |  \\_| ______| |_____ |     |    |    |_____| |    \\_\n" +
            "                                                                                                  ";

    public static void main(String[] args) {
        if(args.length != 1){
            throw new IllegalArgumentException("Usage: java -jar <jarfile> <inputfile.vm> ");
        }
        System.out.println(art);
        String inputFilePath = args[0];
        if (!inputFilePath.contains(".vm")){
            throw new IllegalArgumentException("Input file must have .vm extension ");
        }
        Main program = new Main();
        program.run(inputFilePath);
    }
    public void run(String inputFile){
        try {

            Parser parser = new Parser(inputFile);
            String outputFilePath = parser.getInputFilePath().replaceAll(".vm", ".asm");
            CodeWriter writer = new CodeWriter(outputFilePath);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}