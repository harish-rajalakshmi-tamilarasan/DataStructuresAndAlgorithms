package DataStructures.DS;

/**
 * @author Harish T
 */
public class DoubleStack {
    private int firstPos = -1;
    private int lastPos;
    int[] array;


    public DoubleStack(int length) {
        lastPos = length;
        array = new int[length];
    }

    void pushFirst(int data) {
        if (lastPos - firstPos == 1 || lastPos == 0 || firstPos == array.length - 1) {
            expand();
        } else {
            array[++firstPos] = data;
        }
    }

    void pushEnd(int data) {
        if (lastPos - firstPos == 1 || lastPos == 0 || firstPos == array.length - 1) {
            expand();
        } else {
            array[--lastPos] = data;
        }
    }

    void expand() {
        int[] newArr = new int[array.length * 2];
        System.arraycopy(array, 0, newArr, 0, firstPos + 1);
        int offset = array.length - lastPos;
        System.arraycopy(array, lastPos, newArr, newArr.length - offset, offset);
        array = newArr;
        lastPos = array.length - offset;
    }

    int popFirst() throws Exception {
        if (!isFirstEmpty()) {
            return array[firstPos--];
        }
        throw new Exception("StackDS A Empty");
    }

    int popEnd() throws Exception {
        if (!isEndEmpty()) {
            return array[lastPos++];
        }
        throw new Exception("StackDS B Empty");
    }

    boolean isFirstEmpty() {
        return firstPos == -1;
    }

    boolean isEndEmpty() {
        return lastPos == array.length;
    }
}
