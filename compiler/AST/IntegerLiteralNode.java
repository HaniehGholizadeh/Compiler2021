package compiler.AST;
import compiler.AST.Literal;

//like othres literals we give the primitive value
//then we set the values

public class IntegerLiteralNode extends Literal {

//values
    private final int v;

//constructors
    public IntegerLiteralNode(String v) {
        super(PrimitType.INT);
        this.v = Integer.parseInt(v);
    }

    @Override
    public String toString() {
        return String.valueOf(v);
    }

//getters
    public int getValue() {
        return v;
    }
}
