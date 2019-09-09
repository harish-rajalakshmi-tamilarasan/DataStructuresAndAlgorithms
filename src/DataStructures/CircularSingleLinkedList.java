package DataStructures;


/**
 * @author Harish T
 */
public class CircularSingleLinkedList<E> {
    private int size = 0;
    private Node<E> tail;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        private Node(E element) {
            this.element = element;
            next = this;
        }

        private Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    public void addLast(E key) {
        if (size == 0) {
            tail = new Node<E>(key);
        } else {
            Node<E> newNode = new Node<E>(key, tail.next);
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    public void removeFirst() {
        tail.next = tail.next.next;
        size--;
    }

    public E getElement(Node<E> node) {
        return node.element;
    }

    public Node<E> getTail() {
        return tail;
    }
}


