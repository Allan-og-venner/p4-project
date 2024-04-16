public class WrongTypeException extends RuntimeException {
    public WrongTypeException(int line, String expected, String received) {
        super(line + ": Expected type: " + expected + ", received: " + received);
    }
}
