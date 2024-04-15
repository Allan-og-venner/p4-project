public class SymbolUnboundException extends RuntimeException{
    public SymbolUnboundException(int line, String symbol) {
        super(line + ": Symbol accessed before definition: " + symbol);
    }
}
