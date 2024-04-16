public class NoStaticFieldException extends RuntimeException {
    public NoStaticFieldException(int line, String className, String received) {
        super(line + ": Expected static method for class " + className +", received: " + received);
    }
}
