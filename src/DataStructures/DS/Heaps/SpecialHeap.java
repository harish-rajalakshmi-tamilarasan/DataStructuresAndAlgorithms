package DataStructures.DS.Heaps;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Harish T
 */
public class SpecialHeap {
    public static void main(String[] args) {
        String str = "aaaabb";
        stringSeperation(str);
    }

    public static void stringSeperation(String str) {
        char[] chars = str.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (hashMap.containsKey(chars[i])) {
                hashMap.replace(chars[i], hashMap.get(chars[i]) + 1);
            } else {
                hashMap.put(chars[i], 1);
            }
        }
        Key[] keys = new Key[hashMap.size()];
        int i = 0;
        for (Map.Entry<Character, Integer> temp : hashMap.entrySet()) {
            keys[i] = new Key(temp.getValue(), temp.getKey());
            i++;
        }
        SpecialHeap heap = SpecialHeap.buildHeap(keys);
        StringBuilder stringBuilder = new StringBuilder();
        Key temp = null;
        Key prev = null;
        while (heap.getSize() > 0) {
            stringBuilder.append(heap.getMax().key);
            if (heap.getMax().frequency == 1) {
                heap.removeMax();
            } else {
                --heap.getMax().frequency;
                prev = heap.removeMax();
                if (temp != null) {
                    heap.insert(temp);
                }
                temp = prev;
            }
        }
        System.out.println(stringBuilder.toString());
    }

    private int size = 0;
    private Key[] heapArray;

    private static class Key {
        Key(int frequency, char key) {
            this.frequency = frequency;
            this.key = key;
        }

        int frequency;
        char key;
    }

    public SpecialHeap(int capacity) {
        heapArray = new Key[capacity];
    }

    public int getSize() {
        return size;
    }

    public int getParent(int position) {
        if (position < 0 || position > size - 1) return -1;
        if (position == 0) return -1;
        return (position - 1) / 2;
    }

    private int getRight(int position) {
        return position * 2 + 2;
    }

    private int getLeft(int position) {
        return position * 2 + 1;
    }

    private boolean hasRight(int position) {
        return size > (2 * position + 2);
    }

    private boolean hasLeft(int position) {
        return size > (2 * position + 1);
    }

    private void swap(int i, int j) {
        Key temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    public Key getMax() {
        return heapArray[0];
    }

    public Key removeMax() {
        Key maxValue = heapArray[0];
        heapArray[0] = heapArray[size - 1];
        --size;
        downHeap(0);
        return maxValue;
    }

    public void downHeap(int i) {
        if (hasLeft(i)) {
            int value = getLeft(i);
            if (hasRight(i)) {
                value = heapArray[value].frequency > heapArray[getRight(i)].frequency ? value : getRight(i);
            }
            if (heapArray[i].frequency < heapArray[value].frequency) {
                swap(i, value);
                downHeap(value);
            }
        }
    }

    public void insert(Key value) {
        heapArray[size++] = value;
        upHeap(size - 1);
    }

    public void upHeap(int position) {
        if (getParent(position) == -1) return;
        if (heapArray[getParent(position)].frequency < heapArray[position].frequency) {
            swap(position, getParent(position));
            upHeap(getParent(position));
        }
    }

    public static SpecialHeap buildHeap(Key[] heapArr) {
        SpecialHeap heap = new SpecialHeap(heapArr.length);
        heap.size = heapArr.length;
        for (int i = (heapArr.length / 2) + 1; i < heapArr.length; i++) {
            heap.heapArray[i] = heapArr[i];
        }
        int index = heapArr.length / 2;
        while (index >= 0) {
            heap.heapArray[index] = heapArr[index];
            heap.downHeap(index);
            index--;
        }
        return heap;
    }
}
