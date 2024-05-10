public class MissingDefinitionException extends RuntimeException {
    public MissingDefinitionException(String function) {
        super("Missing definition of: " + function);
    }
}