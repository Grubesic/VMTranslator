package org.rogr;

import java.io.*;

public class Parser {

    private static final String[] ARITHMETIC_COMMANDS = new String[]{
            "add", "sub", "neg", "eq", "gt", "lt", "and", "or", "not"
    };

    private final BufferedReader reader;

    private Command currentCommand;

    private boolean hasMoreCommands = true;

    private final String inputFilePath;

    public Parser(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        inputFilePath = file.getAbsolutePath();
        reader = new BufferedReader(new FileReader(filePath));
    }
    public boolean hasMoreCommands(){
        return hasMoreCommands;
    }
    public void advance() throws IOException {
        String line;
        if ((line = reader.readLine()) != null) {
            if (line.isEmpty() || line.startsWith("//")) {
                advance();
                return;
            }
            if(isArithmeticCommand(line)){
                currentCommand = new Command(line, CommandType.C_ARITHMETIC);
            }
        } else {
            hasMoreCommands = false;
        }
    }
    public CommandType commandType(){
        return currentCommand.getType();
    }

    public Command getCurrentCommand(){
        return currentCommand;
    }

    public String arg1(){
        return currentCommand.getCommand();
        //TODO: Implement this method
    }

    public int arg2(){
        return 0;
        //TODO: Implement this method
    }

    public String getInputFilePath(){
        return inputFilePath;
    }

    public void close() throws IOException {
        reader.close();
    }

    private boolean isArithmeticCommand(String command){
        String cmdLowercase = command.toLowerCase();
        for (String c : ARITHMETIC_COMMANDS){
            if (c.equals(cmdLowercase)){
                return true;
            }
        }
        return false;
    }

}
