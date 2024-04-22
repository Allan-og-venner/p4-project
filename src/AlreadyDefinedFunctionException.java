public class AlreadyDefinedFunctionException extends RuntimeException {
    public AlreadyDefinedFunctionException(String function) {
        super("Function: " + function + " Already Declared");
    }
}