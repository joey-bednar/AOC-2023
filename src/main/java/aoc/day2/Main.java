package aoc.day2;

import aoc.utils.Utils;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final int[] MAX_CUBES = {12,13,14};
    private static final String[] colors = {"r","g","b"};

    private static boolean isPossible(String line) {
        for (int i=0;i<colors.length;i++) {
            String pattern = "([0-9])*(?= "+colors[i]+")";
            Matcher m = Pattern.compile(pattern).matcher(line);

            while (m.find()) {
                String cnt = m.group(0);
                if (cnt.isEmpty()) {
                    continue;
                }
                if (Integer.parseInt(cnt) > MAX_CUBES[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[] fewestCubes(String line) {

        int[] minCubes = {0,0,0};

        for (int i=0;i<colors.length;i++) {
            String pattern = "([0-9])*(?= "+colors[i]+")";
            Matcher m = Pattern.compile(pattern).matcher(line);

            while (m.find()) {
                String s = m.group(0);
                if (s.isEmpty()) {
                    continue;
                }

                int cnt = Integer.parseInt(s);
                if (cnt > minCubes[i]) {
                    minCubes[i] = cnt;
                }
            }
        }
        return minCubes;
    }

    private static int getId(String line) {
        String gamePattern = "Game ([0-9]*):";
        Matcher m_game = Pattern.compile(gamePattern).matcher(line);
        if (m_game.find()) {
            String s = m_game.group(1);
            if (!s.isEmpty()) {
                return Integer.parseInt(s);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = Utils.getInput("day2/input.txt");

        int sumIds = 0;
        int powCubes = 0;

        while (sc != null && sc.hasNextLine()) {
            String line = sc.nextLine();

            int id = getId(line);
            boolean possible = isPossible(line);
            int[] cubes = fewestCubes(line);

            if (possible) {
                sumIds += id;
            }
            powCubes += cubes[0]*cubes[1]*cubes[2];
        }
        System.out.println("Sum possible ids: "+sumIds);
        System.out.println("Power of cubes: "+powCubes);

    }
}