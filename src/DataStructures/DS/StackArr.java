package DataStructures.DS;

/**
 * @author Harish T
 */
public class StackArr {
    int[] array;
    private int size = 0;
    private int maxlength;

    public StackArr(int length) {
        array = new int[length];
        this.maxlength = length;
    }

    public void push(int data) {
        if (size == maxlength) {
            throw new ArrayIndexOutOfBoundsException("StackDS Is Full");
        }
        array[size++] = data;
    }

    public int pop() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("StackDS if empty");
        }
        return array[--size];
    }

    public int getSize(){
        return size;
    }
    public int top(){
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("StackDS if empty");
        }
        return array[size-1];
    }
    public boolean isEmpty(){
        return (size==0);
    }
    public boolean isFullStack(){
        return (size==maxlength);
    }
}
