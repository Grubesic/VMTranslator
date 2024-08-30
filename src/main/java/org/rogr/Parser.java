package org.rogr;

import org.rogr.model.Command;
import org.rogr.model.CommandType;
import org.rogr.translator.ArithmeticTranslator;
import org.rogr.translator.BranchingTranslator;
import org.rogr.translator.MemoryAccessTranslator;

import java.io.*;

public class Parser {

    private final BufferedReader reader;

    private Command currentCommand;

    private boolean hasMoreCommands = true;

    private final String inputFilePath;

    public Parser(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        inputFilePath = file.getAbsolutePath();
        reader = new BufferedReader(new FileReader(filePath));
    }

    public boolean hasMoreCommands() {
        return hasMoreCommands;
    }

    public void advance() throws IOException {
        String line;
        if ((line = reader.readLine()) != null) {
            if (line.isEmpty() || line.startsWith("//")) {
                advance();
                return;
            }
            line = removeUnwanted(line);

            if (isArithmeticCommand(line)) {
                currentCommand = new Command(line, CommandType.C_ARITHMETIC);
            } else {
                String[] parts = line.split("\\s+");
                // Ensure that the command has exactly 3 parts
                if (parts.length == 3) {
                    String operation = parts[0];
                    String segment = parts[1];
                    int index = Integer.parseInt(parts[2]);
                    if (index < 0) {
                        throw new IllegalArgumentException("Index must be a non negative integer");
                    }
                    if (operation.equalsIgnoreCase(MemoryAccessTranslator.PUSH)) {
                        currentCommand = new Command(operation, CommandType.C_PUSH, segment, index);
                    } else if (operation.equalsIgnoreCase(MemoryAccessTranslator.POP)) {
                        currentCommand = new Command(operation, CommandType.C_POP, segment, index);
                    } else {
                        throw new IllegalArgumentException("Invalid memory access command format");
                    }
                } else if (parts.length == 2) {
                    String operation = parts[0];
                    if (operation.equalsIgnoreCase(BranchingTranslator.GOTO)) {
                        currentCommand = new Command(operation, CommandType.C_GOTO, parts[1]);
                    } else if (operation.equalsIgnoreCase(BranchingTranslator.IF_GOTO)) {
                        currentCommand = new Command(operation, CommandType.C_IF, parts[1]);
                    } else if (operation.equalsIgnoreCase(BranchingTranslator.LABEL_COMMAND)) {
                        currentCommand = new Command(operation, CommandType.C_LABEL, parts[1]);

                    } else {
                        throw new IllegalArgumentException("Invalid branching command format");
                    }
                } else {
                    throw new IllegalArgumentException("Invalid command format");
                }
            }
        } else {
            hasMoreCommands = false;
        }
    }

    private String removeUnwanted(String line) {
        if (line.contains("//")) {
            return line.substring(0, line.indexOf("//")).trim();
        }
        return line.trim();
    }

    public CommandType commandType() {
        return currentCommand.getType();
    }

    public Command getCurrentCommand() {
        return currentCommand;
    }

    public String arg1() {
        return currentCommand.getArg1();
    }

    public int arg2() {
        return currentCommand.getArg2();
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void close() throws IOException {
        reader.close();
    }

    private boolean isArithmeticCommand(String command) {
        String cmdLowercase = command.toLowerCase();
        for (String c : ArithmeticTranslator.COMMANDS.keySet()) {
            if (c.equals(cmdLowercase)) {
                return true;
            }
        }
        return false;
    }

}
