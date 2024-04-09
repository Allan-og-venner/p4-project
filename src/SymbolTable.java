import java.util.ArrayList;
import java.util.Hashtable;

public class SymbolTable implements Cloneable {

    private Hashtable<String, String> tTable = new Hashtable<String, String>();
    private Hashtable<String, String> vTable = new Hashtable<String, String>();

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

    public Hashtable<String, String> getTypes() { return this.tTable; }


    public boolean checkType(String input) {
        return this.types.contains(input);
    }

    public boolean checkClass(String input) {
        String found = this.tTable.get(input);
        return !(found == null || found.isEmpty());
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

    public void addValue(String symbol, String type) {
        if (checkType(type)) {
            vTable.put(symbol, type);
        }
    }

    public String lookup(String input) {
        return vTable.get(input);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SymbolTable cloned = (SymbolTable) super.clone();
        cloned.tTable = (Hashtable<String, String>) this.tTable.clone();
        cloned.vTable = (Hashtable<String, String>) this.vTable.clone();
        return cloned;
    }

}
