package DataStructures;

/**
 * @author Harish T
 */
public class ArrayStack<E> {
    private int size = 0;
    private int tos = -1;
    private E[] data;

    public ArrayStack(int size) {
        this.size = size;
        data = (E[]) new Object[size];
    }

    public void push(E e) {
        if (tos == size - 1) {
            System.out.println("StackDS is full");
            throw new ArrayIndexOutOfBoundsException("StackDS full");
        }
        data[++tos] = e;
    }

    public E pop(){
        if(tos==-1){
            throw new ArrayIndexOutOfBoundsException("StackDS empty");
        }
        return data[tos--];
    }

    public E peek(){
        if(tos==-1){
            throw new ArrayIndexOutOfBoundsException("StackDS empty");
        }
        return data[tos-1];
    }
}
