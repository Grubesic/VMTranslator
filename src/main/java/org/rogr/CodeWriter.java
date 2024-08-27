package org.rogr;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {
    private final BufferedWriter writer;
    public CodeWriter(String filePath) throws IOException {
        writer = new BufferedWriter(new FileWriter(filePath));
    }
    public void writeArithmetic(Command command) throws IOException {
        if (!command.getType().equals(CommandType.C_ARITHMETIC)){
            throw new IllegalArgumentException("Command type must be C_ARITHMETIC");
        }
        String arithmeticCommand = command.getCommand();
        writer.write(arithmeticCommand);
        writer.newLine();
    }
    public void writePushPop(Command command) throws IOException {
        if (command.getType().equals(CommandType.C_PUSH)){
            writer.write("push");
        } else if (command.getType().equals(CommandType.C_POP)){
             writer.write("pop");
        } else {
            throw new IllegalArgumentException("Command type must be C_PUSH or C_POP");
        }
        writer.newLine();
    }
    public void close() throws IOException {
        writer.close();
    }
}
