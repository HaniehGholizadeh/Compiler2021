package compiler.AST;

import compiler.CGen.SemanticError;
import compiler.AST.*;
import java.util.stream.Stream;

//we get the childrens and check if it is null or not
//then we have the other types and check them from nodetype

public class Exp extends SimpleNode {

//values
    private boolean isID;
    private String res;
    private Type type;

//constructors
    public Exp() {
        super(NodeType.EXPR_STM);
    }


    public boolean isID() {
        return isID;
    }

//setters

    public void setIsID() throws Exception {
        if (this.getChild(0).getNodeType().equals(NodeType.VAR_USE) ) {
            IDNode id = ((IDNode) this.getChild(0).getChild(0));
            if (!id.getv().startsWith("%")) {
                res = "%" + id.getv();
            }
            else {
                res = id.getv();
            }
            if (id.getDSCP() == null)
                throw new SemanticError("ERROR");
            type = id.getDSCP().getType();
        } 
        else if (Stream.of(NodeType.ADD, NodeType.SUB, NodeType.MUL, NodeType.DIV, NodeType.MOD).anyMatch(nodeType -> this.getChild(0).getNodeType().equals(nodeType))) {
            res = this.getChild(0).getDSCP().getValue();

            type = this.getChild(0).getDSCP().getType();
            
            this.setDSCP(this.getChild(0).getDSCP());

        } 
        else if (this.getChild(0).getNodeType().equals(NodeType.IDENTIFIER)) {
            res = this.getChild(0).getDSCP().getValue();

            type = this.getChild(0).getDSCP().getType();
          
            this.setDSCP(this.getChild(0).getDSCP());
        }
        else if (this.getChild(0).getNodeType().equals(NodeType.READ_LINE)) {
            res = "\"ReadLine()\"";

            type = this.getChild(0).getDSCP().getType();
            
        } 
        else {
            if (!this.getChild(0).getDSCP().getValue().equals("readInteger()")) {
                Literal literal = (Literal) this.getChild(0);

                res = this.getChild(0).toString();

                type = literal.getType();
            } 
            else {
                type = this.getChild(0).getDSCP().getType();
            }

        }
        isID = true;
    }

    public void setType(Type type) {
        this.type = type;
    }

//getters
    public Type getType() {
        return type;
    }
    public String getres() {
        return res;
    }




}
