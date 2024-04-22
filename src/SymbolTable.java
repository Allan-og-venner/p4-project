import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class SymbolTable implements Cloneable {

    private Hashtable<String, String> tTable = new Hashtable<String, String>();
    private Hashtable<String, String> vTable = new Hashtable<String, String>();
    private Hashtable<String, String> fTable = new Hashtable<String, String>();
    private Hashtable<String, String> innerVTable = new Hashtable<String, String>();
    private Hashtable<String, String> innerFTable = new Hashtable<String, String>();
    private Hashtable<String, SymbolTable> cTable = new Hashtable<String, SymbolTable>();

    public SymbolTable() {
        tTable.put("int", "float");
        tTable.put("float", "");
        tTable.put("char", "");
        tTable.put("string", "");
        tTable.put("void", "");
        tTable.put("Object", "");
    }

    /**
     * puts a class name with the superclass Object into the typeTable
     * @param name
     */
    public void addClass(String name) {
        tTable.put(name, "Object");
    }

    /**
     * puts a class name with some superclass into the typeTable
     * @param name
     * @param superclass
     */
    public void addClass(String name, String superclass) {
        tTable.put(name, superclass);
    }

    /**
     * Returns the entire typeTable
     * @return
     */
    public Hashtable<String, String> getTypes() { return this.tTable; }

    /**
     * Checks if a type is in the typeTable
     * @param input
     * @return true or false
     */
    public boolean checkType(String input) {
        return this.tTable.containsKey(input);
    }

    public boolean checkClass(String input) {
        String found = this.tTable.get(input);
        return !(found == null || found.isEmpty());
    }

    public boolean checkInnerF(String input) {
        return this.innerFTable.containsKey(input);
    }

    public boolean checkInnerV(String input) {
        return this.innerVTable.containsKey(input);
    }

    //Check if class1 inherits class2

    /**
     * Removes array prefix if there
     * Loops through the typeTable to check if class1 inherits class2 - returns true if yes
     * @param class1
     * @param class2
     * @return true or false
     */
    public boolean checkInherits(String class1, String class2) {
        if (class1.equals("null")) {
            return true;
        }
        if (class1.startsWith("array ") && class2.startsWith("array ")) {
            class1 = class1.replaceFirst("^array ", "");
            class2 = class2.replaceFirst("^array ", "");
        }
        String key = class1;
        while (!key.equals(class2)) {
            if (key.isEmpty()) {
                return false;
            }
            key = tTable.get(key);
        }
        return true;
    }

    /**
     * Adds an identifier and type to the valueTable if the type exists in the typeTable
     * @param symbol identifier
     * @param type of the identifier
     */
    public void addValue(String symbol, String type) {
        String innerstType = type.replaceAll("^array ", "");
        if (checkType(innerstType)) {
            innerVTable.put(symbol, type);
        } else {
            throw new TypeNotFoundException(type);
        }
    }

    /**
     * Checks if all the parameter types of a function exists in the typeTable
     * Adds the function symbol, return type and parameter types to the function table
     * @param symbol identifier for the function
     * @param type return type + parameter types
     */
    public void addFunction(String symbol, String type) {
        ArrayList<String> types = new ArrayList<>(Arrays.asList(type.split(",")));
        for (String i : types) {
            if (!checkType(i)) {
                throw new TypeNotFoundException(i);
            }
        }
        innerFTable.put(symbol, type);
    }

    /**
     *
     * @param classname
     * @param table
     */
    public void addClassSymbols(String classname, SymbolTable table) {
        cTable.put(classname, table);
    }

    public String vLookup(String input) {
        if (innerVTable.containsKey(input)) {
            return innerVTable.get(input);
        }
        if (innerVTable.containsKey("static " + input)) {
            return innerVTable.get("static " + input);
        }
        if (vTable.containsKey("static " + input)) {
            return vTable.get("static " + input);
        }
        return vTable.get(input);
    }

    public String fLookup(String input) {
        if (innerFTable.containsKey(input)) {
            return innerFTable.get(input);
        }
        return fTable.get(input);
    }

    public String cLookup(String input) {
        if (cTable.containsKey(input)) {
            return "Class " + input;
        }
        return null;
    }
    
    public SymbolTable findClassSymbolTable(String className) {
        return cTable.get(className);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SymbolTable cloned = (SymbolTable) super.clone();
        cloned.tTable = new Hashtable<String, String>();
        cloned.tTable.putAll(this.tTable);
        cloned.vTable = new Hashtable<String, String>();
        cloned.vTable.putAll(this.vTable);
        cloned.vTable.putAll(this.innerVTable);
        cloned.fTable = new Hashtable<String, String>();
        cloned.fTable.putAll(this.fTable);
        cloned.fTable.putAll(this.innerFTable);
        cloned.cTable = new Hashtable<String, SymbolTable>();
        cloned.cTable.putAll(this.cTable);
        cloned.innerVTable = new Hashtable<String,String>();
        cloned.innerFTable = new Hashtable<String,String>();
        return cloned;
    }

    public Hashtable<String,String> getVTable() {return this.vTable;}
    public Hashtable<String,String> getFTable() {return this.fTable;}
    public Hashtable<String,String> getInnerVTable() {return this.innerVTable;}
    public Hashtable<String,String> getInnerFTable() {return this.innerFTable;}
    public Hashtable<String, SymbolTable> getCTable() {return this.cTable;}

    public String findClosestAncestor(String class1, String class2) {
        if (class1.equals("null")) {
            return class2;
        }
        if (class2.equals("null")) {
            return class1;
        }
        ArrayList<String> class1Ancestors = new ArrayList<String>();
        String key = class1;
        while (!key.isEmpty()) {
            class1Ancestors.add(key);
            key = tTable.get(key);
        }
        key = class2;
        while (!key.isEmpty()) {
            if (class1Ancestors.contains(key)) {
                return key;
            }
            key = tTable.get(key);
        }
        return "void";
    }
}
