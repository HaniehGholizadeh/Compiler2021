package compiler.Scope;

import java.util.ArrayList;
import java.util.HashMap;

//to don't be confuse by the values we have to implement scope
public class Scope {

//values
private int ArgCount;
private int CSC;
private int LSC;
private int SLC;
private final HashMap<String, DSCP> scope;
private final ArrayList<Scope> children;
private final Scope parent;
private final String name;
private final BlockType blockType;


//constructor

    public Scope(String name, Scope parent, BlockType blockType) {
        this.scope = new HashMap<>();
        this.name = name;
        this.parent = parent;
        children = new ArrayList<>();
        this.ArgCount = 0;
        this.CSC = 0;
        this.blockType = blockType;
    }

    //getters
    public HashMap<String, DSCP> getScope() {
        return this.scope;
    }

    public BlockType getBlockType() {
        return blockType;
    }
    
    public String getName() {
        return name;
    }
    public Scope getParent() {
        return parent;
    }

    public int getArgumentCounter() {
        return ArgCount;
    }

    public int getLoopStmtCounter() {
        return LSC;
    }
    public int getConditionStmtCounter() {
        return CSC;
    }
    public int getStringLiteralCounter() {
        return SLC;
    }
    ArrayList<Scope> getChildren() {
        return this.children;
    }


    public void addChild(Scope child) {
        children.add(child);
    }
    public void addArgumentCounter() {
        this.ArgCount++;
    }
    public void addConditionStmtCounter() {
        this.CSC++;
    }
    public void addLoopStmtCounter() {
        this.LSC++;
    }
    public void addStringLiteralCounter() {
        this.SLC++;
    }

  

    @Override
    public String toString() {
        return name;
    }
}
