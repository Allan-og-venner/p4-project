public class SymbolUnboundException extends RuntimeException{
    public SymbolUnboundException(String symbol) {
        super("Symbol accessed before definition: " + symbol);
    }
}
