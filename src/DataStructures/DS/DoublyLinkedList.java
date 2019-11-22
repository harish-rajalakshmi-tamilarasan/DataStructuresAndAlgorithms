package DataStructures.DS;

/**
 * @author Harish T
 */
public class DoublyLinkedList<E> {
    private Node<E> root;
    private Node<E> tail;
    private int size = 0;

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        private Node(E data, Node<E> nextNode, Node<E> prevNode) {
            this.data = data;
            this.prev = prevNode;
            this.next = nextNode;
        }

        private void setNext(Node<E> node) {
            this.next = node;
        }

        private void setPrev(Node<E> node) {
            this.prev = node;
        }


        private E getElement() {
            return this.data;
        }

        private Node<E> getNext() {
            return this.next;
        }

        private Node<E> getPrev() {
            return this.prev;
        }

    }

    public int getSize() {
        return this.size;
    }

    public void addRoot(E data) {
        if (size == 0) {
            root = new Node<E>(data, null, null);
            tail = root;
        } else {
            Node<E> node = root;
            root = new Node<>(data, root, null);
            node.setPrev(root);
        }
        size++;
    }

    public E getRoot() {
        return root.getElement();
    }

    public void addTail(E data) {
        if (size == 0) {
            root = new Node<>(data, null, null);
            tail = root;
        } else {
            Node<E> node = new Node<>(data, null, tail);
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    public E getTail() {
        return tail.getElement();
    }

    public E removeRoot() {
        if (size == 0) {
            throw new ArrayIndexOutOfBoundsException("List is empty");
        }
        E element = root.getElement();
        if (size == 1) {
            tail = null;
            root = null;
            size=0;
            return element;
        }
        root.next.prev = null;
        root = root.next;
        --size;
        return element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    Node<E> getRootNode() {
        return root;
    }

}
