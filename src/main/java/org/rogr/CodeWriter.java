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
        String cmd = command.getCommand();
        ArithmeticType arithmeticType = ArithmeticType.fromValue(cmd);
        if(arithmeticType.equals(ArithmeticType.ADD)){
            writer.write("add");
        } else if (arithmeticType.equals(ArithmeticType.SUB)){
            writer.write("sub");
        } else if (arithmeticType.equals(ArithmeticType.NEG)){
            writer.write("neg");
        } else if (arithmeticType.equals(ArithmeticType.EQ)){
            writer.write("eq");
        } else if (arithmeticType.equals(ArithmeticType.GT)){
            writer.write("gt");
        } else if (arithmeticType.equals(ArithmeticType.LT)){
            writer.write("lt");
        } else if (arithmeticType.equals(ArithmeticType.AND)){
            writer.write("and");
        } else if (arithmeticType.equals(ArithmeticType.OR)){
            writer.write("or");
        } else if (arithmeticType.equals(ArithmeticType.NOT)){
            writer.write("not");
        } else {
            throw new IllegalArgumentException("Illegal arithmetic command: " + cmd);
        }
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
