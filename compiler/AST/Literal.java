package compiler.AST;

//it is like the father of all literals and all the literals have
// two function as the same
public abstract class Literal extends SimpleNode {

//values
    private final Type type;

//constructors
    public Literal(Type type) {
        super(NodeType.LITERAL);
        this.type = type;
    }

//getters
    public Type getType() {
        return type;
    }
}
