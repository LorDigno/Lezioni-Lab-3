import java.io.*;
public class ListFiles {
    public static void main(String[] args) {
        File dir = new File("."); // current working directory

        if (dir.isDirectory()) {
            // List only files that meet the filtering criteria
            String[] files = dir.list();
            
            for (String file : files) {
                if (file.endsWith(".java"))
                System.out.println(file);
            }
        }
    }
}