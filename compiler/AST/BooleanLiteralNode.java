package compiler.AST;
import compiler.AST.*;


//we make sure we have primitive value for booleans
//then we get the value and check if it is true or false


public class BooleanLiteralNode extends Literal {

//values
    private final boolean v;
    private final int i;

//constructor
    public BooleanLiteralNode(String v) {
        super(PrimitType.BOOL);
        this.v = Boolean.parseBoolean(v);
        if(this.v)
            i = 1;
        
        else
            i = 0;
        
    }

// we have to turn the value to string
    @Override
    public String toString() {
        return i + "";
    }

//getters
    public int getI() {
            return i;
    }
    public boolean getV() {
            return v;
    }
}
