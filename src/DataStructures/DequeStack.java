package DataStructures;

/**
 * @author Harish T
 */
public class DequeStack<E> {
    private int size = 0;
    private int tos = -1;
    private E[] data;

    public DequeStack(int size) {
        this.size = size;
        data = (E[]) new Object[size];
    }

    public void addLast(E e) {
        if (tos == size - 1) {
            System.out.println("Stack full");
            throw new ArrayIndexOutOfBoundsException("Stackfull");
        } else {
            data[++tos] = e;
        }
    }

    public void addFirst(E e) {
        if (tos == -1) {
            data[0] = e;
        } else {
            data[++tos] = e;
        }
    }
}
