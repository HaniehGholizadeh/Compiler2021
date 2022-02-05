package compiler.Vtable;

import compiler.CGen.*;

import java.util.HashMap;
//now we have to implement Vtable to see which function we have to implement
public class VTable {
    //values
    private final HashMap<String, CodeForFunct> table;

    //constructors
    public VTable() {
        this.table = new HashMap<>();
    }

    //getters
    public void addFunction(String name, CodeForFunct codeForFunct) {
        table.put(name, codeForFunct);
    }
    public CodeForFunct getFunction(String name) throws Exception {
        if ((CodeForFunct)table.get(name) == null) {
            throw new SemanticError("error");
        }
        return (CodeForFunct)table.get(name) ;
    }
}
