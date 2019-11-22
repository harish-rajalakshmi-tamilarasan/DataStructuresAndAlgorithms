package DataStructures.DS;

/**
 * @author Harish T
 */
public class StackGetMinimum {
    private int size = 0;
    private int maxlength = 0;
    int[] array;
    int[] minArray;
    private int minArrSize = 0;

    StackGetMinimum(int length) {
        array = new int[length];
        maxlength = length;
        minArray = new int[length];
    }

    public void push(int data) throws Exception {
        if (size == maxlength) {
            throw new Exception("StackDS is Full");
        }
        if (minArrSize == 0 || minTop() >= data) {
            minArray[minArrSize++] = data;
        }
        array[size++] = data;
    }

    public int minTop() {
        return minArray[minArrSize - 1];
    }

    public int pop() throws Exception {
        if (size == 0) {
            throw new Exception("StackDS is Empty");
        }
        int popData = array[--size];
        if (popData == minTop()) {
            getMin();
        }
        return popData;
    }

    public int getMin() throws Exception {
        if (minArrSize == 0) {
            throw new Exception("StackDS is Empty");
        }
        return minArray[--minArrSize];
    }
}
