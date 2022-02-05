package compiler.Scope;
 import compiler.CGen.*;

public class SymbolTable {

//values

    private static final Scope root = new Scope("RootScope", null, BlockType.ROOT);
    private static Scope currentScope;

//constructors

    public SymbolTable() {
        currentScope = root;
    }

//functions
    public void addEntry(String entry, DSCP dscp) throws Exception {
        if (currentScope.getScope().containsKey(entry)) {
            throw new SemanticError("error");
        }
        currentScope.getScope().put(entry, dscp);
    }


    public void enterScope(String name, BlockType blockType, boolean isFirstPass) {
        Scope newScope = null;
        if (isFirstPass) {
            newScope = new Scope(name + "Scope", currentScope, blockType);
            currentScope.addChild(newScope);
        } else {
            for (Scope child : currentScope.getChildren()) {
                newScope = child;
                if (child.getName().equals(name + "Scope"))
                    break;
            }
        }
        currentScope = newScope;
    }

    public void leaveScope() {
        currentScope = currentScope.getParent();
    }

    @Override
    public String toString() {
        return currentScope.getName();
    }

//getters
    public static Scope getCurrentScope() {
        return currentScope;
    }

    public Scope getEntryScope(String entry) throws Exception {
        Scope scopeCrawler = currentScope;
        while (true) {
            if (scopeCrawler.getScope().get(entry) != null)
                return scopeCrawler;
            else {
                if (scopeCrawler.getParent() != null)
                    scopeCrawler = scopeCrawler.getParent();
                else break;
            }
        }
        throw new SemanticError("error");
    }

    public DSCP getDSCP(String entry) throws Exception {
        Scope scopeCrawler = currentScope;
        while (true) {
            if (scopeCrawler.getScope().get(entry) != null)
                return scopeCrawler.getScope().get(entry);
            else {
                if (scopeCrawler.getParent() != null)
                    scopeCrawler = scopeCrawler.getParent();
                else break;
            }
        }
        throw new SemanticError("error");
    }
}
