package org.rogr;

public enum ArithmeticType {
    ADD("add"),
    SUB("sub"),
    NEG("neg"),
    EQ("eq"),
    GT("gt"),
    LT("lt"),
    AND("and"),
    OR("or"),
    NOT("not");

    private final String command;

    ArithmeticType(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }

    public static ArithmeticType fromValue(String value) {
        for (ArithmeticType type : ArithmeticType.values()) {
            if (type.command.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Illegal arithmetic command: " + value);
    }
}
