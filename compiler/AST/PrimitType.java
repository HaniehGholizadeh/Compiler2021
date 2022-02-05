package compiler.AST;

//if the node is one of literals then we give the size of them to mips
//and give them primitive values

public enum PrimitType implements Type {

    BOOL("0", ".word", 1),
    INT("0", ".word", 4),
    DOUBLE("0.0", ".double", 8),
    VOID("", "void", 0),
    STRING("0", ".word", 0);
//values
    private final String initialValue;
    private final String signature;
    private final int align;

//constructors
    PrimitType(String initialValue, String signature, int align) {
        this.initialValue = initialValue;
        this.signature = signature;
        this.align = align;
    }

//getters
    public String getSignature() {
        return signature;
    }
    public int getAlign() {
        return align;
    }

    @Override
    public PrimitType getPrimitive() {
        return this;
    }
    @Override
    public String toString() {
        return signature;
    }
    @Override
    public String getInitialValue() {
        return this.initialValue;
    }
}
