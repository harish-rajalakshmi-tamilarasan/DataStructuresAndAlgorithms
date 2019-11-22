package DataStructures.DS.Arrays;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author Harish T
 */
public class OrderStatistics {
    public static void main(String[] args) {
        int[] a = {3, 2, 5, 23, 5, 7, 2, 8, 33, 5, 8, 0};
        System.out.println(kthSmallestElement(a, 0, a.length - 1, 11));
    }

    public static int kthSmallestElement(int[] arr, int start, int end, int k) {
        randomPartition(arr, start, end);
        int partition = partition(arr, start, end);
        if (partition == k - 1) return arr[partition];
        if (partition >= k) return kthSmallestElement(arr, start, partition - 1, k);
        return kthSmallestElement(arr, partition + 1, end, k);
    }

    public static int partition(int[] arr, int start, int end) {
        int j = start - 1;
        int temp = 0;
        int pivot = arr[end];
        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {
                ++j;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        arr[end] = arr[++j];
        arr[j] = pivot;
        return j;
    }

    public static void randomPartition(int[] arr, int start, int end) {
        System.out.println(start + "=" + end);
        Random random = new Random();
        int probableValue = random.nextInt(end - start + 1) + start;
        int temp = arr[probableValue];
        arr[probableValue] = arr[end];
        arr[end] = temp;
    }


   /** public static int[] kthMaximumSubArray(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            int sumSoFar = 0;
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    sumSoFar += arr[i];
                    if (queue.size() != k) {
                        queue.add(arr[i]);
                    } else {
                        if (!queue.isEmpty() && queue.peek() < arr[i]) {
                            queue.poll();
                            queue.add(arr[i]);
                        }
                    }
                } else {
                    if (queue.size() != k) {
                        queue.add()
                    }
                }
            }
        }
    }**/
   public static void generateAllSubArrays(int[] a){
       for(int i=0;i<a.length;i++){
           for(int j=0;j<=i;j++){
               if(i==j) System.out.println(a[i]);
               else System.out.println();
           }
       }
   }
}
