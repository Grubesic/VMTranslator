package org.rogr;

import java.util.Map;
public class ArithmeticTranslator {

    public static final String LABEL = "LABEL";
    public static final Map<String, String> COMMANDS = Map.of(
            "add", translateAdd(),
            "sub", translateSub(),
            "neg", translateNeg(),
            "eq", translateEq(),
            "gt", translateGt(),
            "lt", translateLt(),
            "and", translateAnd(),
            "or", translateOr(),
            "not", translateNot()
    );

    public static String translateAdd(){
        return """
                @SP
                M=M-1
                A=M
                D=M
                A=A-1
                M=M+D
                """;
    }

    public static String translateSub(){
        return """
                @SP
                M=M-1
                A=M
                D=M
                A=A-1
                M=M-D
                """;
    }

    public static String translateNeg(){
        return """
                @SP
                A=M-1
                M=-M
                """;
    }

    public static String translateEq(){
        return "@SP" + "\n" +
                "M=M-1" + "\n" +
                "A=M" + "\n" +
                "D=M" + "\n" +
                "A=A-1" + "\n" +
                "D=M-D" + "\n" +
                "M=-1" + "\n" +
                "@"+ LABEL + "\n" +
                "D;JEQ" + "\n" +
                "@SP" + "\n" +
                "A=M-1" + "\n" +
                "M=0" + "\n" +
                "(" + LABEL+ ")"+ "\n";
    }

    public static String translateGt(){
        return "@SP" + "\n" +
                "M=M-1" + "\n" +
                "A=M" + "\n" +
                "D=M" + "\n" +
                "A=A-1" + "\n" +
                "D=M-D" + "\n" +
                "M=-1" + "\n" +
                "@"+ LABEL + "\n" +
                "D;JGT" + "\n" +
                "@SP" + "\n" +
                "A=M-1" + "\n" +
                "M=0" + "\n" +
                "(" + LABEL+ ")"+ "\n";
    }

    public static String translateLt(){
        return "@SP" + "\n" +
                "M=M-1" + "\n" +
                "A=M" + "\n" +
                "D=M" + "\n" +
                "A=A-1" + "\n" +
                "D=M-D" + "\n" +
                "M=-1" + "\n" +
                "@"+ LABEL + "\n" +
                "D;JLT" + "\n" +
                "@SP" + "\n" +
                "A=M-1" + "\n" +
                "M=0" + "\n" +
                "(" + LABEL + ")"+ "\n";
    }

    private static String translateAnd(){
        return """
                @SP
                M=M-1
                A=M
                D=M
                A=A-1
                M=D&M
                """;
    }

    private static String translateOr(){
        return """
                @SP
                M=M-1
                A=M
                D=M
                A=A-1
                M=D|M
                """;
    }

    private static String translateNot(){
        return """
                @SP
                A=M-1
                M=!M
                """;
    }
}
