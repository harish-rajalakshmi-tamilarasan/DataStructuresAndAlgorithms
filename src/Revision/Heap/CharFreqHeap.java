package Revision.Heap;

/**
 * @author Harish T
 */
public class CharFreqHeap {
    public static class KeyFreq {
        char key;
        int freq;

        KeyFreq(char key, int freq) {
            this.key = key;
            this.freq = freq;
        }
    }

    public KeyFreq createObj(char key, int freq){
        return new KeyFreq(key,freq);
    }
    private int size = 0;
    private KeyFreq[] heapArr;

    public CharFreqHeap(int capacity) {
        heapArr = new KeyFreq[capacity];
    }

    public int size() {
        return size;
    }

    public KeyFreq getMin() {
        return heapArr[0];
    }

    public KeyFreq removeMin() {
        KeyFreq value = heapArr[0];
        heapArr[0] = heapArr[size - 1];
        --size;
        downHeap(0);
        return value;
    }

    public boolean hasRight(int position) {
        return (size > 2 * position + 2);
    }

    public boolean hasLeft(int position) {
        return (size > 2 * position + 1);
    }

    public int getParent(int position) {
        if (position <= 0 || position > size - 1) return -1;
        return (position - 1) / 2;
    }

    public int getRight(int position) {
        return position * 2 + 2;
    }

    public int getLeft(int position) {
        return position * 2 + 1;
    }

    public void swap(int a, int b) {
        KeyFreq temp = heapArr[a];
        heapArr[a] = heapArr[b];
        heapArr[b] = temp;
    }

    public void insert(KeyFreq element) {
        heapArr[size++] = element;
        upHeap(size - 1);
    }

    public void upHeap(int position) {
        int parent = getParent(position);
        if (parent != -1) {
            if ((heapArr[parent].freq < heapArr[position].freq) || (heapArr[parent].freq == heapArr[position].freq && heapArr[parent].key < heapArr[position].key)) {
                swap(position, parent);
                upHeap(parent);
            }
        }
    }

    public void downHeap(int position) {
        if (hasLeft(position)) {
            int value = getLeft(position);
            if (hasRight(position)) {
                int right = getRight(position);
                if ((heapArr[right].freq > heapArr[value].freq) || (heapArr[right].freq == heapArr[value].freq && heapArr[right].key > heapArr[value].key)) {
                    value = right;
                }
            }
            if ((heapArr[position].freq < heapArr[value].freq) || (heapArr[position].freq == heapArr[position].freq && heapArr[position].key < heapArr[value].key)) {
                swap(value, position);
                downHeap(value);
            }
        }
    }

}


