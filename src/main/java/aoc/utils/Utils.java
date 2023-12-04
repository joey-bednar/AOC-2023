package aoc.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {
    public static Scanner getInput(String input) {
        String path = "src/main/java/aoc/" + input;
        File file = new File(path).getAbsoluteFile();
        try {
            return new Scanner(file);
        } catch(FileNotFoundException e) {
            System.out.println("File not found: ");
        }
        return null;
    }
}
