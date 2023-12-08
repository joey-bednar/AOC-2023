package aoc.day3;

import aoc.utils.Utils;
import java.util.ArrayList;

class Direction {
    int x;
    int y;

    public Direction(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}

public class Main {


    final static Direction[] dirs = new Direction[]{
            new Direction(-1,0),
            new Direction(1,0),
            new Direction(0,1),
            new Direction(-1,1),
            new Direction(1,1),
            new Direction(0,-1),
            new Direction(1,-1),
            new Direction(-1,-1),
    };
    private static boolean hasAdjSymbol(ArrayList<String> lines, int i,int j) {

        for (Direction dir : dirs) {
            int dx = dir.getX() + i;
            int dy = dir.getY() + j;
            try {
                char sym = lines.get(dy).charAt(dx);
                if (sym != '.' && !Character.isDigit(sym)) {
                    return true;
                }
            } catch (Exception ignore) {
                //Exception.ignore(e);
            }
        }
        return false;
    }


    private static ArrayList<Direction> getAdjNums(ArrayList<String> lines, int i,int j) {
        ArrayList<Direction> found = new ArrayList<>();
        for (int k=0;k<dirs.length;k++) {
            int dx = dirs[k].getX() + i;
            int dy = dirs[k].getY() + j;
            try {
                char sym = lines.get(dy).charAt(dx);
                if (Character.isDigit(sym) && sym != '.') {
                    found.add(dirs[k]);

                    // if up/down has number, don't
                    // check for diagonal up/down
                    if (k == 2 || k == 5) {
                        k += 2;
                    }
                }
            } catch (Exception ignore) {
                //Exception.ignore(e);
            }
        }
        return found;
    }

    private static int expandNum(String line,int i) {
        if (!Character.isDigit(line.charAt(i))) {
            return 0;
        }

        int start = i;
        int end = i;

        while (end+1 < line.length()) {
            if (!Character.isDigit(line.charAt(end+1))) {
                break;
            }
            end += 1;
        }
        while (start-1 >= 0) {
            if (!Character.isDigit(line.charAt(start-1))) {
                break;
            }
            start -= 1;
        }

        int num = 0;
        for (int x=start;x<=end;x++) {
            num = 10*num + Character.getNumericValue(line.charAt(x));
        }
        return num;

    }

    private static int part1(ArrayList<String> lines) {

        int sum = 0;
        int num = 0;

        for (int j=0;j<lines.size();j++) { // iterate through lines
            boolean adjSym = false;
            sum += num; // ensures numbers are added after broken by newline

            for (int i=0;i<lines.get(0).length();i++) { // iterate through chars

                char c = lines.get(j).charAt(i);

                if (Character.isDigit(c)) {
                    // check if number has adjacent symbol
                    if (!adjSym) {
                        adjSym = hasAdjSymbol(lines,i,j);
                    }

                    // add digit to end of number
                    int digit = Character.getNumericValue(c);
                    num = num*10 + digit;
                } else  {
                    // when no more digits found in number,
                    // add results to sum of has adjacent symbol
                    if (adjSym) {
                        sum += num;
                    }
                    adjSym = false;
                    num = 0;
                }
            }
        }
        return sum;
    }

    private static int part2(ArrayList<String> lines) {

        int sum = 0;

        for (int j = 0; j < lines.size(); j++) {
            for (int i = 0; i < lines.get(0).length(); i++) {
                char c = lines.get(j).charAt(i);
                if (c == '*') {
                    ArrayList<Direction> found = getAdjNums(lines, i, j);
                    if (found.size() == 2) {
                        int num = 1;
                        for (Direction direction : found) {
                            int x = direction.getX() + i;
                            int y = direction.getY() + j;
                            int f = expandNum(lines.get(y), x);
                            num *= f;
                        }
                        sum += num;
                    }
                }
            }
        }
        return sum;
    }
    public static void main(String[] args) {

        ArrayList<String> lines = Utils.getAllLines("day3/input.txt");
        int sum = part1(lines);
        System.out.println(sum);

        sum = part2(lines);
        System.out.println(sum);

    }
}