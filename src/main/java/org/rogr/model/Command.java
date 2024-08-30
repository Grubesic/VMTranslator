package org.rogr.model;

public class Command {
    private String command;
    private CommandType type;
    private String arg1;
    private int arg2;
    public Command(String command, CommandType type) {
        this.command = command;
        this.type = type;
    }
    public Command(String command, CommandType type, String arg1, int arg2) {
        this.command = command;
        this.type = type;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
    public Command(String command, CommandType type, String arg1) {
        this.command = command;
        this.type = type;
        this.arg1 = arg1;
    }
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public CommandType getType() {
        return type;
    }
    public void setType(CommandType type) {
        this.type = type;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }
}
