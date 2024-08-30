package org.rogr.translator;

public class MemoryAccessTranslator {
    public static final String PUSH = "push";
    public static final String POP = "pop";
    public static final String CONSTANT = "constant";
    public static final String LOCAL = "local";
    public static final String ARGUMENT = "argument";
    public static final String THIS = "this";
    public static final String THAT = "that";
    public static final String TEMP = "temp";
    public static final String POINTER = "pointer";
    public static final String STATIC = "static";

    public static String getSegment(String segment){
        return switch (segment) {
            case LOCAL -> "LCL";
            case ARGUMENT -> "ARG";
            case THIS -> "THIS";
            case THAT -> "THAT";
            default -> throw new IllegalArgumentException("Invalid segment: " + segment);
        };
    }

    public static String translatePush(String segment, int index){
        String asm;
        switch (segment) {
            case CONSTANT -> asm = "@" + index + "\n" +
                    "D=A\n" +
                    "@SP\n" +
                    "A=M\n" +
                    "M=D\n" +
                    "@SP\n" +
                    "M=M+1\n";
            case TEMP -> asm = "@" + (5 + index) + "\n" +
                    "D=M\n" +
                    "@SP\n" +
                    "A=M\n" +
                    "M=D\n" +
                    "@SP\n" +
                    "M=M+1\n";
            case POINTER -> asm = "@" + (3 + index) + "\n" +
                    "D=M\n" +
                    "@SP\n" +
                    "A=M\n" +
                    "M=D\n" +
                    "@SP\n" +
                    "M=M+1\n";
            case STATIC -> asm = "@" + index + "\n" +
                    "D=M\n" +
                    "@SP\n" +
                    "A=M\n" +
                    "M=D\n" +
                    "@SP\n" +
                    "M=M+1\n";
            default -> {
                String seg = getSegment(segment);
                asm = "@" + seg + "\n" +
                        "D=M\n" +
                        "@" + index + "\n" +
                        "A=D+A\n" +
                        "D=M\n" +
                        "@SP\n" +
                        "A=M\n" +
                        "M=D\n" +
                        "@SP\n" +
                        "M=M+1\n";
            }
        }
        return asm;
    }

    public static String translatePop(String segment, int index){
        String asm;
        switch (segment) {
            case TEMP -> asm = "@SP\n" +
                    "M=M-1\n" +
                    "A=M\n" +
                    "D=M\n" +
                    "@" + (5 + index) + "\n" +
                    "M=D\n";
            case POINTER -> asm = "@SP\n" +
                    "M=M-1\n" +
                    "A=M\n" +
                    "D=M\n" +
                    "@" + (3 + index) + "\n" +
                    "M=D\n";
            case STATIC -> asm = "@SP\n" +
                    "M=M-1\n" +
                    "A=M\n" +
                    "D=M\n" +
                    "@" + index + "\n" +
                    "M=D\n";
            default -> {
                String seg = getSegment(segment);
                asm = "@" + seg + "\n" +
                        "D=M\n" +
                        "@" + index + "\n" +
                        "D=D+A\n" +
                        "@R13\n" +
                        "M=D\n" +
                        "@SP\n" +
                        "M=M-1\n" +
                        "A=M\n" +
                        "D=M\n" +
                        "@R13\n" +
                        "A=M\n" +
                        "M=D\n";
            }
        }
        return asm;
    }
}