package DataStructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Harish T
 */
public class HashedMap<K, V> {
    private int mapsize;
    private int capacity;
    private int prime;
    private long scale, shift;
    private Map<K, V>[] table;

    public HashedMap(int capacity, int prime) {
        this.capacity = capacity;
        this.prime = prime;
        Random rand = new Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
    }

    protected void createTable() {
        table = new Map[capacity];
    }

    public HashedMap() {
        this(17, 109345121);
    }

    public int mapSize() {
        return mapsize;
    }

    public boolean isEmpty() {
        return mapsize == 0;
    }

    public int hashValue(K k) {
        return (int) ((Math.abs(k.hashCode() * scale + shift) % prime) % capacity);
    }

    public V get(K key) {
        return bucketGet(hashValue(key), key);
    }

    public V put(K key, V value) {
        return bucketPut(hashValue(key), key, value);
    }

    public V remove(K key) {
        return bucketRemove(hashValue(key), key);
    }

    protected V bucketGet(int h, K k) {
        Map<K, V> map = table[h];
        if (map.get(k) == null) {
            return null;
        }
        return map.get(k);
    }

    protected V bucketPut(int h, K k, V v) {
        Map<K, V> map = table[h];
        if (map == null) {
            map = new Map<K, V>();
        }
        int oldSize = map.size();
        map.put(k, v);
        mapsize += map.size() - oldSize;
        return v;
    }

    protected V bucketRemove(int h, K k) {
        Map<K, V> map = table[h];
        if (map == null) {
            return null;
        }
        int oldSize = map.size();
        V ans = map.remove(k);
        mapsize -= oldSize - map.size();
        return ans;
    }


    public Iterable<Map.Entry<K, V>> entrySet() {
        ArrayList<Map.Entry<K, V>> entry = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                for (Map.Entry<K, V> temp : table[i].entrySet()) {
                    entry.add(temp);
                }
            }
        }
        return entry;
    }

}
