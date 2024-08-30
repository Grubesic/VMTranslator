package org.rogr.translator;

import org.rogr.model.Command;

import java.util.Map;

public class BranchingTranslator {

    public static final String GOTO = "goto";
    public static final String IF_GOTO = "if-goto";

    public static final String LABEL_COMMAND = "label";

    private static final String LABEL = "<LABEL>";

    private static final Map<String, String> BRANCHING_COMMANDS = Map.of(
            GOTO, translateGoto(),
            IF_GOTO, translateIfGoto(),
            LABEL_COMMAND, translateLabel()
    );

    private static String translateGoto() {
        return "@" + LABEL + "\n" +
                """
                0;JMP
                """;
    }

    private static String translateIfGoto() {
        return """
                @SP
                M=M-1
                A=M
                D=M
                @""" + LABEL + "\n" +
                """ 
                D;JNE
                """;
    }

    private static String translateLabel() {
        return "(" + LABEL + ")";
    }

    public static String translate(String command, String label) {
        return BRANCHING_COMMANDS.get(command.toLowerCase()).replaceAll(LABEL, label);
    }
}
