package compiler;


import compiler.CGen.CodeGen;
import compiler.Parser.*;
import compiler.Scanner.*;

import java.io.*;

import static compiler.CGen.CodeGen.DS;
import static compiler.CGen.CodeGen.TS;



public class Compiler {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader("C:\\Users\\Asus\\IdeaProjects\\Compiler2021\\compiler\\tests\\t002-io2.d");
        Scanner scanner = new Scanner(fr);
        parser parser = new parser(scanner);

        try {
            parser.parse();
        } catch (Exception e){
            throw new SyntaxError(e.getMessage());
        }

        CodeGen.compile(parser.getRoot());


        FileWriter out = new FileWriter("C:\\Users\\Asus\\IdeaProjects\\Compiler2021\\compiler\\tests\\out.asm");
        out.write(DS + TS);
        out.close();
    }

    public static void compile(String read, String writer) throws Exception {
        FileReader fr = new FileReader(read);
        Scanner scanner = new Scanner(fr);
        parser parser = new parser(scanner);

        FileWriter out = new FileWriter(writer);


        String errorCode = null;
        boolean isError = false;

        try {
            parser.parse();
        } catch (Exception e){
            errorCode = SyntaxError.writeError();
            isError = true;
        }

        try {
            CodeGen.compile(parser.getRoot());
            out.write(DS + TS);
            out.close();
        } catch (Exception e){

            isError = true;
        }

        if (isError) {
            out.write("error");
            out.close();
        }
    }
}
