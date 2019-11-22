package DataStructures.DS;


/**
 * @author Harish T
 */
public class StackDynArr<E> {
    E[] array;
    private int size = 0;
    private int maxlength;

    public StackDynArr(int length) {
        array = (E[]) new Object[length];
        this.maxlength = length;
    }

    public void expand() {
        E[] newArr = (E[]) new Object[size * 2];
        System.arraycopy(array, 0, newArr, 0, size);
        array = newArr;
    }

    public void push(E data) {
        if (size == maxlength) {
            expand();
        }
        array[size++] = data;
    }

    public E pop() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("StackDS if empty");
        }
        size = size - 1;
        return array[size];
    }

    public int getSize() {
        return size;
    }

    public E top() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("StackDS if empty");
        }
        return array[size - 1];
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isFullStack() {
        return (size == maxlength);
    }

    public boolean isBalanced(String str) {
        char[] chars = new char[str.length()];
        str.getChars(0, str.length() - 1, chars, 0);
        StackDynArr<Character> stack = new StackDynArr<>(chars.length);
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '[' || chars[i] == '{' || chars[i] == '(') {
                stack.push(chars[i]);
            } else if (chars[i] == ']') {
                if (stack.pop() == '[')
                    continue;
                return false;
            } else if (chars[i] == '}') {
                if (stack.pop() == '{')
                    continue;
                return false;
            } else if (chars[i] == ')') {
                if (stack.pop() == '(')
                    continue;
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static String intToPostFix(String expression) {
        char[] exprChars = new char[expression.length()];
        expression.getChars(0, expression.length(), exprChars, 0);
        StringBuilder postExpression = new StringBuilder();
        StackDynArr<Character> stack = new StackDynArr<>(expression.length());
        for (int i = 0; i < exprChars.length; i++) {
            if (exprChars[i] == '(' || exprChars[i] == '+' || exprChars[i] == '*' || exprChars[i] == '-' || exprChars[i] == '%' || exprChars[i] == '/' || exprChars[i] == '^') {
                stack.push(exprChars[i]);
            } else if (exprChars[i] == ')') {
                while (!stack.isEmpty()) {
                    char temp = stack.pop();
                    if (temp == '(') {
                        if (!stack.isEmpty()) {
                            postExpression.append(stack.pop());
                        }
                        break;
                    }
                    postExpression.append(temp);
                }
            } else {
                postExpression.append(exprChars[i]);
            }
        }
        return postExpression.toString();
    }

    public static int Prec(char ch) {
        switch (ch) {
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

    public static String intToPostfix(String expression) {
        char[] exprChars = new char[expression.length()];
        expression.getChars(0, expression.length(), exprChars, 0);
        StringBuilder postExpression = new StringBuilder();
        StackDynArr<Character> stack = new StackDynArr<>(expression.length());
        for (int i = 0; i < exprChars.length; i++) {
            if (exprChars[i] == '(') {
                stack.push(exprChars[i]);
            } else if (exprChars[i] == ')') {
                while (stack.top() != '(') {
                    postExpression.append(stack.pop());
                }
                stack.pop();
            } else if (Character.isLetterOrDigit(exprChars[i])) {
                postExpression.append(exprChars[i]);
            } else {
                if (!stack.isEmpty() && Prec(exprChars[i]) < Prec(stack.top())) {
                    while (!stack.isEmpty() && stack.top() != '(') {
                        postExpression.append(stack.pop());
                    }
                }
                stack.push(exprChars[i]);
            }
        }
        while (!stack.isEmpty()) {
            postExpression.append(stack.pop());
        }
        return postExpression.toString();
    }

    public static String intToPrefix(String expression) {
        expression = expression.replaceAll("\\(", "|").replaceAll("\\)", "(").replaceAll("\\|", ")");
        StringBuilder str = new StringBuilder(expression);
        return new StringBuilder(intToPostFix(str.reverse().toString())).reverse().toString();
    }

    public static int maxDaysAtTop(int[] stockDays) {

        for (int temp : stockDays) {

        }
        return 0;
    }

    public static void nextGreaterElem(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        StackDynArr<Integer> stack = new StackDynArr<>(arr.length);
        stack.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (stack.top() < arr[i]) {
                while (!stack.isEmpty() && stack.top() < arr[i]) {
                    int nextL = stack.pop();
                    System.out.println("next L of " + nextL + "is " + arr[i]);
                }
            }
            stack.push(arr[i]);
        }
        while (!stack.isEmpty()) {
            System.out.println("next L of " + stack.pop() + "is " + -1);
        }
    }

    public static String prefixToInfix(String expression) {
        StackDynArr<String> stack = new StackDynArr<>(expression.length());
        StringBuilder str = new StringBuilder(expression);
        str.reverse();
        StringBuilder infix = new StringBuilder();
        String[] strExp = str.toString().split("");
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isLetterOrDigit(strExp[i].charAt(0))) {
                stack.push(strExp[i]);
            } else if (strExp[i].equals("+") || strExp[i].equals("-") || strExp[i].equals("/") || strExp[i].equals("*") || strExp[i].equals("^") || strExp[i].equals("%")) {
                infix.append("(").append(stack.pop()).append(strExp[i]).append(stack.pop()).append(")");
                stack.push(infix.toString());
                infix.setLength(0);
            }
        }
        return stack.pop();
    }

    public static String postfixToIntfix(String expression) {
        StackDynArr<String> stack = new StackDynArr<>(expression.length());
        StringBuilder infix = new StringBuilder();
        String[] strExp = expression.split("");
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isLetterOrDigit(strExp[i].charAt(0))) {
                stack.push(strExp[i]);
            } else if (strExp[i].equals("+") || strExp[i].equals("-") || strExp[i].equals("/") || strExp[i].equals("*") || strExp[i].equals("^") || strExp[i].equals("%")) {
                String pop1 = stack.pop();
                String pop2 = stack.pop();
                infix.append("(").append(pop2).append(strExp[i]).append(pop1).append(")");
                stack.push(infix.toString());
                infix.setLength(0);
            }
        }
        return stack.pop();
    }

    public static String postToPre(String expression) {
        StackDynArr<String> stack = new StackDynArr<>(expression.length());
        StringBuilder infix = new StringBuilder();
        String[] strExp = expression.split("");
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isLetterOrDigit(strExp[i].charAt(0))) {
                stack.push(strExp[i]);
            } else if (strExp[i].equals("+") || strExp[i].equals("-") || strExp[i].equals("/") || strExp[i].equals("*") || strExp[i].equals("^") || strExp[i].equals("%")) {
                String pop1 = stack.pop();
                String pop2 = stack.pop();
                infix.append(strExp[i]).append(pop2).append(pop1);
                stack.push(infix.toString());
                infix.setLength(0);
            }
        }
        return stack.pop();
    }

    public static String preToPost(String expression) {
        //-A/BC-/AKL
        StackDynArr<String> stack = new StackDynArr<>(expression.length());
        StringBuilder str = new StringBuilder(expression);
        str.reverse();
        StringBuilder infix = new StringBuilder();
        String[] strExp = str.toString().split("");
        for (int i = 0; i < expression.length(); i++) {
            if (Character.isLetterOrDigit(strExp[i].charAt(0))) {
                stack.push(strExp[i]);
            } else if (strExp[i].equals("+") || strExp[i].equals("-") || strExp[i].equals("/") || strExp[i].equals("*") || strExp[i].equals("^") || strExp[i].equals("%")) {
                String pop1 = stack.pop();
                String pop2 = stack.pop();
                infix.append(pop1).append(pop2).append(strExp[i]);
                stack.push(infix.toString());
                infix.setLength(0);
            }
        }
        while (!stack.isEmpty()) {
            infix.append(stack.pop());
        }
        return infix.toString();
    }

    public static int findCeleb(int[][] arr) {
        int temp = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[temp][i] == 1) {
                temp = i;
            }

        }
        return temp;
    }

    public static void stockSpan(int[] arr) {
        StackDynArr<Integer> stack = new StackDynArr<>(arr.length);
        int[] stockSpan = new int[arr.length];
        stack.push(0);
        stockSpan[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[stack.top()] >= arr[i]) {
                stockSpan[i] = 1;
                stack.push(i);
            } else {
                while (!stack.isEmpty() && arr[stack.top()] < arr[i]) {
                    stack.pop();
                }
                stockSpan[i] = !stack.isEmpty() ? i - stack.top() : i;
                stack.push(i);
            }
        }
        for (int temp : stockSpan) {
            System.out.println(temp);
        }
    }

    public static boolean isPalindrome(String expression) {
        StackDynArr<Character> stack = new StackDynArr<>(expression.length());
        char[] chars = expression.toCharArray();
        for (int i = 0; i < (expression.length() / 2); i++) {
            stack.push(chars[i]);
        }
        int mid = expression.length() / 2;
        if (expression.length() % 2 == 1) {
            ++mid;
        }
        for (int i = mid; i < expression.length(); i++) {
            if (chars[i] != stack.pop()) {
                return false;
            }
        }
        return true;
    }

    public static void reverseStack(StackDynArr<Integer> stack) {
        stack.push(reverse(stack));
    }

    public static int reverse(StackDynArr<Integer> stack) {
        if (!stack.isEmpty()) {
            int data = stack.pop();
            int elem = reverse(stack);
            if (elem != -1) {
                stack.push(elem);
            }
            return data;
        }
        return -1;
    }

    public static void sort(StackDynArr<Integer> stack) {
        if (!stack.isEmpty()) {
            int temp = stack.pop();
            sort(stack);
            sortInsert(stack, temp);

        }
    }

    public static void sortInsert(StackDynArr<Integer> stack, int data) {
       if(!stack.isEmpty()){

       }
    }

    public static void removeDup(int[] arr) {
        StackDynArr<Integer> stack = new StackDynArr<>(arr.length);
        stack.push(arr[0]);
        int temp = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (!stack.isEmpty() && stack.top() == arr[i]) {
                temp = stack.pop();
            } else if (stack.isEmpty() || temp != arr[i]) {
                stack.push(arr[i]);
            }
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    static int prodSum(int[] arr) {
        StackDynArr<Integer> stack = new StackDynArr<>(arr.length);
        stack.push(0);
        int[] array = new int[arr.length];
        array[0] = -1;
        for (int i = 1; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.top()] <= arr[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                array[i] = -1;
                stack.push(i);
                break;
            }
            array[i] = stack.top();
            stack.push(i);
        }

        StackDynArr<Integer> stackB = new StackDynArr<>(arr.length);
        stackB.push(arr.length - 1);
        int[] arrayB = new int[arr.length];
        arrayB[arrayB.length - 1] = -1;
        for (int i = arrayB.length - 2; i >= 0; i--) {
            while (!stackB.isEmpty() && arr[stackB.top()] <= arr[i]) {
                stackB.pop();
            }
            if (stackB.isEmpty()) {
                arrayB[i] = -1;
                stackB.push(i);
                break;
            }
            arrayB[i] = stackB.top();
            stackB.push(i);
        }
        int max = 0;
        for (int j = 0; j < arr.length; j++) {
            int temp = (array[j] + 1) * (arrayB[arrayB.length - 1 - j] + 1);
            if (max < temp) max = temp;
        }
        return max;
    }

}