package DataStructures.DS.Queue;

import DataStructures.DS.LinkedList.DoubleLinkedList;

import java.util.HashMap;

/**
 * @author Harish T
 */
public class LRUCache {
    private int pageSize = 0;
    private int currSize = 0;

    public LRUCache(int pageSize) {
        this.pageSize = pageSize;
    }

    private DoubleLinkedList list = new DoubleLinkedList();
    private HashMap<Integer, DoubleLinkedList.Node> map = new HashMap<>();

    public void insert(int number) {
        if (map.containsKey(number)) {
            DoubleLinkedList.Node node = map.get(number);
            if (node.prev != null) {
                if (node.next == null) {
                    node.prev.next = null;
                    node.next = list.root;
                    list.root.prev = node;
                    node.prev = null;
                    list.root = node;
                } else {
                    node.next.prev = node.prev;
                    node.prev.next = node.next;
                    node.next = list.root;
                    node.prev = null;
                    list.root.prev = node;
                    list.root = node;
                }
            }
            return;
        }

        if (currSize < pageSize) {
            ++currSize;
            map.put(number, list.addRoot(number));
        } else {
            map.remove(list.tail.data);
            list.tail = list.tail.prev;
            list.tail.next = null;
            map.put(number, list.addRoot(number));
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(4);
        cache.insert(1);
        cache.insert(2);
        cache.insert(3);
        cache.insert(4);
        cache.insert(5);
        cache.insert(3);
        System.out.println();
    }

}
