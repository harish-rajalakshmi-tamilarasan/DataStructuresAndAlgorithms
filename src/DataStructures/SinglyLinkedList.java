package DataStructures;

/**
 * @author Harish T
 */
public class SinglyLinkedList<E> {
    private int size = 0;
    private Node<E> head;
    private Node<E> tail;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        private Node(E element) {
            this.element = element;
            next = null;
        }

        private Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        private void setNext(Node<E> node) {
            this.next = node;
        }
    }

    public void addFirst(E key) {
        head = new Node<E>(key, head);
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
            tail.setNext(newTail);
            tail = newTail;
            size++;
        }
    }

    public E removeFirst() {
        if (size == 0) {
            return null;
        }
        E element = head.element;
        head = head.next;
        size--;
        return element;
    }

    public E removeLast() {
        if (size == 0) {
            return null;
        }
        E element = tail.element;
        if (size == 1) {
            head = null;
            tail = null;
            size = 0;
            return element;
        }
        int no = 1;
        Node<E> penaltimate = head;
        while (no < size-2) {
            penaltimate = penaltimate.next;
            no++;
        }
        penaltimate.next = null;
        tail = penaltimate;
        size--;
        return element;
    }

    public void insertAfter(E key,Node<E> node){

    }
}
