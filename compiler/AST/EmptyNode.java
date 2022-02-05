package compiler.AST;
import compiler.AST.*;

//if there is empty node and take the type from the nodetype
public class EmptyNode extends SimpleNode {

//constructors
    public EmptyNode() {
        super(NodeType.EMPTY_STM);
    }
}
