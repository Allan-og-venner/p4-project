import java.util.Hashtable;

public class ClassBuilder {
    private SymbolTable table;
    private String name = "";
    private String superClass = "Object";

    public ClassBuilder(SymbolTable table) {
        this.table = table;
    }

    public ClassBuilder addField(String identifier, String type) {
        table.addValue(identifier, type);
        return this;
    }

    public ClassBuilder addMethod(String identifier, String type) {
        table.addFunction(identifier, type);
        return this;
    }

    public ClassBuilder addName(String name) {
        this.name = name;
        return this;
    }

    public ClassBuilder addSuperClass(String name) {
        this.name = name;
        return this;
    }

    public void buildClass(Hashtable<String, String> tTable, Hashtable<String, SymbolTable> cTable) {
        tTable.put(name, superClass);
        cTable.put(name, table);
    }
}