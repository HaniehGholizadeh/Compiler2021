package compiler.AST;

//like other literals we have to give double primitive value
//and then take the value from others 
//make it to string 


public class DoubleLiteralNode extends Literal {

//values
    private final Double v;

//constructors
    public DoubleLiteralNode(String v) {
        super(PrimitType.DOUBLE);
        this.v = Double.valueOf(v);
    }

//make it to string
    @Override
    public String toString() {
        return v.toString();
    }
//getters
    public Double getValue() {
        return v;
    }
}
