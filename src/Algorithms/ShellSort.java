package Algorithms;

/**
 * @author Harish T
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] rr = {12, 3, 4, 21, 3, 5453, 756, 234, 13132, 123, 43, 5};
        shellSort(rr, 3);
        for(int temp:rr){
            System.out.println(temp);
        }
    }

    public static void shellSort(int[] arr, int gap) {
        if (gap > 0) {
            for (int k = 0; k < gap; k++) {
                for (int j = gap+k; j <=arr.length - 1; j = j + gap) {
                    int key = arr[j];
                    int i = j - gap;
                    while (i >= 0 && key < arr[i]) {
                        arr[i + gap] = arr[i];
                        i = i - gap;
                    }
                    arr[i + gap] = key;
                }
            }
            gap = gap / 2;
           shellSort(arr, gap);
        }
    }

}
