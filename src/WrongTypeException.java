public class WrongTypeException extends RuntimeException {
    public WrongTypeException(String expected, String received) {
        super("Expected type: " + expected + ", received: " + received);
    }
}
