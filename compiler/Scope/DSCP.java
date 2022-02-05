package compiler.Scope;


import compiler.AST.*;

//the way to concat the childrens and their parents
public class DSCP {

//values
    private final Node node;
    private Type type;
    private int AGP;
    private String v;
    private boolean isConst;
    private boolean IsArg;
    private boolean IsFunc;
   
    
    //constructors

    public DSCP(Type type, Node node) {
        this.type = type;
        this.node = node;
        this.IsArg = false;
    }

    public DSCP(Type type, Node node, boolean IsFunc) {
        this.type = type;
        this.node = node;
        this.IsArg = false;
        this.IsFunc = IsFunc;
    }

    public boolean isFunction() {
        return IsFunc;
    }

//setters and getters
    public void setFunction() {
        IsFunc = true;
    }

   

    public void setValue(String v) {
        this.v = v;
    }

    public void setArgumentTrue(int AGP) {
        IsArg = true;
        this.AGP = AGP;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public void setConstant(boolean constant) {
        isConst = constant;
    }
    public int getArgumentPlace() {
        return AGP;
    }
    public String getValue() {
        return v;
    }
    
    public Type getType() {
        return type;
    }
    
    public Node getNode() {
        return node;
    }

    public boolean isArgument() {
        return IsArg;
    }
    public boolean isConstant() {
        return isConst;
    }

  
}
