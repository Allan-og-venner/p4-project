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

    public SymbolTable createOuterSymbolTable() {
        tTable.put("int", "float");
        tTable.put("float", "");
        tTable.put("char", "string");
        tTable.put("string", "");
        tTable.put("void", "");
        tTable.put("action", "");
        innerVTable.put("players", "array Player");
        innerFTable.put("strlen", "int,string");
        innerFTable.put("command print", "void,string");
        innerFTable.put("command allowAction", "void,action");
        innerFTable.put("command disallowAction", "void,action");
        innerFTable.put("command displayAllowedActions", "void,");
        innerFTable.put("command disallowAllActions", "void,");
        innerFTable.put("command showGameState", "void,Player");
        innerFTable.put("command generatePlayerList", "array Player,array string");
        try {
            new ClassBuilder((SymbolTable) this.clone()).addName("Object").addSuperClass("").addMethod("toString", "string").buildClass(tTable, cTable);
            new ClassBuilder((SymbolTable) this.clone()).addName("Location").addField("name", "string").addField("cards", "array Card").addMethod("numberOfCards","int").addMethod("move", "void,int,Location").buildClass(tTable, cTable);
            new ClassBuilder((SymbolTable) this.clone()).addName("PlayArea").addSuperClass("Location").buildClass(tTable, cTable);
            new ClassBuilder((SymbolTable) this.clone()).addName("Deck").addSuperClass("Location").addField("visible", "int").addMethod("shuffle", "void").addMethod("draw", "void,Location").addMethod("drawMany", "void,Location,int").addMethod("getTop", "Card").addMethod("add", "void,Card,int").buildClass(tTable, cTable);
            new ClassBuilder((SymbolTable) this.clone()).addName("Hand").addSuperClass("Location").addField("owner", "Player").addField("maxSize","int").addMethod("move", "void,int,Location").buildClass(tTable, cTable);
            new ClassBuilder((SymbolTable) this.clone()).addName("Player").addField("name", "string").addField("hand", "Hand").addMethod("findNextPlayer","Player,int").addField("nextPlayer", "Player").buildClass(tTable, cTable);
            new ClassBuilder((SymbolTable) this.clone()).addName("Card").addField("ID", "string").buildClass(tTable, cTable);
        } catch(CloneNotSupportedException e) {
            System.err.println(e.getMessage());
        }
        return this;
    }


    public void addClass(String name) {
        tTable.put(name, "Object");
    }

    public void addClass(String name, String superclass) {
        tTable.put(name, superclass);
    }

    public Hashtable<String, String> getTypes() {
        return this.tTable;
    }

    public boolean checkType(String input) {
        return this.tTable.containsKey(input);
    }

    public boolean checkClass(String input) {
        String found = this.tTable.get(input);
        return !(found == null );
    }

    public boolean checkInnerF(String input) {
        return this.innerFTable.containsKey(input);
    }

    public boolean checkInnerV(String input) {
        return this.innerVTable.containsKey(input);
    }

    //Check if class1 inherits class2
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

    public void addValue(String symbol, String type) {
        String innerstType = type.replaceAll("^array ", "");
        if (checkType(innerstType)) {
            innerVTable.put(symbol, type);
        } else {
            throw new TypeNotFoundException(type);
        }
    }

    public void addFunction(String symbol, String type) {
        ArrayList<String> types = new ArrayList<>(Arrays.asList(type.split(",")));
        for (String i : types) {
            if (!checkType(i)) {
                throw new TypeNotFoundException(i);
            }
        }
        innerFTable.put(symbol, type);
    }

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
        if (innerFTable.containsKey("command " + input)) {
            return innerFTable.get("command " + input);
        }
        if (fTable.containsKey("command " + input)) {
            return fTable.get("command " + input);
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

    public Hashtable<String,String> getVTable() {
        return this.vTable;
    }
    public Hashtable<String,String> getFTable() {
        return this.fTable;
    }
    public Hashtable<String, SymbolTable> getCTable() {
        return this.cTable;
    }
    public Hashtable<String,String> getInnerVTable() {
        return this.innerVTable;
    }
    public Hashtable<String,String> getInnerFTable() {
        return this.innerFTable;
    }

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
