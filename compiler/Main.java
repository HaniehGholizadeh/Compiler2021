package compiler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;

public class Main {
    public static boolean run(File inputFile) throws Exception {
        StringBuilder str = new StringBuilder();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream original = System.out;
        System.setOut(ps);
        Scanner scanner = new compiler.Scanner(new FileReader(inputFile));
        parser parser = new compiler.parser(scanner);
//        System.err.close();
        try {
            parser.parse();
//            System.out.println("OK");
            return true;
        } catch (Exception e) {
//            System.out.println("Syntax Error");
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        StringBuilder str = new StringBuilder();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream original = System.out;
//        System.setOut(ps);
        Scanner scanner = new compiler.Scanner(new FileReader("compiler/samples/t001-class1.in"));
        parser parser = new compiler.parser(scanner);
//      System.err.close();
        parser.parse();
        System.out.println("OK");
    }
}

