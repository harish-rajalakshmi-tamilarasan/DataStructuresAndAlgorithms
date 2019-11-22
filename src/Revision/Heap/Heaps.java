package Revision.Heap;

/**
 * @author Harish T
 */
public interface Heaps {
    int size();

    int getTop();

    int removeTop();

    void insert(int value);

    boolean hasRight(int position);

    boolean hasLeft(int position);

    int getRight(int position);

    int getLeft(int position);

    void upHeap(int position);

    void downHeap(int position);

    void heapSort(int[] arr);
}
