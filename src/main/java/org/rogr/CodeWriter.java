package org.rogr;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/*
* SP stored in RAM[0]
* Stack base address is 256
*
*
*
* */

public class CodeWriter {
    private final BufferedWriter writer;
    public CodeWriter(String filePath) throws IOException {
        writer = new BufferedWriter(new FileWriter(filePath));
    }
    public void writeArithmetic(Command command) throws IOException {
        if (!command.getType().equals(CommandType.C_ARITHMETIC)){
            throw new IllegalArgumentException("Command type must be C_ARITHMETIC");
        }
        String cmd = command.getCommand();
        writer.write("// Arithmetic cmd: " + cmd);
        writer.newLine();
        String translatedCmd = ArithmeticTranslator.COMMANDS.get(cmd.toLowerCase());

        if(translatedCmd != null){
            //Replace all occurrences of LABEL with a random UUID
            translatedCmd = translatedCmd.replace(ArithmeticTranslator.LABEL, UUID.randomUUID().toString());
            writer.write(translatedCmd);
            writer.newLine();
        } else {
            throw new IllegalArgumentException("Illegal arithmetic command: " + cmd);
        }
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
