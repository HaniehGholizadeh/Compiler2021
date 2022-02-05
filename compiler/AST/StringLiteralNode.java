package compiler.AST;
import compiler.AST.Literal;

//like other literals

public class StringLiteralNode extends Literal {
//values
    private final String v;

//constructors
    public StringLiteralNode(String v) {
        super(PrimitType.STRING);
        this.v = v;
    }
    @Override
    public String toString() {
        return v;
    }
//getters
    public String getValue() {
        return v;
    }
}
