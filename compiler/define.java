package compiler;
import java.io.File;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Scanner;

public class define {
    public static void main(String[] args) throws Exception {

        File file = new File("C:\\Users\\Iran\\Desktop\\example.txt");
        Scanner scan = new Scanner(file);
        String s = null;
        ArrayList<String> list = new ArrayList();

        while (scan.hasNext()) {
            s = scan.next();

            if (s.equals("define")) {
                list.add(scan.next());
                list.add(scan.next());



            }
        }
        Map<String, String> var = fillmap(list);
        Path path = Paths.get("C:\\Users\\Iran\\Desktop\\example.txt");
        Stream<String> lines;
        try {
            lines = Files.lines(path, Charset.forName("UTF-8"));
            List<String> replaceLines = lines.map(line -> replaceTag(line, var)).collect(Collectors.toList());
            Files.write(path, replaceLines, Charset.forName("UTF-8"));
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filePath = "C:\\Users\\Iran\\Desktop\\example.txt";
        String result = fileToString(filePath);

        //Replacing the word with desired one
        result = result.replaceAll("define\\s\\S*\\s\\S*", "");


        //Rewriting the contents of the file
        PrintWriter writer = new PrintWriter(new File(filePath));
        writer.append(result);
        writer.flush();


    }



    public static Map<String, String> fillmap(ArrayList<String> list) {

        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < list.size(); i += 2) {
            map.put(list.get(i), list.get(i + 1));
        }
        return map;
    }

    private static String replaceTag(String str, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (str.contains(entry.getKey()))
                str = str.replace(entry.getKey(), entry.getValue());
        }
        return str;
    }
    public static String fileToString(String filePath) throws Exception{
        String input = null;
        Scanner sc = new Scanner(new File(filePath));
        StringBuffer sb = new StringBuffer();
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            sb.append(input);
        }
        return sb.toString();
    }
}



