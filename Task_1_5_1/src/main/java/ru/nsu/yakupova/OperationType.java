package ru.nsu.yakupova;

/**
 * Enum for operation types.
 */
public enum OperationType {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    SIN("sin"),
    COS("cos"),
    SQRT("sqrt"),
    LOG("log"),
    POW("pow");

    private final String title;

    OperationType(String title) {
        this.title = title;
    }

    /**
     * Method for getting const from string.
     */
    public static OperationType fromString(String title) {
        for (OperationType x : OperationType.values()) {
            if (x.title.equals(title)) {
                return x;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "OperationType{" + "title='" + title + '\'' + '}';
    }
}