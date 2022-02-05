package compiler.AST;
import compiler.Scope.DSCP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//to making a ast we need to make a tree and we need nodes and one node can be
//others nodes parent or child so we need parent and child functions
public class SimpleNode implements Node {

//values
    private Node parent;
    private DSCP dscp;
    private final NodeType nodeType;
    private List<Node> children = new ArrayList<>();
    
//constructors
    public SimpleNode(NodeType nodeType) {
        this.nodeType = nodeType;
    }

//getters
    @Override
    public NodeType getNodeType() {
        return this.nodeType;
    }

    @Override
    public DSCP getDSCP() {
        return this.dscp;
    }

    @Override
    public Node getChild(int index) {
        return children.get(index);
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }

//adding childrens nodes
    @Override
    public void addChild(Node node) {
        children.add(node);
    }

    @Override
    public void addChild(int index, Node node) {
        children.add(index, node);
    }

    @Override
    public void addChildren(List<Node> nodes) {
        children.addAll(nodes);
    }

    @Override
    public void addChild(Node... nodes) {
        Collections.addAll(children, nodes);
    }

 
//setters
    @Override
    public void setChildren(Node... nodes) {
        children = Arrays.asList(nodes);
    }



    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public void setDSCP(DSCP dscp) {
        this.dscp = dscp;
    }
}
