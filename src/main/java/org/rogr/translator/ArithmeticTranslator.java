package org.rogr.translator;

import java.util.Map;
import java.util.UUID;

public class ArithmeticTranslator {

    public static final String LABEL = "LABEL";
    public static final String LABEL_1 = "LABEL_1";
    public static final String LABEL_2 = "LABEL_2";
    private static final String MULT_KEY = "mult";
    private static final String EQ_KEY = "eq";
    private static final String GT_KEY = "gt";
    private static final String LT_KEY = "lt";

    private static final String ADD_KEY = "add";
    private static final String SUB_KEY = "sub";
    private static final String NEG_KEY = "neg";
    private static final String AND_KEY = "and";
    private static final String OR_KEY = "or";
    private static final String NOT_KEY = "not";

    public static final Map<String, String> COMMANDS = Map.of(
            ADD_KEY, translateAdd(),
            SUB_KEY, translateSub(),
            NEG_KEY, translateNeg(),
            EQ_KEY, translateEq(),
            GT_KEY, translateGt(),
            LT_KEY, translateLt(),
            AND_KEY, translateAnd(),
            OR_KEY, translateOr(),
            NOT_KEY, translateNot(),
            MULT_KEY, translateMult()
    );

    private static String translateAdd() {
        return """
                @SP
                M=M-1
                A=M
                D=M
                A=A-1
                M=D+M
                """;
    }

    private static String translateSub() {
        return """
                @SP
                M=M-1
                A=M
                D=M
                A=A-1
                M=M-D
                """;
    }

    private static String translateNeg() {
        return """
                @SP
                A=M-1
                M=-M
                """;
    }

    private static String translateEq() {
        return "@SP" + "\n" +
                "M=M-1" + "\n" +
                "A=M" + "\n" +
                "D=M" + "\n" +
                "A=A-1" + "\n" +
                "D=M-D" + "\n" +
                "M=-1" + "\n" +
                "@" + LABEL + "\n" +
                "D;JEQ" + "\n" +
                "@SP" + "\n" +
                "A=M-1" + "\n" +
                "M=0" + "\n" +
                "(" + LABEL + ")" + "\n";
    }

    private static String translateGt() {
        return "@SP" + "\n" +
                "M=M-1" + "\n" +
                "A=M" + "\n" +
                "D=M" + "\n" +
                "A=A-1" + "\n" +
                "D=M-D" + "\n" +
                "M=-1" + "\n" +
                "@" + LABEL + "\n" +
                "D;JGT" + "\n" +
                "@SP" + "\n" +
                "A=M-1" + "\n" +
                "M=0" + "\n" +
                "(" + LABEL + ")" + "\n";
    }

    private static String translateLt() {
        return "@SP" + "\n" +
                "M=M-1" + "\n" +
                "A=M" + "\n" +
                "D=M" + "\n" +
                "A=A-1" + "\n" +
                "D=M-D" + "\n" +
                "M=-1" + "\n" +
                "@" + LABEL + "\n" +
                "D;JLT" + "\n" +
                "@SP" + "\n" +
                "A=M-1" + "\n" +
                "M=0" + "\n" +
                "(" + LABEL + ")" + "\n";
    }

    private static String translateAnd() {
        return """
                @SP
                M=M-1
                A=M
                D=M
                A=A-1
                M=D&M
                """;
    }

    private static String translateOr() {
        return """
                @SP
                M=M-1
                A=M
                D=M
                A=A-1
                M=D|M
                """;
    }

    private static String translateNot() {
        return """
                @SP
                A=M-1
                M=!M
                """;
    }

    private static String translateMult() {
        // TODO: Not tested yet
        return """
                @SP
                M=M-1
                A=M
                D=M
                @y
                M=D
                @i
                M=D // load y into i so that we can decrement the value each time we add it to the sum until 0
                @SP
                A=A-1
                D=M
                @x
                M=D
                @sum
                M=D // load x into sum so that we can add it to itself i times
                """+
                "("+LABEL_1+")" + "\n"+
                """
                @i
                M=M - 1
                D=M
                """+
                "@"+LABEL_2 + "\n"+
                """
                D;JEQ
                @sum
                D=M
                @x
                D=D+M
                @sum
                M=D
                """ +
                "@" + LABEL_1 + "\n" +
                "0;JMP" + "\n" +
                "("+ LABEL_2+ ")" + "\n" +
                """
                @sum
                D=M
                @SP
                A=M-1
                M=D
                """;
    }

    public static String get(String key){
        if (key.equals(MULT_KEY)){
           String multCmd = COMMANDS.get(MULT_KEY);
            return multCmd
                   .replaceAll(LABEL, UUID.randomUUID().toString())
                   .replaceAll(LABEL_1, UUID.randomUUID().toString())
                   .replaceAll(LABEL_2, UUID.randomUUID().toString());
        }else if (key.equals(EQ_KEY) || key.equals(GT_KEY) || key.equals(LT_KEY)){
            return COMMANDS.get(key).replaceAll(LABEL, UUID.randomUUID().toString());
        } else {
            return COMMANDS.get(key);
        }
    }
}
