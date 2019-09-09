package DataStructures;

import java.util.ArrayList;

/**
 * @author Harish T
 */
public class PriorityQueueArray<K, V> implements Comparable<K> {
    private int size;
    private Comparable<K> comp;
    public ArrayList<Entry<K,V>> list=new ArrayList<>();

    private static class Entry<K, V> {
        private K key;
        private V value;

        private Entry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        private void setKey(K k) {
            key = k;
        }

        private void setValue(V v) {
            value = v;
        }
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int compareTo(K a) {
        return 0;
    }

    public int parent(int j) {
        return (j - 1) / 2;
    }

    public int left(int j) {
        return (2 * j) + 1;
    }

    public int right(int j) {
        return (2 * j) + 2;
    }
    public void upHeap(int j){
    }
}
