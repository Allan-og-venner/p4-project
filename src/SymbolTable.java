import java.util.ArrayList;
import java.util.Hashtable;

public class SymbolTable implements Cloneable {

    private Hashtable<String, String> tTable = new Hashtable<String, String>();
    private Hashtable<String, String> vTable = new Hashtable<String, String>();
    private Hashtable<String, String> fTable = new Hashtable<String, String>();
    private Hashtable<String, SymbolTable> cTable = new Hashtable<String, SymbolTable>();

    public SymbolTable() {
        tTable.put("int", "");
        tTable.put("float", "");
        tTable.put("char", "");
        tTable.put("string", "");
        tTable.put("void", "");
        tTable.put("Object", "");
    }

    public void addClass(String name) {
        tTable.put(name, "Object");
    }

    public void addClass(String name, String superclass) {
        tTable.put(name, superclass);
    }

    public Hashtable<String, String> getTypes() { return this.tTable; }


    public boolean checkType(String input) {
        return this.tTable.contains(input);
    }

    public boolean checkClass(String input) {
        String found = this.tTable.get(input);
        return !(found == null || found.isEmpty());
    }

    //Check if class1 inherits class2
    public boolean checkInherits(String class1, String class2) {
        if (class1.equals("null")) {
            return true;
        }
        String key = class1;
        while (key.equals(class2)) {
            if (key.equals("Object")) {
                return false;
            }
            key = tTable.get(key);
        }
        return true;
    }

    public void addValue(String symbol, String type) {
        if (checkType(type)) {
            vTable.put(symbol, type);
        }
    }

    public void addFunction(String symbol, String type) {
        if (checkType(type)) {
            fTable.put(symbol, type);
        }
    }

    public void superAddFunction(String input, String type) {
        fTable.put(input, type);
    }

    public void superAddValue(String input, String type) {
        vTable.put(input, type);
    }

    public String vLookup(String input) {
        return vTable.get(input);
    }

    public String fLookup(String input) {
        return fTable.get(input);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SymbolTable cloned = (SymbolTable) super.clone();
        cloned.tTable = (Hashtable<String, String>) this.tTable.clone();
        cloned.vTable = (Hashtable<String, String>) this.vTable.clone();
        cloned.fTable = (Hashtable<String, String>) this.fTable.clone();
        return cloned;
    }
}
