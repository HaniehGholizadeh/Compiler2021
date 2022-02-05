package compiler.AST;

//like the Node this class is the interface and have the all of function 
public interface Type {
    String getSignature();
    String getInitialValue();
    int getAlign();
    PrimitType getPrimitive();

}
