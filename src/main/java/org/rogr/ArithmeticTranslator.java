package org.rogr;

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
    public static final Map<String, String> COMMANDS = Map.of(
            "add", translateAdd(),
            "sub", translateSub(),
            "neg", translateNeg(),
            EQ_KEY, translateEq(),
            GT_KEY, translateGt(),
            LT_KEY, translateLt(),
            "and", translateAnd(),
            "or", translateOr(),
            "not", translateNot(),
            MULT_KEY, translateMult()
    );

    public static String translateAdd() {
        return """
                @SP
                M=M-1
                A=M
                D=M
                A=A-1
                M=M+D
                """;
    }

    public static String translateSub() {
        return """
                @SP
                M=M-1
                A=M
                D=M
                A=A-1
                M=M-D
                """;
    }

    public static String translateNeg() {
        return """
                @SP
                A=M-1
                M=-M
                """;
    }

    public static String translateEq() {
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

    public static String translateGt() {
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

    public static String translateLt() {
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
        //TODO: Figure out labels that work more generically
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
                   
                (LOOP)
                @i
                M=M - 1
                D=M
                @FINAL
                D;JEQ
                @sum
                D=M
                @x
                D=D+M
                @sum
                M=D
                                
                @LOOP
                0;JMP
                (FINAL)
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
