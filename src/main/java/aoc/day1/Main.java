package aoc.day1;

import java.util.Scanner;
import aoc.utils.Utils;

public class Main {

    public static void main(String[] args) {
        Scanner sc = Utils.getInput("day1/input.txt");

        int number = 0;
        while (sc != null && sc.hasNextLine()) {

            char[] chars = sc.nextLine().toCharArray();

            int ptr1 = 0;
            int ptr2 = chars.length - 1;
            boolean d1 = false;
            boolean d2 = false;

            while (!d1 || !d2) {
                char c1 = chars[ptr1];
                char c2 = chars[ptr2];

                if (!d1 && Character.isDigit(c1)) {
                    d1 = true;
                    number += 10 * Character.getNumericValue(c1);
                }
                if (!d2 && Character.isDigit(c2)) {
                    d2 = true;
                    number += Character.getNumericValue(c2);
                }
                ptr1++;
                ptr2--;
            }
        }
        System.out.println(number);
    }
}