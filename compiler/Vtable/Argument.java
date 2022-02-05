package compiler.Vtable;

import compiler.AST.*;
public class Argument {
    int place;
    PrimitType type;

    public Argument(int place, PrimitType type) {
        this.place = place;
        this.type = type;
    }
}
