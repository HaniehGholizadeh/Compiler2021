package compiler.AST;

import compiler.Scope.DSCP;

import java.util.List;

//all the functions that we have to use we make them in here that is the interface
//all of them are in SimpleNode
public interface Node {

    NodeType getNodeType();

    DSCP getDSCP();

    void setDSCP(DSCP dscp);

    void addChild(Node node);

    void addChild(int index, Node node);

    void addChildren(List<Node> nodes);

    void addChild(Node... nodes);

    List<Node> getChildren();

    void setChildren(Node... nodes);

    Node getChild(int index);

    Node getParent();

    void setParent(Node parent);

}
