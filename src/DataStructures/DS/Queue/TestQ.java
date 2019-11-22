package DataStructures.DS.Queue;

/**
 * @author Harish T
 */
public class TestQ {
    public static void main(String[] args)throws Exception {
        QueueDyn q=new QueueDyn(3);
        q.enqueue(2);
        q.enqueue(4);
        q.enqueue(5);
        q.dequeue();
        q.enqueue(7);
        q.enqueue(6);

    }
}
