public class TypeNotFoundException extends RuntimeException {
    public TypeNotFoundException(String type) {
        super("Type does not exist: " + type);
    }
}
