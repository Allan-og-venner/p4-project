public class IllegalFunctionLocationException extends RuntimeException {
    public IllegalFunctionLocationException(int line) {super(line + " - Cannot define function inside this scope");}
}
