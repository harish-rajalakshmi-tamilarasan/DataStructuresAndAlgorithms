package DataStructures.DS.Queue;

/**
 * @author Harish T
 */
public class QueueDyn {
    private int front = -1;
    private int end = -1;
    private int size;
    private int[] array;
    private int capacity = 16;

    QueueDyn(int size) {
        this.capacity = size;
        this.array = new int[size];
    }

    QueueDyn() {
        this.array = new int[16];
    }

    public void enqueue(int data) throws Exception {
        if (size == capacity) {
            expand();
        }
        if (size == 0) {
            array[++front] = data;
            ++end;
            ++size;
            return;
        }
        end = (end + 1) % capacity;
        array[end] = data;
        ++size;
    }

    public int dequeue() throws Exception {
        if (size == 0) {
            throw new Exception("StackDS is Empty");
        }
        --size;
        int data = array[front];
        front = (front + 1) % capacity;
        return data;
    }

    public void expand() {
        int[] arr = new int[capacity * 2];
        if (end < front) {
            System.arraycopy(array, 0, arr, 0, end + 1);
            System.arraycopy(array, front, arr, 2 * capacity - front - 1, capacity - front);
            front = 2 * capacity - front - 1;
        } else {
            System.arraycopy(array, 0, arr, 0, capacity);
        }
        array = arr;
        capacity = capacity * 2;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int front() throws Exception {
        if (size == 0) {
            throw new Exception("StackDS is Empty");
        }
        return array[front];
    }
}
