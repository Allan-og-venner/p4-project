public class DuplicateDefinitionException extends RuntimeException {
    public DuplicateDefinitionException(int line, String symbol) {
        super(line + ": Symbol already defined: " + symbol);
    }
}
