package Important;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * @author Harish T
 */
public class StackDS {
    public static void main(String[] args) {
        System.out.println(patternIncDec("DDIIDDI"));
    }

    public static String infixToPost(String infix) {
        char[] infixArr = infix.toCharArray();
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        for (int i = 0; i < infix.length(); i++) {
            if (infixArr[i] == '(') {
                stack.push('(');
            } else if (infixArr[i] == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop();
                //    postfix.append('(');
            } else if (Character.isLetterOrDigit(infixArr[i])) {
                postfix.append(infixArr[i]);
            } else {
                if (!stack.isEmpty() && prec(infixArr[i]) < prec(stack.peek())) {
                    while (!stack.isEmpty() && prec(infixArr[i]) < prec(stack.peek())) {
                        char temp = stack.pop();
                        if (temp != '(')
                            postfix.append(temp);
                    }
                }
                stack.push(infixArr[i]);
            }
        }
        while (!stack.isEmpty()) {
            char temp = stack.pop();
            if (temp != '(')
                postfix.append(temp);
        }
        return postfix.toString();
    }

    public static String postToInfinx(String postfix) {
        Stack<String> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        String[] strArr = postfix.split("");
        for (int i = 0; i < postfix.length(); i++) {
            if (Character.isLetterOrDigit(strArr[i].charAt(0))) {
                stack.push(strArr[i]);
            } else {
                String secondOp = stack.pop();
                String firstOp = stack.pop();
                stringBuilder.append('(').append(firstOp).append(strArr[i]).append(secondOp).append(')');
                stack.push(stringBuilder.toString());
                stringBuilder.setLength(0);
            }
        }
        return stack.pop();
    }

    public static int prec(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '%':
                return 3;
            case '^':
                return 4;
        }
        return -1;
    }

    public static void mergeInterval(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                Integer first = o1[0];
                Integer second = o2[0];
                return first.compareTo(second);
            }
        });
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] == intervals[i + 1][0]) {
                // merge(intervals[i], intervals[i + 1]);
            }
        }
    }

    public static int maxDepthParenthesis(String str) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                ++count;
                max = max > count ? max : count;
            } else if (str.charAt(i) == ')') {
                if (count <= 0) return -1;
                --count;
            }
        }
        return max;
    }

    public static int validLengthParenthesis(String str) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        int last = -1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    last = i;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        max = Math.max(max, i - last);
                    } else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
        }
        return max;
    }

    public static int changeToBalancedStr(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push('(');
            } else {
                if (stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(')');
                }
            }
        }
        int count = 0;
        char lastSet = '1';
        int stackSize = stack.size();
        for (int i = 0; i < stackSize; i++) {
            if (lastSet == '1') {
                lastSet = stack.pop();
            } else {
                if (lastSet == stack.pop()) {
                    ++count;
                } else {
                    count = count + 2;
                }
            }
        }
        return count;
    }

    public static String patternIncDec(String pattern) {
        int min = 0;
        int length = pattern.length();
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (i < length) {
            if (pattern.charAt(i) == 'I') {
                if (i + 1 == length || pattern.charAt(i + 1) == 'I') {
                    if (min == 0) {
                        stringBuilder.append(++min).append(++min);
                    } else {
                        stringBuilder.append(++min);
                    }
                } else {
                    int d = 0;
                    while (i + 1 < length && pattern.charAt(i + 1) == 'D') {
                        ++i;
                        ++d;
                    }
                    boolean reset = true;
                    int offset = min;
                    min = min + d + 1;
                    int iter = min - offset;
                    int init = 0;
                    while (init < iter) {
                        if (reset) {
                            stringBuilder.append(min).append(min-1);
                            init = init + 2;
                            reset = !reset;
                        } else {
                            stringBuilder.append(min - init);
                            ++init;
                        }
                    }
                }
            } else {
                int d = 1;
                while (i + 1 < length && pattern.charAt(i + 1) == 'D') {
                    ++i;
                    ++d;
                }
                d = d + 1;
                min = d;
                while (d > 0) {
                    stringBuilder.append(d);
                    --d;
                }
            }
            ++i;
        }
        return stringBuilder.toString();
    }

}
