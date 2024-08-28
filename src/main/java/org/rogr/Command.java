package org.rogr;

public class Command {
    private String command;
    private CommandType type;
    public Command(String command, CommandType type) {
        this.command = command;
        this.type = type;
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
}
