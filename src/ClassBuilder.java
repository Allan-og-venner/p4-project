import java.util.ArrayList;
import java.util.Hashtable;

public class ClassBuilder {
    private SymbolTable table;
    private String name = "";
    private String superClass = "Object";

    public ClassBuilder(SymbolTable table) {
        this.table = table;
    }

    public ClassBuilder addField(String identifier, String type) {
        table.getInnerVTable().put(identifier, type);
        return this;
    }

    public ClassBuilder addMethod(String identifier, String type) {
        table.getInnerFTable().put(identifier, type);
        return this;
    }

    public ClassBuilder addName(String name) {
        this.name = name;
        return this;
    }

    public ClassBuilder addSuperClass(String name) {
        this.superClass = name;
        if (table.findClassSymbolTable(superClass) != null) {
            table.getInnerFTable().putAll(table.findClassSymbolTable(superClass).getInnerFTable());
            table.getFTable().putAll(table.findClassSymbolTable(superClass).getFTable());
            table.getInnerVTable().putAll(table.findClassSymbolTable(superClass).getInnerVTable());
            table.getVTable().putAll(table.findClassSymbolTable(superClass).getVTable());
        }
        return this;
    }

    public void buildClass(Hashtable<String, String> tTable, Hashtable<String, SymbolTable> cTable) {
        table.getTypes().put(name, superClass);
        tTable.put(name, superClass);
        cTable.put(name, table);
    }
}
