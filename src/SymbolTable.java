import java.util.ArrayList;
import java.util.Hashtable;

public class SymbolTable {
    private Hashtable<String, String> types = new Hashtable<String, String>();

    public SymbolTable() {
        types.put("int", "");
        types.put("float", "");
        types.put("char", "");
        types.put("string", "");
        types.put("void", "");
        types.put("object", "");
    }

    public void addClass(String name) {
        types.put(name, "Object");
    }

    public void addClass(String name, String superclass) {
        types.put(name, superclass);
    }

    public Hashtable<String, String> getTypes() { return this.types; }

    private Hashtable<String, String> table = new Hashtable<String, String>();

    public boolean checkType(String input) {
        return this.types.contains(input);
    }

    //Check if class1 inherits class2
    public boolean checkInherits(String class1, String class2) {
        if (class1 == "null") {
            return true;
        }
        String key = class1;
        while (key != class2) {
            if(key == "Object"){
                return false;
            }
            key = types.get(key);
        }
        return true;
    }

    public void addSymbol(String symbol, String type) {
        if (checkType(type)) {
            table.put(symbol, type);
        }
    }

}
