package DataStructures.DS;

/**
 * @author Harish T
 */
public class MultipleStack {
    private int length;
    private int span;
    private int noOfStacks;
    private int[] size;
    private int[] array;

    MultipleStack(int length, int n) {
        this.span = length;
        this.length = n * length;
        this.noOfStacks = n;
        size = new int[n];
        array = new int[this.length];
    }

    void push(int data, int stack) {
        if (size[stack - 1] == span) {
            expand();
        }
        array[(stack - 1) * span + size[stack - 1]] = data;
        ++size[stack - 1];
    }

    int pop(int stack) throws Exception {
        if (size[stack - 1] == 0) {
            throw new Exception("StackDS is Empty");
        }
        int data = array[(stack - 1) * span + (size[stack - 1] - 1)];
        --size[stack - 1];
        return data;
    }

    void expand() {
        int[] newArr = new int[array.length * 2];
        for (int i = 0; i < noOfStacks; i++) {
            System.arraycopy(array, i * span, newArr, 2 * i * span, span);
        }
        array = newArr;
        span = span * 2;
    }

}
