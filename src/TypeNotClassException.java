class TypeNotClassException extends RuntimeException {
    TypeNotClassException(int lineNumber, String type) {super(lineNumber + " - " + type + " cannot be dereferenced");}
}