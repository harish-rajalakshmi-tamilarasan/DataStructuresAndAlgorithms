package Algorithms;

/**
 * @author Harish T
 */
public class StringMerger {
    public static void main(String[] args) {
        System.out.println(isMerge(";\\K\\dqb '[T;A<Ft.yeds", "\\dqb '[;<ye", ";\\KTAFt.ds"));
    }


    public static boolean isMerge(String s, String part1, String part2) {
        if (s.length() == 0 && part1.length() == 0 && part2.length() == 0) {
            return true;
        }
        char[] charsS = new char[s.length()];
        s.getChars(0, s.length(), charsS, 0);
        char[] charP1 = new char[part1.length()];
        part1.getChars(0, part1.length(), charP1, 0);
        char[] charP2 = new char[part2.length()];
        part2.getChars(0, part2.length(), charP2, 0);
        int a = 0, b = 0;
        for (int i = 0; i < charsS.length; i++) {
            if (a < charP1.length && charsS[i] == charP1[a]) {
                a++;
            } else if (b < charP2.length && charsS[i] == charP2[b]) {
                b++;
            } else {
                return false;
            }
        }
        return (a == charP1.length && b == charP2.length);
    }

}
