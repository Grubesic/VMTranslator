package org.rogr;

import org.rogr.model.Command;
import org.rogr.model.CommandType;
import org.rogr.translator.ArithmeticTranslator;
import org.rogr.translator.MemoryAccessTranslator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
* SP stored in RAM[0]
* Stack base address is 256
*
*
*
* */

public class CodeWriter {
    private final BufferedWriter writer;

    private final String fileName;

    public CodeWriter(String filePath) throws IOException {
        File file = new File(filePath);
        writer = new BufferedWriter(new FileWriter(file));
        fileName = file.getName();
    }
    public void writeArithmetic(Command command) throws IOException {
        if (!command.getType().equals(CommandType.C_ARITHMETIC)){
            throw new IllegalArgumentException("Command type must be C_ARITHMETIC");
        }
        String cmd = command.getCommand();
        writer.write("// " + command.getType() + " " + cmd);

        writer.newLine();
        String translatedCmd = ArithmeticTranslator.get(cmd.toLowerCase());

        if(translatedCmd != null){
            writer.write(translatedCmd);
            writer.newLine();
        } else {
            throw new IllegalArgumentException("Illegal arithmetic command: " + cmd);
        }
    }

    public void writePushPop(Command command) throws IOException {
        String translatedCommand;
        writer.write("// " + command.getType() + " " + command.getArg1() + " " + command.getArg2());
        writer.newLine();
        if (command.getType().equals(CommandType.C_PUSH)) {
            translatedCommand = MemoryAccessTranslator.translatePush(command.getArg1(), command.getArg2(), fileName);
        } else if (command.getType().equals(CommandType.C_POP)) {
            translatedCommand = MemoryAccessTranslator.translatePop(command.getArg1(), command.getArg2(), fileName);
        } else {
            throw new IllegalArgumentException("Command type must be C_PUSH or C_POP");
        }
        writer.write(translatedCommand);
        writer.newLine();
    }
    public void close() throws IOException {
        writer.close();
    }
}
