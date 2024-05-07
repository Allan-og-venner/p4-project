public class IllegalTypeException extends RuntimeException {
    public IllegalTypeException(int lineNumber, String type) {
        super(lineNumber + " - Illegal type: " + type);
    }
}
