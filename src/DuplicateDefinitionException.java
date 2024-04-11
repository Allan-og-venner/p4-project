public class DuplicateDefinitionException extends RuntimeException {
    public DuplicateDefinitionException(String symbol) {
        super("Symbol already defined: " + symbol);
    }
}
