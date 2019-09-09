package DataStructures;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Harish T
 */
public class Map<K, V> {
    public int size;
    private ArrayList<Entry<K, V>> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }


    protected static class Entry<K, V> {
        private K k;
        private V v;

        public Entry(K key, V value) {
            k = key;
            v = value;
        }

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }

        public void setKey(K key) {
            k = key;
        }

        public void setValue(V value) {
            v = value;
        }
    }

    public int findIndex(K k) {
        for (int i = 0; i < size(); i++) {
            if (list.get(i).getKey().equals(k)) {
                return i;
            }
        }
        return -1;
    }

    public V get(K key) {
        int index = findIndex(key);
        if (index == -1) {
            throw new IllegalStateException("key not found");
        }
        return list.get(index).getValue();
    }

    public V put(K key, V value) {
        int index = findIndex(key);
        if (index == -1) {
            list.add(new Entry<>(key, value));
            return null;
        }
        V old = list.get(index).getValue();
        list.get(index).setValue(value);
        return old;
    }

    public V remove(K key) {
        int index = findIndex(key);
        int lastIndex = size() - 1;
        if (index == -1) {
            throw new IllegalStateException("Not found key");
        }
        V old = list.get(index).getValue();
        if (index != lastIndex) {
            list.set(index, list.get(lastIndex));
        }
        list.remove(lastIndex);
        return old;
    }

    private class EntryItr implements Iterator<Entry<K, V>> {
        private int j = 0;

        public boolean hasNext() {
            return (j < list.size());
        }

        public Entry<K, V> next() {
            if (!hasNext()) throw new IllegalStateException("No such Element");
            return list.get(j++);
        }

        public void remove() {
            throw new IllegalArgumentException("Not implemented");
        }
    }

    private class EntryIterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new EntryItr();
        }
    }

    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }

}
