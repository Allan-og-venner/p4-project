public class ClassStringBuilder {
    private String start;
    private String end = "}";
    private StringBuilder block = new StringBuilder();

    public ClassStringBuilder addStart(String name) {
        start = "static class " + name + " {";
        return this;
    }

    public ClassStringBuilder addStart(String name, String superClass) {
        start = "class " + name + " extends " + superClass + " {";
        return this;
    }

    public ClassStringBuilder addToBlock(String string) {
        block.append(string);
        return this;
    }

    public StringBuilder getBlock() {
        return block;
    }

    public String close() {
        return (!(start == null) ? start : "") + block + end;
    }

}
