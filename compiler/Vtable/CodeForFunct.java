package compiler.Vtable;

import compiler.AST.*;

import java.util.ArrayList;
//we make the functions that vtable told us we need

public class CodeForFunct {

//values

    private final ArrayList<Argument> arguments;
    private int argumentCounter = 0;
    private final String functName;
    private final String code;
    private final Type returnType;

//constructors
    public CodeForFunct(String functName, String code, Type returnType) {
        this.functName = functName;
        this.code = code;
        this.returnType = returnType;
        this.arguments = new ArrayList<>();
    }


//getters
    public void addArgument(PrimitType PrimitType) {
        arguments.add(new Argument(argumentCounter++, PrimitType));
    }

    public PrimitType getArgument(int placeInArguments) {
        return arguments.get(placeInArguments).type;
    }
    
    public Type getReturnType() {
        return returnType;
    }

    public String getCode() {
        return code;
    }

    public String getFunctName() {
        return functName;
    }

}

