package Algorithms.BitAlgorithms;


import java.util.ArrayList;

/**
 * @author Harish T
 */
public class easyBitOperations {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 4, 3, 1, 2};
        System.out.println(alternateOdds(9));
    }

    public static int findDuplicateInArray(int[] array) {
        int temp = array[0];
        for (int i = 1; i < array.length; i++) {
            temp = temp ^ array[i];
        }
        return temp;
    }

    public static int multiplyByBits(int a, int b) {
        return 0;
    }

    public static boolean oppositeNum(int a, int b) {
        return ((a ^ b) < 0);
    }

    public static int addOneToNum(int a) {
        int m = 1;
        while ((a & m) >= 1) {
            a = a ^ m;
            m = m << 1;
        }
        a = a ^ m;
        return a;
    }

    public static int multiplyBy3andHalf(int a) {
        return (a << 1) + (a) + (a >> 1);
    }

    public static int turnOffRightMostSetBit(int a) {
        int m = 1;
        while ((a & m) == 0) {
            m = m << 1;
        }
        return a ^ m;
    }

    public static int turnOffRightMostSetBitSecond(int a) {
        return a & a - 1;
    }

    public static int positionOfRightMostSetBit(int a) {
        int m = 1;
        int count = 1;
        while ((a & m) == 0) {
            m = m << 1;
            ++count;
        }
        return count;
    }

    public static boolean isPowerOfFour(int num) {
        int count = 0;
        int m = num & num - 1;
        if (m == 0) {
            while (num > 1) {
                num = num >> 1;
                count++;
            }
            return count % 2 == 0;
        }
        return false;
    }

    public static int modByPowerOfTwo(int number, int power) {
        int k = 1 << power;
        return number & (k - 1);
    }

    public static int findNoOccurringOddNo(int[] array) {
        int l = array[0];
        for (int i = 1; i < array.length; i++) {
            l ^= array[i];
        }
        return l;
    }

    public static int setBitsCount(int a) {
        int count = 0;
        while (a > 0) {
            if ((a & 1) == 1) {
                ++count;
            }
            a = a >> 1;
        }
        return count;
    }

    public static boolean isPowOfTwo(int num) {
        return (num & (num - 1)) == 0;
    }

    public static void swap(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println(a + " " + b);
    }

    public static void swapByXor(int a, int b) {
        a = a ^ b;//1 0 , 0 1-> 1 1
        b = a ^ b;//1 1 , 0 1-> 1 0
        a = a ^ b;//1 1 , 1 0-> 0 1
        System.out.println(a + " " + b);
    }

    public static int posOfOnlySetBit(int a) {
        int count = 0;
        while (a > 0) {
            a = a >> 1;
            ++count;
        }
        return count;
    }

    public static int numofFlipNeededToSwap(int a, int b) {
        int k = a ^ b;
        int count = 0;
        while (k > 0) {
            count += k & 1;
            k >>= 1;
        }
        return count;
    }

    public static void binaryRep(int a, ArrayList<Integer> array) {
        while (a > 0) {
            array.add(a & 1);
            a >>= 1;
        }
    }

    public static int multiplication(int a, int b) {
        int multipliedValue = 0;
        while (b > 0) {
            if ((b & 1) > 0) {
                multipliedValue += a;
            }
            b >>= 1;
            a <<= 1;
        }
        return multipliedValue;
    }

    public static int swapNibble(int a) {
        return (a >> 4) | ((a << 4) & 0xF0);
    }

    public static String toggle(String a) {
        char[] chars = a.toCharArray();
        StringBuilder strBuild = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            strBuild.append((char) (chars[i] ^ 32));
        }
        return strBuild.toString();
    }

    public static int XORWithoutXOR(int a, int b) {
        return (a | b) & (-a | -b);
    }

    public static int toggleKthBit(int num, int k) {
        k = 1 << (k - 1);
        return num ^ k;
    }

    public static int twoComplement(int num) {
        num = ~num;
        return addOneToNum(num);
    }

    public static int notToggleKthBit(int num, int k) {
        return ~toggleKthBit(num, k);
    }

    public static int toggleLastMbits(int m) {
        return m ^ ((1 << m) - 1);
    }

    public static boolean isDivBy4(int n) {
        return (n & 3) == 0;
    }

    public static int toggleExceptFirstAndLast(int a) {
        int m = mostSigBit(a);
        return a ^ (m - 1) ^ 1;
    }

    public static int mostSigBit(int a) {
        a |= a >> 1;
        a |= a >> 2;
        a |= a >> 4;
        a |= a >> 8;
        a |= a >> 16;
        return (a + 1) >> 1;
    }

    public static int alternateEven(int no) {
        int prev = 1;
        while (no > 3) {
            prev = (prev << 2) + 1;
            no >>= 2;
        }
        return prev;
    }

    public static int alternateOdds(int num) {
        int prev = 2;
        while (num > 3) {
            prev = (prev << 2) + 2;
            num >>= 2;
        }
        return prev;
    }

    public static int toggleEven(int num) {
        int coeff = alternateEven(num);
        return num ^ coeff;
    }

}
