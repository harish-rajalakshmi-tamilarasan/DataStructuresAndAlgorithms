package DataStructures.DS;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

/**
 * @author Harish T
 */
public class SingleLinkedList<E> implements Iterable<E> {

    private Node<E> root;
    private Node<E> tail;
    public int size = 0;

    private static class Node<E> {
        private E data;
        private Node<E> next = null;

        private Node(E data, Node<E> nextNode) {
            this.data = data;
            this.next = nextNode;
        }

        private void setNext(Node<E> node) {
            this.next = node;
        }

        private E getElement() {
            return this.data;
        }

        private Node<E> getNext() {
            return this.next;
        }
    }


    public int getSize() {
        return this.size;
    }

    public void addRoot(E data) {
        if (size == 0) {
            root = new Node<>(data, null);
            tail = root;
        } else {
            root = new Node<>(data, root);
        }
        size++;
    }

    public E getRoot() {
        return root.getElement();
    }

    public void addTail(E data) {
        if (size == 0) {
            root = new Node<>(data, null);
            tail = root;
        } else {
            Node<E> node = new Node<>(data, null);
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
        root = root.next;
        --size;
        if (size == 0) {
            tail = null;
        }
        return element;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int getPosition(E element) {
        int position = 0;
        Node<E> node = root;
        while (node != null) {
            if (node.getElement().equals(element)) {
                return position;
            }
            ++position;
            node = node.getNext();
        }
        return -1;
    }

    public boolean remove(E element) {
        Node<E> node = root;
        Node<E> prevNode = null;
        while (node != null) {
            if (node.getElement().equals(element)) {
                Node<E> nextNode = node.getNext();
                if (prevNode != null) {
                    prevNode.setNext(nextNode);
                    if (nextNode == null) tail = prevNode;
                } else {
                    root = nextNode;
                }
                --size;
                return true;
            }
            prevNode = node;
            node = node.getNext();
        }
        return false;
    }

    public E removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index is invalid");
        }
        Node<E> currentNode = root;
        Node<E> prevNode = null;
        for (int i = 1; i <= index; i++) {
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
        E element = currentNode.getElement();
        if (prevNode != null) {
            prevNode.setNext(currentNode.getNext());
            if (currentNode.getNext() == null) {
                tail = prevNode;
            }
        } else {
            root = currentNode.getNext();
        }
        --size;
        return element;
    }

    public int insert(E element, int index) {
        if (size == 0 || index < 0 || index >= size) {
            addTail(element);
            return size - 1;
        }
        if (index == 0) {
            addRoot(element);
            return 0;
        }
        Node<E> currentNode = root;
        for (int i = 1; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        Node<E> nodeToAdd = new Node<>(element, currentNode.getNext());
        currentNode.setNext(nodeToAdd);
        ++size;
        return index;
    }

    public E getMiddleElement() {
        Node<E> slowPtr = root, fastPtr = root.getNext();
        while (fastPtr != null && fastPtr.getNext() != null) {
            fastPtr = fastPtr.getNext().getNext();
            slowPtr = slowPtr.getNext();
        }
        return slowPtr.getElement();
    }

    Node<E> getRootNode() {
        return root;
    }

    public Iterator<E> iterator() {
        return new LinkedListIterator(this);
    }

    private class LinkedListIterator implements Iterator<E> {
        Node<E> current;
        int position = 0;

        public LinkedListIterator(SingleLinkedList<E> obj) {
            this.current = obj.getRootNode();
        }

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public E next() {
            if (hasNext()) {
                E elem = current.getElement();
                ++position;
                current = current.getNext();
                return elem;
            }
            return null;
        }
    }

    //utility functions

    public boolean hasLoop() {
        //   tail.setNext(root);
        HashSet<E> hashSet = new HashSet<>();
        hashSet.add(root.getElement());
        Node<E> currentNode = root.getNext();
        while (currentNode != null) {
            E element = currentNode.getElement();
            if (hashSet.contains(element)) {
                return true;
            }
            hashSet.add(element);
            currentNode = currentNode.getNext();
        }
        return false;
    }

    public int loopLength() {
        //tail.setNext(root.getNext());
        HashMap<E, Integer> map = new HashMap<>();
        map.put(root.getElement(), 0);
        Node<E> currentNode = root.getNext();
        int count = 1;
        while (currentNode != null) {
            E element = currentNode.getElement();
            if (map.containsKey(element)) {
                return count - map.get(element);
            }
            map.put(element, count);
            ++count;
            currentNode = currentNode.getNext();
        }
        return -1;
    }

    public int removeDuplicates() {
        int initialSize = size;
        HashSet<E> set = new HashSet<>();
        if (size > 0) {
            Node<E> current = root;
            set.add(root.getElement());
            while (current.getNext() != null) {
                E elem = current.getNext().getElement();
                if (set.contains(elem)) {
                    if (current.getNext().getNext() != null) {
                        current.setNext(current.getNext().getNext());
                    } else {
                        current.setNext(null);
                        tail = current;
                    }
                    --size;
                }
                set.add(elem);
                current = current.getNext();
            }
            return initialSize - size;
        }
        return initialSize;
    }

    public E loopStarter() {
        tail.setNext(root.getNext());
        Node<E> slowPtr = root;
        Node<E> fastPtr = root;
        while (fastPtr != null && fastPtr.getNext() != null) {
            slowPtr = slowPtr.getNext();
            fastPtr = fastPtr.getNext().getNext();
            if (slowPtr == fastPtr) {
                slowPtr = root;
                while (slowPtr != fastPtr) {
                    slowPtr = slowPtr.getNext();
                    fastPtr = fastPtr.getNext();
                }
                return slowPtr.getElement();
            }

        }
        return null;
    }

    public int loopLength(int dummy) {
        tail.setNext(root.getNext().getNext());
        Node<E> slowPtr = root;
        Node<E> fastPtr = root;
        while (fastPtr != null && fastPtr.getNext() != null) {
            slowPtr = slowPtr.getNext();
            fastPtr = fastPtr.getNext().getNext();
            if (slowPtr == fastPtr) {
                int length = 1;
                slowPtr = slowPtr.getNext();
                while (slowPtr != fastPtr) {
                    slowPtr = slowPtr.getNext();
                    ++length;
                }
                return length;
            }

        }
        return -1;
    }

    public boolean isPalindrome() {
        int middle = size / 2;
        Stack<E> stack = new Stack<>();
        Node<E> current = root;
        for (int i = 0; i < middle; i++) {
            stack.add(current.getElement());
            current = current.getNext();
        }
        if (size % 2 == 1) {
            current = current.getNext();
        }
        while (!stack.empty()) {
            if (current.getElement().equals(stack.pop())) {
                current = current.getNext();
            } else {
                return false;
            }
        }
        return true;
    }

    public E nthNode(int n) {
        Node<E> node = root;
        int index = size - n - 1;
        if (index < 0) return null;
        for (int i = 1; i <= index; i++) {
            node = node.getNext();
        }
        return node.getElement();
    }

    public E nthNodeFromBack(int n, boolean isTrue) {
        Node<E> slowPtr = root;
        Node<E> fastPtr = root;
        for (int i = 0; fastPtr.getNext() != null; i++) {
            fastPtr = fastPtr.getNext();
            if (i >= n) {
                slowPtr = slowPtr.getNext();
            }
        }
        return slowPtr.getElement();
    }

    public static int counter = 0;
    public E temp = null;

    public E nthNodeRecursiveFromEnd(int n, Node<E> node) {
        if (node != null) {
            nthNodeRecursiveFromEnd(n, node.next);
            ++counter;
            if (counter == n) {
                temp = node.getElement();
            }
        }
        return temp;
    }


    public void recursiveReverse() {
        reverseRecursively(root, root.getNext());
    }

    public void reverseRecursively(Node<E> current, Node<E> temp) {
        if (current != null) {
            if (temp != null) {
                current.setNext(temp.getNext());
                temp.setNext(root);
                root = temp;
                reverseRecursively(current, current.getNext());
            }
        }
    }

    public E getIntersection(SingleLinkedList<E> listA) {
        int listAl = listA.getSize();
        int d = getSize();
        int diff = Math.abs(listAl - d);
        Node<E> startPtrA;
        Node<E> startPtrB;
        if (d < listAl) {
            startPtrA = listA.root;
            startPtrB = this.root;
        } else {
            startPtrA = this.root;
            startPtrB = listA.root;
        }
        for (int i = 0; i < diff; i++) {
            startPtrA = startPtrA.getNext();
        }
        while (startPtrA != null && startPtrB != null) {
            if (startPtrA == startPtrB) {
                return startPtrA.getElement();
            }
            startPtrA = startPtrB.getNext();
            startPtrB = startPtrB.getNext();
        }
        return null;
    }

    public boolean isOdd() {
        Node<E> startPtr = root;
        Node<E> fastPtr = root;
        while (fastPtr != null) {
            startPtr = startPtr.getNext();
            if (fastPtr.getNext() == null) break;
            fastPtr = fastPtr.getNext().getNext();
        }
        return fastPtr != null;
    }

    public void addNodeToList(Node<E> node) {
        if (this.getSize() == 0) {
            root = node;
            ++size;
            tail = root;
        } else {
            tail.setNext(node);
            ++size;
            tail = node;
        }
    }

    public void append(SingleLinkedList<E> list) {
        if (this.getSize() == 0) {

        }
    }

    /**
     * public SingleLinkedList<Integer> mergeTwoList(SingleLinkedList<Integer> listA, SingleLinkedList<Integer> listB) {
     * SingleLinkedList<Integer> mergedList = new SingleLinkedList<>();
     * Node<Integer> currentA = listA.root;
     * Node<Integer> currentB = listB.root;
     * while (currentA != null && currentB != null) {
     * if (currentA.getElement() > currentB.getElement()) {
     * mergedList.addNodeToList(currentB);
     * currentB = currentB.getNext();
     * } else {
     * mergedList.addNodeToList(currentA);
     * currentB = currentA.getNext();
     * }
     * }
     * if (currentA != null) {
     * mergedList.addTail();
     * }
     * }
     **/

    public synchronized void swap() throws ArrayIndexOutOfBoundsException {
        if (root != null) {
            swapPositions(3, null, root);
        } else {
            throw new ArrayIndexOutOfBoundsException("Empty Array");
        }
    }

    public static boolean tempV = true;

    public void swapPositions(Node<E> prev, Node<E> first) {
        if (first.getNext() != null) {
            Node<E> second = first.getNext();
            first.setNext(second.getNext());
            second.setNext(first);
            if (prev != null) {
                prev.setNext(second);
            }
            if (tempV) {
                root = second;
            }
            tempV = false;
            if (first.getNext() == null) {
                tail = first;
                return;
            }
            swapPositions(first, first.getNext());
        }
    }

    public void swapPositions() {
        if (root == null) return;
        Node<E> headNode = root;
        Node<E> prevNode = null;
        Node<E> edgeNode = root.getNext();
        while (edgeNode != null) {
            headNode.setNext(edgeNode.getNext());
            edgeNode.setNext(headNode);
            if (prevNode != null) {
                prevNode.setNext(edgeNode);
            } else {
                root = edgeNode;
            }
            prevNode = headNode;
            headNode = headNode.getNext();
            if (headNode == null) {
                tail = prevNode;
                return;
            }
            edgeNode = headNode.getNext();
        }
    }

    public void swapPositions(int n, Node<E> prev, Node<E> head) {
        Node iterTemp = head;
        for (int i = 0; i < n - 1; i++) {
            iterTemp = iterTemp.getNext();
            if (iterTemp == null)
                return;
        }
        Node<E> current = head;
        Node<E> last = head.getNext();
        Node<E> temp = null;
        for (int i = 0; i < n - 1; i++) {
            current.setNext(last.getNext());
            last.setNext(head);
            head = last;
            temp = current;
            last = current.getNext();
        }
        if (prev == null) {
            root = head;
        } else {
            prev.setNext(head);
        }
        if (last == null) {
            tail = temp;
            return;
        }
        swapPositions(n, temp, last);
    }

    public void reverseList() {
        Node<E> current = root;
        if (current != null) {
            Node<E> last = current.getNext();

            while (last != null) {
                current.setNext(last.getNext());
                last.setNext(root);
                root = last;
                last = current.getNext();
            }
        }
    }

    public void sortList(SingleLinkedList<Integer> list) {
        Node[] nodes = sort(list.getRootNode(), list.getRootNode(), list.getRootNode().getNext());
        list.root = nodes[0];
        list.tail = nodes[1];
    }

    public Node[] sort(Node<Integer> head, Node<Integer> last, Node<Integer> node) {
        if (node != null) {
            Node<Integer> tempC = head;
            Node<Integer> tempPrev = null;
            while (node.getElement() > tempC.getElement() && tempC != node) {
                if (tempPrev == null) tempPrev = head;
                tempC = tempC.getNext();
            }
            if (tempC != node) {
                last.setNext(node.getNext());
                if (tempPrev != null) {
                    tempPrev.setNext(node);
                } else {
                    head = node;
                }
                node.setNext(tempC);
                return sort(head, tempC, tempC.getNext());
            } else {
                return sort(head, node, node.getNext());
            }
        }
        Node[] nodes = {head, node};
        return nodes;
    }


    public void swapInBetween() {
        Node<E> slowPtr = root;
        Node<E> fastPtr = root;
        while (fastPtr != null && fastPtr.getNext() != null) {
            fastPtr = fastPtr.getNext().getNext();
            if (fastPtr != null) {
                slowPtr = slowPtr.getNext();
            }
        }
        Node<E> current = slowPtr.getNext().getNext();
        Node<E> last = slowPtr.getNext();
        while (current != null) {
            last.setNext(current.getNext());
            current.setNext(slowPtr.getNext());
            slowPtr.setNext(current);
            current = last.getNext();
        }
        tail = last;
        System.out.println(tail.getNext());
        fastPtr = root;
        Node<E> tempN = slowPtr.getNext();
        Node<E> emer;
        while (tempN != null && fastPtr != slowPtr) {
            emer = fastPtr.getNext();
            slowPtr.setNext(tempN.getNext());
            fastPtr.setNext(tempN);
            tempN.setNext(emer);
            tempN = slowPtr.getNext();
            fastPtr = emer;

        }
        tail = slowPtr;
    }

    public void kRotate(int k) {
        Node<E> slowPtr = root;
        Node<E> fastPtr = root;
        while (k > 0) {
            fastPtr = fastPtr.getNext() != null ? fastPtr.getNext() : root;
            --k;
        }
        if (slowPtr == fastPtr) {
            return;
        }
        while (fastPtr.getNext() != null) {
            slowPtr = slowPtr.getNext();
            fastPtr = fastPtr.getNext();
        }
        Node<E> head = slowPtr.getNext();
        slowPtr.setNext(null);
        tail.setNext(root);
        root = head;
        tail = slowPtr;
    }

    public SingleLinkedList<Integer> addTwoList(SingleLinkedList<Integer> firstList, SingleLinkedList<Integer> secondList) {
        SingleLinkedList<Integer> addedList = new SingleLinkedList<>();
        Node<Integer> firstPtr = firstList.getRootNode();
        Node<Integer> secondPtr = secondList.getRootNode();
        int sum;
        int carry = 0;
        int elem;
        while (firstPtr != null && secondPtr != null) {
            sum = firstPtr.getElement() + secondPtr.getElement() + carry;
            elem = sum % 10;
            addedList.addRoot(elem);
            carry = sum / 10;
            firstPtr = firstPtr.getNext();
            secondPtr = secondPtr.getNext();
        }
        while (firstPtr != null) {
            sum = firstPtr.getElement() + carry;
            elem = sum % 10;
            addedList.addRoot(elem);
            carry = sum / 10;
            firstPtr = firstPtr.getNext();
        }
        while (secondPtr != null) {
            sum = secondPtr.getElement() + carry;
            elem = sum % 10;
            addedList.addRoot(elem);
            carry = sum / 10;
            secondPtr = secondPtr.getNext();
        }
        return addedList;
    }

    public void printCommon(SingleLinkedList<Integer> firstList, SingleLinkedList<Integer> secondList) {
        Node<Integer> firstPtr = firstList.getRootNode();
        Node<Integer> secondPtr = secondList.getRootNode();
        while (firstPtr != null && secondPtr != null) {
            if (firstPtr.getElement().equals(secondPtr.getElement())) {
                System.out.println(firstPtr.getElement());
                firstPtr = firstPtr.getNext();
                secondPtr = secondPtr.getNext();
            } else if (firstPtr.getElement() > secondPtr.getElement()) {
                secondPtr = secondPtr.getNext();
            } else {
                firstPtr = firstPtr.getNext();
            }
        }
    }

    public void UnSortedRemoveDuplicates() {
        HashSet<E> hashSet = new HashSet<>();
        hashSet.add(root.getElement());
        Node<E> ptr = root;
        Node<E> tempPtr;
        while (ptr.getNext() != null) {
            tempPtr = ptr.getNext();
            if (hashSet.contains(tempPtr.getElement())) {
                ptr.setNext(tempPtr.getNext());
                --size;
                //    tempPtr.setNext(null);
                continue;
            }
            hashSet.add(tempPtr.getElement());
            ptr = ptr.getNext();
        }
        tail = ptr;
    }

    public void removeDupSorted(SingleLinkedList<Integer> listA) {
        Node<Integer> current = listA.getRootNode();
        while (current.getNext() != null) {
            if (current.getElement().equals(current.getNext().getElement())) {
                current.setNext(current.getNext().getNext());
                --listA.size;
                continue;
            }
            current = current.getNext();
        }
        listA.tail = current;
    }

    public void sepEvenOdd(SingleLinkedList<Integer> list) {
        Node<Integer> ptr = list.getRootNode();
        Node<Integer> temp;
        while (ptr.getNext() != null) {
            temp = ptr.getNext();
            if (temp.getElement() % 2 == 0) {
                ptr.setNext(temp.getNext());
                temp.setNext(list.root);
                list.root = temp;
                continue;
            }
            ptr = ptr.getNext();
        }
        list.tail = ptr;
    }

    public void sepOnK(SingleLinkedList<Integer> list, int n) {
        Node<Integer> ptr = list.getRootNode();
        Node<Integer> temp;
        while (ptr.getNext() != null) {
            temp = ptr.getNext();
            if (temp.getElement() > n) {
                ptr.setNext(temp.getNext());
                temp.setNext(list.root);
                list.root = temp;
                continue;
            }
            ptr = ptr.getNext();
        }
        list.tail = ptr;
    }

    public SingleLinkedList<Integer> mergeSort(SingleLinkedList<Integer> a, SingleLinkedList<Integer> b) {
        SingleLinkedList<Integer> c = new SingleLinkedList<>();
        Node<Integer> frst = a.getRootNode();
        Node<Integer> second = b.getRootNode();
        while (frst != null && second != null) {
            if (frst.getElement() > second.getElement()) {
                c.addRoot(second.getElement());
                second = second.getNext();
            } else if (second.getElement() > frst.getElement()) {
                c.addRoot(frst.getElement());
                frst = frst.getNext();
            } else {
                c.addRoot(frst.getElement());
                c.addRoot(second.getElement());
                frst = frst.getNext();
                second = second.getNext();
            }
        }
        while (frst != null) {
            c.addRoot(frst.getElement());
            frst = frst.getNext();
        }
        while (second != null) {
            c.addRoot(second.getElement());
            second = second.getNext();
        }
        return c;
    }

    //1,3,2,4,5,6,7 turn into 1,2,3,4,5,6,7 depending on starting node.
    public void arrangeAsOddEven(SingleLinkedList<Integer> list) {
        int oddN = 0;
        int even = 0;
        Node<Integer> last = list.getRootNode();
        boolean isRootOdd = last.getElement() % 2 == 1;
        if (isRootOdd) oddN = 1;
        else even = 1;
        Node<Integer> prev = last.getNext();
        Node<Integer> node = prev.getNext();
        while (node != null && node.getNext() != null) {
            if (node.getElement() % 2 == 1) {
                prev.setNext(node.getNext());
                node.setNext(last.getNext());
                last.setNext(node);
            }
        }
    }

    public Node<Integer> getMiddle(Node<Integer> start) {
        Node<Integer> slowPtr = start, fastPtr = start.getNext();
        while (fastPtr != null && fastPtr.getNext() != null) {
            fastPtr = fastPtr.getNext().getNext();
            slowPtr = slowPtr.getNext();
        }
        return slowPtr;
    }


    public Node mergeSort(Node<Integer> start) {
        if (start == null || start.getNext() == null) {
            return start;
        }
        Node<Integer> mid = getMiddle(start);
        Node<Integer> midNext = mid.getNext();
        mid.setNext(null);
        return merge(mergeSort(start), mergeSort(midNext));
    }


    public Node merge(Node<Integer> start, Node<Integer> mid) {
        Node<Integer> node = null;
        if (start == null) {
            return mid;
        }
        if (mid == null) {
            return start;
        }
        if (start.getElement() <= mid.getElement()) {
            node = start;
            node.next = merge(start.getNext(), mid);
        } else {
            node = mid;
            node.next = merge(start, mid.getNext());
        }

        return node;

    }

    public void mergeSortInitialize(SingleLinkedList<Integer> list) {
        Node node = mergeSort(list.getRootNode());
        while (node != null) {
            System.out.println(node.getElement());
            node = node.getNext();
        }
    }
}


