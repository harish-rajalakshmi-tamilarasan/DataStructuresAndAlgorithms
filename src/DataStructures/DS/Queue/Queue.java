package DataStructures.DS.Queue;

/**
 * @author Harish T
 */
public interface Queue<E> {
    void enqueue(E data);

    E dequeue() throws Exception;

    int getSize();

    boolean isEmpty();

    E front();
}
