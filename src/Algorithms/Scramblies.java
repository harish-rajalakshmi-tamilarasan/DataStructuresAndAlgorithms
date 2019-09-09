package Algorithms;

public class Scramblies {
    public static boolean scramble(String str1, String str2) {
        int str1l=str1.length();
        int str2l=str2.length();
        if (str1l < str2l) {
            return false;
        }
        char[] c1 = new char[str1l];
        str1.getChars(0, str1.length(), c1, 0);
        char[] c2 = new char[str2l];
        str2.getChars(0, str2.length(), c2, 0);

        for (int i = 0; i < c2.length; i++) {
            boolean flag = false;
            for (int j = 0; j < c1.length; j++) {
                if (c2[i] == c1[j]) {
                    c1[j]=0;
                    flag = true;
                    break;
                }
            }
            if (flag==false) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(scramble("scriptjavx","javascript"));
    }
}