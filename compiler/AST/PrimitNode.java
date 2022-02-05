package compiler.AST;
import compiler.AST.Type;
import compiler.AST.NodeType;

//we make the primitive type for each node that are first call
public class PrimitNode extends SimpleNode {

//values
    private final Type type;

//constructors
    public PrimitNode(NodeType nodeType, Type type) {
        super(nodeType);
        this.type = type;
    }

//getters
    public Type getType() {
        return type;
    }
}
