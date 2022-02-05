package compiler.CGen;

import compiler.AST.*;
import compiler.CGen.*;
import compiler.Scope.*;
import compiler.Vtable.*;
import compiler.Parser.*;
import compiler.Scanner.*;

import java.io.FileWriter;
import java.io.Writer;

public class CodeGen {

    private static final SymbolTable spaghettiStack = new SymbolTable();
    private static final VTable vTable = new VTable();
    private static final int USER_INPUT_SIZE = 20;
    public static String DS = ".data\n";
    public static String TS = ".text\n";
    private static int LoopStmtCounter = 0;
    private static int ConditionStmtCounter = 0;
    private static int StringCounter = 0;
    private static int ArgumentCounter = 0;

    private static int readLineCounter = 0;

    static {
        DS += "\tnewLine: \t.asciiz \t\"\\n\"\n";
        DS += "\tbool_0: \t.asciiz \t\"false\"\n";
        DS += "\tbool_1: \t.asciiz \t\"true\"\n";
        DS += "\tzeroDouble: \t.double \t0.0\n";
        TS += "\tldc1\t$f0, zeroDouble\n";
        TS += "\tjal\tmain\n";
        TS += "PrintBool:\n" +
                "\tbeq\t$a0, 0, Print_Bool0\n" +
                "\tla\t$v1, bool_1\n" +
                "\tb\tPrint_Bool1\n" +
                "Print_Bool0:\n" +
                "\tla\t$v1, bool_0\n" +
                "Print_Bool1:\n" +
                "\tjr\t$ra\n";
    }

    public static void cgen(Node node) throws Exception {
        if (node.getNodeType() == NodeType.START) {
            cgenStart(node);
        }
        else if (node.getNodeType() == NodeType.METHOD_DEC) {
            cgenMethodDeclaration(node, false);
        }
        else if (node.getNodeType() == NodeType.BLOCK) {
            cgenBlock(node);
        }
        else if (node.getNodeType() == NodeType.VAR_DEC) {
            cgenVariableDecl(node);
        }
        else if (node.getNodeType() == NodeType.IDENTIFIER) {
            cgenIdentifier(node);
        }
        else if (node.getNodeType() == NodeType.ASSIGN) {
            cgenAssign(node);
        }
        else if (node.getNodeType() == NodeType.READ_INTEGER) {
            cgenReadInteger(node);
        }
        else if (node.getNodeType() == NodeType.READ_LINE) {
            cgenReadLine(node);
        }
        else if (node.getNodeType() == NodeType.EXPR_STM) {
            cgenExpressionStatement(node);
        }
        else if (node.getNodeType() == NodeType.LITERAL) {
            cgenLiteral((Literal) node);
        }
        else if (node.getNodeType() == NodeType.UNARY_MINUS) {
            cgenUnaryMinus(node);
        }
        else if (
                node.getNodeType() == NodeType.ADD
                        ||node.getNodeType() == NodeType.SUB
                        ||node.getNodeType() == NodeType.MUL
                        ||node.getNodeType() == NodeType.DIV
                        ||node.getNodeType() == NodeType.MOD)
        {
            cgenArithmeticCalculation(node);
        }

        else if (node.getNodeType() == NodeType.PRINT_STM) {
            cgenPrint(node);
        }
        else if (node.getNodeType() == NodeType.ARGUMENT) {
            cgenArgument(node);
        }
        else if (node.getNodeType() == NodeType.FUNCTION_CALL) {
            cgenFunctionCall(node);
        }
        else if (node.getNodeType() == NodeType.PARAMETERS) {
            cgenParameters(node);
        }
        else if (node.getNodeType() == NodeType.RETURN_STM) {
            cgenReturn(node);
        }
        else if (node.getNodeType() == NodeType.IF_STM) {
            cgenIfStatement(node);
        }
        else if (node.getNodeType() == NodeType.WHILE_STM) {
            cgenWhileStatement(node);
        }
        else if (node.getNodeType() == NodeType.FOR_STM) {
            cgenForStatement(node);
        }
        else if (node.getNodeType() == NodeType.BREAK_STM) {
            cgenBreak(node);
        }
        else if (node.getNodeType() == NodeType.CONTINUE_STM) {
            cgenContinue(node);
        }
        else if (node.getNodeType() == NodeType.EQUAL
                ||node.getNodeType() == NodeType.NOT_EQUAL)
        {
            cgenEqNeq(node);
        }
        else if (node.getNodeType() == NodeType.GREATER_THAN
                ||node.getNodeType() == NodeType.G_T_OR_EQ
                ||node.getNodeType() == NodeType.LESS_THAN
                ||node.getNodeType() == NodeType.L_T_OR_EQ)
        {
            cgenCompare(node);
        }
        else if (node.getNodeType() == NodeType.AND_LOGIC
                ||node.getNodeType() == NodeType.OR_LOGIC
                ||node.getNodeType() == NodeType.NOT_LOGIC)
        {
            cgenBooleanCalculation(node);
        }
        else
            cgenAllChildren(node);
    }

    private static void cgenStart(Node node) throws Exception {


        for (Node child : node.getChildren()) {
            if (child.getNodeType().equals(NodeType.METHOD_DEC))
                cgenMethodDeclaration(child, true);
        }


        for (Node child : node.getChildren()) {
            cgen(child);
        }
    }

    private static void cgenMethodDeclaration(Node node, boolean isFirstPass) throws Exception {

        PrimitNode returnNode = (PrimitNode) node.getChild(0);
        Type methodType = returnNode.getType();

        IDNode IDNode = (IDNode) node.getChild(1);
        String methodName = IDNode.getv();

        if (isFirstPass) {
            CodeForFunct code = new CodeForFunct(methodName, "", methodType);
            spaghettiStack.enterScope(String.valueOf(node.getChild(1)), BlockType.METHOD, true);
            vTable.addFunction(methodName, code);
            cgen(node.getChild(2));
            spaghettiStack.leaveScope();
        } else {
            TS += methodName + ":\n";

            pushRegistersA();

            spaghettiStack.enterScope(String.valueOf(node.getChild(1)), BlockType.METHOD, false);

            cgen(node.getChild(3));
            if (methodName.equals("main")) {
                TS += "\t# This line is going to signal end of program.\n";
                TS += "\tli\t$v0, 10\n";
                TS += "\tsyscall\n";
            } else {
                popRegistersA();
                TS += "\tjr\t$ra\n";
            }
        }
    }

    private static void cgenLiteral(Literal node) throws Exception {
        DSCP dscp = new DSCP(node.getType(), null);
        dscp.setValue(String.valueOf(node));
        node.setDSCP(dscp);
        node.getParent().setDSCP(dscp);

        if (dscp.getType().equals(PrimitType.STRING)) {
            String stringLabel = spaghettiStack + "_StringLiteral" + SymbolTable.getCurrentScope().getStringLiteralCounter();
            DS += "\t" + stringLabel + ":\t.asciiz\t" + dscp.getValue() + '\n';
            TS += "\tla\t$v1, " + stringLabel + '\n';
            SymbolTable.getCurrentScope().addStringLiteralCounter();
        } else {
            TS += "\tli\t$v1, " + dscp.getValue() + '\n';
        }

        ((Exp) node.getParent()).setIsID();

    }

    private static void cgenUnaryMinus(Node node) throws Exception {
        cgen(node.getChild(0));
        node.setDSCP(node.getChild(0).getDSCP());
        node.getParent().setDSCP(node.getChild(0).getDSCP());
        TS += "\tnot\t$v1, $v1\n";
        TS += "\taddi\t$v1, $v1, 1\n";
    }

    private static void cgenIdentifier(Node node) throws Exception {
        IDNode IDNode = (IDNode) node;
        String entry = IDNode.getv();
        if (node.getParent().getNodeType().equals(NodeType.FUNCTION_CALL)) {
            cgenAllChildren(node);
            TS += "\taddi\t$sp, $sp, -4\n";
            TS += "\tsw\t$ra, 0($sp)\n";
            TS += "\tjal\t" + node + '\n';
            TS += "\tlw\t$ra, 0($sp)\n";
            TS += "\taddi\t$sp, $sp, 4\n";
            node.getParent().setDSCP(new DSCP(null, null, true));
        } else if (node.getParent().getNodeType().equals(NodeType.EXPR_STM)) {
            DSCP dscp = spaghettiStack.getDSCP(entry);
            node.setDSCP(dscp);
            ((Exp) node.getParent()).setIsID();

            if (spaghettiStack.getDSCP(entry).isArgument()) {
                int argumentPlace = spaghettiStack.getDSCP(entry).getArgumentPlace() - 1;
                TS += "\tmove\t$v1, $a" + argumentPlace + "\n";
            } else {
                TS += "\tlw\t$v1, " + spaghettiStack.getEntryScope(entry) + "." + entry + "\n";
            }
        }
    }

    private static void cgenExpressionStatement(Node node) throws Exception {
        cgen(node.getChild(0));
        node.setDSCP(node.getChild(0).getDSCP());
    }

    private static void cgenAssign(Node node) throws Exception {
        IDNode IDNode = (IDNode) node.getChild(0);
        Exp Exp = (Exp) node.getChild(1);

        cgen(Exp);

        String entry = IDNode.toString();
        DSCP identifierDSCP = spaghettiStack.getDSCP(entry);

        if (!identifierDSCP.getType().equals(Exp.getDSCP().getType()))
            throw new SemanticError("error");

        if (Exp.getDSCP().getType().equals(PrimitType.STRING)) {
            identifierDSCP.setValue(Exp.getres());
        }

        if ((Exp.getres() != null) && Exp.getres().equals("\"ReadLine()\"")) {
            String stringLabel = "readLine_number" + (readLineCounter - 1);
            TS += "\tla\t$v1, " + stringLabel + '\n';
        }

        TS += "\tsw\t$v1  " + spaghettiStack.getEntryScope(entry).toString() + "." + entry + "\t\t\t\t\t\t# End assign\n";

        if (node.getChildren().size() == 3)
            cgen(node.getChild(2));
    }

    private static void cgenReadInteger(Node node) throws Exception {
        DSCP dscp = new DSCP(PrimitType.INT, null);
        dscp.setValue("readInteger()");
        node.setDSCP(dscp);
        pushRegistersA();
        TS += "\tli\t$v0, 5\n";
        TS += "\tsyscall\n";
        TS += "\tmove\t$v1, $v0\n";
        popRegistersA();
        ((Exp) node.getParent()).setIsID();
    }

    private static void cgenReadLine(Node node) throws Exception {
        DS += "\treadLine_number" + readLineCounter + ":\t.space\t" + USER_INPUT_SIZE + '\n';
        DSCP dscp = new DSCP(PrimitType.STRING, null);
        dscp.setValue("readLine()");
        node.setDSCP(dscp);
        ((Exp) node.getParent()).setIsID();
        pushRegistersA();
        TS += "\tli\t$v0, 8\n";
        TS += "\tla\t$a0, " + "readLine_number" + readLineCounter++ + '\n';
        TS += "\tli\t$a1, " + USER_INPUT_SIZE + '\n';
        TS += "\tsyscall\n";
        TS += "\tmove\t$v1, $v0\n";
        popRegistersA();
        ((Exp) node.getParent()).setIsID();
    }

    private static void cgenVariableDecl(Node node) throws Exception {
        Type typePrimitive = ((PrimitNode) node.getChild(0)).getType();
        IDNode IDNode = (IDNode) node.getChild(1);

        String data_id = spaghettiStack + "." + IDNode.getv() + ':';
        DS += '\t' + data_id + '\t' + typePrimitive.getSignature() + '\t' + typePrimitive.getInitialValue() + '\n';

        if (node.getParent().getNodeType().equals(NodeType.ARGUMENT)) {
            DSCP dscp = new DSCP(typePrimitive, IDNode);
            dscp.setArgumentTrue(getArgumentCounter());
            spaghettiStack.addEntry(IDNode.getv(), dscp);


            vTable.getFunction(node.getParent().getParent().getParent().getChild(1).toString()).addArgument((compiler.AST.PrimitType) typePrimitive);
        } else {
            DSCP dscp = new DSCP(typePrimitive, IDNode);
            spaghettiStack.addEntry(IDNode.getv(), dscp);
        }
    }

    private static void cgenBlock(Node node) throws Exception {
        cgenAllChildren(node);
        spaghettiStack.leaveScope();
    }

    private static void cgenAllChildren(Node node) throws Exception {
        for (Node child : node.getChildren()) {
            cgen(child);
        }
    }

    private static void cgenArithmeticCalculation(Node node) throws Exception {
        pushRegistersS();
        Exp leftChild = (Exp) node.getChild(0);
        cgen(leftChild);
        if (leftChild.getDSCP().getType().equals(PrimitType.INT)) {
            TS += "\tmove\t$s0, $v1\n";
        } else if (leftChild.getDSCP().getType().equals(PrimitType.DOUBLE)) {
            TS += "\tmfc1.d\t$s0, $f10\n";
        }

        Exp rightChild = (Exp) node.getChild(1);
        cgen(rightChild);
        if (rightChild.getDSCP().getType().equals(PrimitType.INT)) {
            TS += "\tmove\t$s2, $v1\n";
        } else if (rightChild.getDSCP().getType().equals(PrimitType.DOUBLE)) {
            TS += "\tmfc1.d\t$s2, $f10\n";
        }

        Type type = widen(leftChild, rightChild, false);
        DSCP dscp = new DSCP(type, null);

        if (type.equals(PrimitType.INT)) {

            if (node.getNodeType() == NodeType.ADD) {
                TS += "\tadd\t$v1, $s0, $s2\n";
            }
            else if (node.getNodeType() == NodeType.SUB) {
                TS += "\tsub\t$v1, $s0, $s2\n";
            }
            else if (node.getNodeType() == NodeType.MUL) {
                TS += "\tmul\t$v1, $s0, $s2\n";
            }
            else if (node.getNodeType() == NodeType.DIV) {
                TS += "\tdiv\t$v1, $s0, $s2\n";
            }
            else if (node.getNodeType() == NodeType.MOD) {
                TS += "\trem\t$v1, $s0, $s2\n";
            }
        }
        else if (type.equals(PrimitType.DOUBLE)) {

        } else if (type.equals(PrimitType.STRING)) {
            if (!node.getNodeType().equals(NodeType.ADD))
                throw new SemanticError("error");
            String finalString = (leftChild.getres() + rightChild.getres()).replaceAll("\"", "");
            dscp.setValue(finalString);
            String stringLabel = spaghettiStack + "_StringLiteral" + SymbolTable.getCurrentScope().getStringLiteralCounter();
            DS += "\t" + stringLabel + ":\t.asciiz\t\"" + dscp.getValue() + "\"\n";
            TS += "\tla\t$v1, " + stringLabel + '\n';
            SymbolTable.getCurrentScope().addStringLiteralCounter();
        }
        popRegistersS();

        node.setDSCP(dscp);
        Exp parent = (Exp) node.getParent();
        parent.setIsID();
    }

    private static void cgenPrint(Node node) throws Exception {
        Node inputNodes = node.getChild(0);
        for (Node child : inputNodes.getChildren()) {
            cgen(child.getChild(0));
            if(child.getChild(0).getDSCP().getType().getPrimitive() == PrimitType.INT){
                TS += "\tli\t$v0, 1\n";
                TS += "\tadd\t$a0, $v1, $zero\n";
                TS += "\tsyscall\n";

            }
            else if(child.getChild(0).getDSCP().getType().getPrimitive() == PrimitType.DOUBLE){
                TS += "\tli\t$v0, 3\n";
                TS += "\tadd.d\t$f12, $f10, $f0\n";
                TS += "\tsyscall\n";

            }
            else if(child.getChild(0).getDSCP().getType().getPrimitive() == PrimitType.BOOL){
                TS += "\tmove\t$a0, $v1\n";
                TS += "\tsw\t$ra, 0($sp)\n";
                TS += "\tjal\tPrintBool\n";
                TS += "\tlw\t$ra, 0($sp)\n";

            }
            else if(child.getChild(0).getDSCP().getType().getPrimitive() == PrimitType.STRING){
                TS += "\tli\t$v0, 4\n";
                TS += "\tmove\t$a0, $v1\n";
                TS += "\tsyscall\n";

            }
        }

        TS += "\tli\t$v0, 4\n"
                + "\tla\t$a0, newLine\n"
                + "\tsyscall\n";


        if (node.getChildren().size() == 2){
            cgen(node.getChild(1));
        }
    }

    private static void cgenArgument(Node node) throws Exception {
        addArgumentCounter();
        cgenVariableDecl(node.getChild(0));
    }

    private static void cgenFunctionCall(Node node) throws Exception {
        cgen(node.getChild(1));
        cgen(node.getChild(0));

        IDNode IDNode = (IDNode) node.getChild(0);
        DSCP dscp = new DSCP(vTable.getFunction(IDNode.getv()).getReturnType(), null, node.getDSCP().isFunction());
        node.setDSCP(dscp);
    }

    private static void cgenParameters(Node node) throws Exception {
        for (int i = 0; i < node.getChild(0).getChildren().size(); i++) {
            pushRegistersA();
            cgenAllChildren(node.getChild(0).getChild(i));
            popRegistersA();
            TS += "\tmove\t$a" + i + ", $v1\n";

            String functionName = String.valueOf(node.getParent().getChild(0));
            if (!node.getChild(0).getChild(i).getDSCP().getType().equals(vTable.getFunction(functionName).getArgument(i)))
                throw new SemanticError("error");
        }
    }

    private static void cgenReturn(Node node) throws Exception {
        cgen(node.getChild(0));


        Node nodeCrawler = node;
        while (!nodeCrawler.getNodeType().equals(NodeType.METHOD_DEC)) {
            nodeCrawler = nodeCrawler.getParent();
        }

        PrimitType returnType = (PrimitType) ((PrimitNode) nodeCrawler.getChild(0)).getType();
        if (node.getChild(0).getDSCP() == null) {
            if (!returnType.equals(PrimitType.VOID)) {
                throw new SemanticError("error");
            }
        } else if (!node.getChild(0).getDSCP().getType().equals(returnType)) {
            throw new SemanticError("error");
        }

        String methodName = ((IDNode) nodeCrawler.getChild(1)).getv();

        if (methodName.equals("main")) {
            TS += "\t# This line is going to signal end of program.\n";
            TS += "\tli\t$v0, 10\n";
            TS += "\tsyscall\n";
        } else {
            popRegistersA();
            TS += "\tjr\t$ra\n";
        }


        cgen(node.getChild(1));
    }

    private static void cgenIfStatement(Node node) throws Exception {
        addConditionStmtCounter();

        String entry = "IfStmt_" + getConditionStmtCounter();
        String labelName = spaghettiStack + "_" + entry;

        String entryElse = "ElseStmt_" + getConditionStmtCounter();    // Scope name for Else
        String labelNameElse = spaghettiStack + "_" + entryElse;

        spaghettiStack.enterScope(entry, BlockType.CONDITION, true);

        cgen(node.getChild(0));
        DSCP conditionDscp = node.getChild(0).getDSCP();
        if (!conditionDscp.getType().equals(PrimitType.BOOL)) {
            throw new SemanticError("error");
        }


        TS += "\tbeq\t$v1, 0, " + labelName + '\n';
        cgen(node.getChild(1));

        if (node.getChildren().size() == 4) {
            TS += "\tb\t" + labelNameElse + '\n';
        }

        TS += labelName + ":\n";

        if (node.getChildren().size() == 3) {
            addConditionStmtCounter();
            cgen(node.getChild(2));
        } else {
            spaghettiStack.enterScope(entryElse, BlockType.CONDITION, true);
            cgen(node.getChild(2));
            TS += labelNameElse + ":\n";
            addConditionStmtCounter();
            cgen(node.getChild(3));
        }

    }

    private static void cgenWhileStatement(Node node) throws Exception {
        addLoopStmtCounter();

        String entry = "LoopStmt_" + getLoopStmtCounter();
        String labelNameFirst = spaghettiStack + "_" + entry + "_start";
        String labelNameUpdate = spaghettiStack + "_" + entry + "_update";
        String labelNameEnd = spaghettiStack + "_" + entry + "_end";

        spaghettiStack.enterScope(entry, BlockType.LOOP, true);

        TS += labelNameFirst + ":\n";

        cgen(node.getChild(0));
        DSCP conditionDscp = node.getChild(0).getDSCP();
        if (!conditionDscp.getType().equals(PrimitType.BOOL)) {
            throw new SemanticError("error");
        }


        TS += "\tbeq\t$v1, 0, " + labelNameEnd + '\n';
        TS += labelNameUpdate + ":\n";
        cgen(node.getChild(1));
        TS += "\tb\t" + labelNameFirst + '\n';
        TS += labelNameEnd + ":\n";
        cgen(node.getChild(2));
    }

    private static void cgenForStatement(Node node) throws Exception {
        addLoopStmtCounter();
        String entry = "LoopStmt_" + getLoopStmtCounter();
        String labelNameFirst = spaghettiStack + "_" + entry + "_start";
        String labelNameUpdate = spaghettiStack + "_" + entry + "_update";
        String labelNameEnd = spaghettiStack + "_" + entry + "_end";

        spaghettiStack.enterScope(entry, BlockType.LOOP, true);

        cgen(node.getChild(0));

        TS += labelNameFirst + ":\n";

        cgen(node.getChild(1));
        DSCP conditionDscp = node.getChild(1).getDSCP();
        if (!conditionDscp.getType().equals(PrimitType.BOOL)) {
            throw new SemanticError("error");
        }

        TS += "\tbeq\t$v1, 0, " + labelNameEnd + '\n';
        cgen(node.getChild(3));
        TS += labelNameUpdate + ":\n";
        cgen(node.getChild(2));
        TS += "\tb\t" + labelNameFirst + '\n';
        TS += labelNameEnd + ":\n";
        cgen(node.getChild(4));
    }

    private static void cgenBreak(Node node) throws Exception {
        Scope scopeCrawler = SymbolTable.getCurrentScope();
        Node nodeCrawler = node;
        while (!scopeCrawler.getBlockType().equals(BlockType.LOOP)) {
            while (!nodeCrawler.getNodeType().equals(NodeType.BLOCK)) {
                nodeCrawler = nodeCrawler.getParent();
            }
            nodeCrawler = nodeCrawler.getParent();
            scopeCrawler = scopeCrawler.getParent();
        }

        String entry = "LoopStmt_" + getLoopStmtCounter();
        String labelNameEnd = scopeCrawler.getParent() + "_" + entry + "_end";
        TS += "\tb\t" + labelNameEnd + "\n";


        if (node.getChildren().size() != 0)
            cgen(node.getChild(0));
    }

    private static void cgenContinue(Node node) throws Exception {


        Scope scopeCrawler = SymbolTable.getCurrentScope();
        Node nodeCrawler = node;
        while (!scopeCrawler.getBlockType().equals(BlockType.LOOP)) {
            while (!nodeCrawler.getNodeType().equals(NodeType.BLOCK)) {
                nodeCrawler = nodeCrawler.getParent();
            }
            nodeCrawler = nodeCrawler.getParent();
            scopeCrawler = scopeCrawler.getParent();
        }

        String entry = "LoopStmt_" + getLoopStmtCounter();
        String labelNameEnd = scopeCrawler.getParent() + "_" + entry + "_update";
        TS += "\tb\t" + labelNameEnd + "\n";
        if (node.getChildren().size() != 0)
            cgen(node.getChild(0));
    }

    private static void cgenEqNeq(Node node) throws Exception {
        pushRegistersS();
        Exp leftChild = (Exp) node.getChild(0);
        cgen(leftChild);
        TS += "\tmove\t$s0, $v1\n";

        Exp rightChild = (Exp) node.getChild(1);
        cgen(rightChild);
        TS += "\tmove\t$s2, $v1\n";

        Type type = widen(leftChild, rightChild, true);

        if (node.getNodeType().equals(NodeType.EQUAL))
            TS += "\tseq\t$v1, $s0, $s2\n";
        else if (node.getNodeType().equals(NodeType.NOT_EQUAL))
            TS += "\tsne\t$v1, $s0, $s2\n";

        if (type.equals(PrimitType.STRING)) {
            String leftValue = leftChild.getDSCP().getValue();
            String rightValue = rightChild.getDSCP().getValue();
            if (node.getNodeType().equals(NodeType.EQUAL)) {
                if (leftValue.equals(rightValue))
                    TS += "\taddi\t$v1, $zero, 1\n";
                else
                    TS += "\taddi\t$v1, $zero, 0\n";
            } else {
                if (leftValue.equals(rightValue))
                    TS += "\taddi\t$v1, $zero, 0\n";
                else
                    TS += "\taddi\t$v1, $zero, 1\n";
            }

        }

        popRegistersS();

        DSCP dscp = new DSCP(PrimitType.BOOL, null);
        node.setDSCP(dscp);
        Exp parent = (Exp) node.getParent();
        parent.setDSCP(dscp);
    }

    private static void cgenCompare(Node node) throws Exception {
        pushRegistersS();
        Exp leftChild = (Exp) node.getChild(0);
        cgen(leftChild);
        if (leftChild.getDSCP().getType().equals(PrimitType.INT)) {
            TS += "\tmove\t$s0, $v1\n";
        } else if (leftChild.getDSCP().getType().equals(PrimitType.DOUBLE)) {
            TS += "\tmfc1.d\t$s0, $f10\n";
        }

        Exp rightChild = (Exp) node.getChild(1);
        cgen(rightChild);
        if (rightChild.getDSCP().getType().equals(PrimitType.INT)) {
            TS += "\tmove\t$s2, $v1\n";
        } else if (rightChild.getDSCP().getType().equals(PrimitType.DOUBLE)) {
            TS += "\tmfc1.d\t$s2, $f10\n";
        }

        Type type = widen(leftChild, rightChild, false);

        if (type.equals(PrimitType.INT)) {
            if(node.getNodeType() == NodeType.GREATER_THAN){
                TS += "\tsgt\t$v1, $s0, $s2\n";
            }
            else if(node.getNodeType() == NodeType.G_T_OR_EQ){
                TS += "\tsge\t$v1, $s0, $s2\n";
            }
            else if(node.getNodeType() == NodeType.LESS_THAN){
                TS += "\tslt\t$v1, $s0, $s2\n";
            }
            else if(node.getNodeType() == NodeType.L_T_OR_EQ){
                TS += "\tsle\t$v1, $s0, $s2\n";
            }
        } else if (type.equals(PrimitType.DOUBLE)) {

        }
        popRegistersS();

        DSCP dscp = new DSCP(PrimitType.BOOL, null);
        node.setDSCP(dscp);
        Exp parent = (Exp) node.getParent();
        parent.setDSCP(dscp);
    }

    private static void cgenBooleanCalculation(Node node) throws Exception {
        pushRegistersS();
        Exp leftChild = (Exp) node.getChild(0);
        cgen(leftChild);
        TS += "\tmove\t$s0, $v1\n";

        Exp rightChild = null;
        if (!node.getNodeType().equals(NodeType.NOT_LOGIC)) {
            rightChild = (Exp) node.getChild(1);
            cgen(rightChild);
            TS += "\tmove\t$s2, $v1\n";
            widen(leftChild, rightChild, true);
        } else {
            if (!leftChild.getChild(0).getDSCP().getType().equals(PrimitType.BOOL))
                throw new SemanticError("error");
        }

        if (node.getNodeType() == NodeType.AND_LOGIC) {
            TS += "\tand\t$v1, $s0, $s2\n";
        }
        else if(node.getNodeType() == NodeType.OR_LOGIC){
            TS += "\tor\t$v1, $s0, $s2\n";
        }
        else if(node.getNodeType() == NodeType.NOT_LOGIC){
            TS += "\tneg\t$v1, $s0\n";
            TS += "\tadd\t$v1, $v1, 1\n";
        }



        popRegistersS();

        DSCP dscp = new DSCP(PrimitType.BOOL, null);
        node.setDSCP(dscp);
        Exp parent = (Exp) node.getParent();
        parent.setDSCP(dscp);
    }


    private static void pushRegistersS() {
        TS += "\taddi\t$sp, $sp, -24\n";
        TS += "\tsw\t$s0, 0($sp)\n";
        TS += "\tsw\t$s1, 4($sp)\n";
        TS += "\tsw\t$s2, 8($sp)\n";
        TS += "\tsw\t$s3, 12($sp)\n";
        TS += "\tsw\t$s4, 16($sp)\n";
        TS += "\tsw\t$s5, 20($sp)\n";
    }

    private static void popRegistersS() {
        TS += "\tlw\t$s0, 0($sp)\n";
        TS += "\tlw\t$s1, 4($sp)\n";
        TS += "\tlw\t$s2, 8($sp)\n";
        TS += "\tlw\t$s3, 12($sp)\n";
        TS += "\tlw\t$s4, 16($sp)\n";
        TS += "\tlw\t$s5, 20($sp)\n";
        TS += "\taddi\t$sp, $sp, 24\n";
    }


    private static void pushRegistersA() {
        TS += "\taddi\t$sp, $sp, -16\n";
        TS += "\tsw\t$a0, 0($sp)\n";
        TS += "\tsw\t$a1, 4($sp)\n";
        TS += "\tsw\t$a2, 8($sp)\n";
        TS += "\tsw\t$a3, 12($sp)\n";
    }

    private static void popRegistersA() {
        TS += "\tlw\t$a0, 0($sp)\n";
        TS += "\tlw\t$a1, 4($sp)\n";
        TS += "\tlw\t$a2, 8($sp)\n";
        TS += "\tlw\t$a3, 12($sp)\n";
        TS += "\taddi\t$sp, $sp, 16\n";
    }

    private static Type widen(Exp leftChild, Exp rightChild, boolean isLogical) throws Exception {
        if (leftChild.getDSCP().getType().equals(rightChild.getDSCP().getType())) {
            Type type = leftChild.getDSCP().getType();
            if ((type.equals(PrimitType.INT) || type.equals(PrimitType.DOUBLE) || type.equals(PrimitType.STRING)) && !isLogical)
                return type;
            else if (isLogical)
                return type;
            throw new SemanticError("error");
        }
        throw new SemanticError("error");
    }

    public static void compile(RootNode root) throws Exception {
        cgen(root);
        FileWriter out = new FileWriter("C:\\Users\\Asus\\IdeaProjects\\Compiler2021\\compiler\\tests\\out.asm");
        out.write(DS + TS);
        out.close();
    }

    public static void compile(RootNode root, Writer writer) throws Exception {
        cgen(root);
        writer.write(DS);
        writer.write(TS);
    }

    public static String getdataSegment() {
        return DS;
    }

    public static String gettextSegment() {
        return TS;
    }

    private static int getLoopStmtCounter() {
        return LoopStmtCounter;
    }

    private static void addLoopStmtCounter() {
        LoopStmtCounter++;
    }

    private static int getConditionStmtCounter() {
        return ConditionStmtCounter;
    }

    private static void addConditionStmtCounter() {
        ConditionStmtCounter++;
    }

    private static int getStringLiteralCounter() {
        return StringCounter;
    }

    private static void addStringLiteralCounter() {
        StringCounter++;
    }

    private static int getArgumentCounter() {
        return ArgumentCounter;
    }

    private static void addArgumentCounter() {
        ArgumentCounter++;
    }

}
