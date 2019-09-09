package DataStructures;

/**
 * @author Harish T
 */
public class DoublyLinkedList<E> {
    private int size = 0;
    private Node<E> head;
    private Node<E> tail;

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        private Node(E element) {
            this.element = element;
            next = null;
            prev = null;
        }

        private Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

    }

    public void addFirst(E key) {
        head = new Node<E>(key, head, tail);
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    public void addLast(E key) {
        if (size == 0) {
            addFirst(key);
        } else {
            Node<E> newTail = new Node<E>(key);
            newTail.prev = tail;
            tail.next = newTail;
            tail = newTail;
            size++;
        }
    }
}
