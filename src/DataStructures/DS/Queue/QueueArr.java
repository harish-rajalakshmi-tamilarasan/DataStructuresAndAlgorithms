package DataStructures.DS.Queue;

/**
 * @author Harish T
 */
public class QueueArr {
    private int front = -1;
    private int end = -1;
    private int size;
    private int[] array;
    private int capacity = 16;

    QueueArr(int size) {
        this.capacity = size;
        this.array = new int[size];
    }

    QueueArr() {
        this.array = new int[16];
    }

    public void enqueue(int data) throws Exception {
        if (size == capacity) {
            throw new Exception("Queue is Full");
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
