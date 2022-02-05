package compiler.AST;
import compiler.AST.NodeType;

import static compiler.AST.NodeType.*;

//if it was an IDNode then we take the type from node type and set the values

public class IDNode extends SimpleNode {

//values
    private final String v;
    private boolean isConstant;

//constructors
    public IDNode(String v) {
        super(IDENTIFIER);
        this.v = v;
    }
    public boolean isConstant() {
        return isConstant;
    }

//setters
    public void setisConstant(boolean isConstant) {
        this.isConstant = isConstant;
    }

//getters
    public String getv() {
        return v;
    }
    @Override
    public String toString() {
        return v;
    }
}
