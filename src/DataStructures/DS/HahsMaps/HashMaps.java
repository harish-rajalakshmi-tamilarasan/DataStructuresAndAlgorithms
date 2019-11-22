package DataStructures.DS.HahsMaps;

import DataStructures.DS.Heaps.MaxHeap;
import DataStructures.DS.Heaps.MinHeap;
import org.apache.poi.ss.formula.functions.Index;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * @author Harish T
 */
public class HashMaps {
    static HashMap<Character, Integer> result = new HashMap<>();

    public static void main(String[] args) {
        int[] a = {10, 10, 10, 11,  12};
        //-2,-1,-1,-1,0,1,1,5
        //-2,1,1
        System.out.println(maxDistinctElemAfterKremoval(a, 3));
    }

    //....2,1,4,3,7,6,5,8....
    public static int maxContiguousSubArrayWithoutDistinctElem(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length - 1; i++) {
            int tempMin = a[i];
            int tempMax = a[i];
            for (int j = i + 1; j < a.length; j++) {
                if (tempMin > a[j]) tempMin = a[j];
                else if (tempMax < a[j]) tempMax = a[j];
                if (Math.abs(tempMax - tempMin) == j - i && j - i + 1 > max) {
                    max = j - i + 1;
                }
            }
        }
        return max;
    }

    public static int maxContiguousSubArrayWithDistinctElem(int[] a) {
        int max = Integer.MIN_VALUE;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < a.length - 1; i++) {
            int tempMin = a[i];
            int tempMax = a[i];
            hashSet.clear();
            hashSet.add(a[i]);
            for (int j = i + 1; j < a.length; j++) {
                if (tempMin > a[j]) tempMin = a[j];
                else if (tempMax < a[j]) tempMax = a[j];
                hashSet.add(a[j]);
                if (Math.abs(tempMax - tempMin) == j - i && hashSet.size() - 1 == j - i && j - i + 1 > max) {
                    max = j - i + 1;
                }
            }
        }
        return max;
    }


    public static int countSubArraysWithDistinctElemAsFullArray(int[] a) {
        int count = 0;
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (hashMap.containsKey(a[i])) {
                hashMap.get(a[i]).add(i);
            } else {
                ++count;
                hashMap.put(a[i], new ArrayList<>(Arrays.asList(i)));
            }
        }
        return 0;
    }

    private static class Indexes {
        int startIndex = -1;
        int endIndex = -1;

        Indexes(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }

    public static void getTripletEqualsAnotherValue(int[] a) {
        HashMap<Integer, Indexes> indexesMap = new HashMap<>();
        boolean isFound = false;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int temp = a[i] + a[j];
                if (!isFound && indexesMap.containsKey(temp)) {
                    Indexes indexes = indexesMap.get(a[i] + a[j]);
                    if (indexes.startIndex != i && indexes.endIndex != i && indexes.startIndex != j && indexes.endIndex != j) {
                        System.out.println(i + "," + j);
                        System.out.println(indexes.startIndex);
                        System.out.println(indexes.endIndex);
                        isFound = !isFound;
                    }
                }
                if (!isFound && indexesMap.containsKey(-temp)) {
                    Indexes indexes = indexesMap.get(-a[i] - a[j]);
                    if (indexes.startIndex != i && indexes.endIndex != i && indexes.startIndex != j && indexes.endIndex != j) {
                        System.out.println(i + "," + j);
                        System.out.println(indexes.startIndex);
                        System.out.println(indexes.endIndex);
                        isFound = !isFound;
                    }
                }
                if (!isFound) {
                    indexesMap.put(a[i] - a[j], new Indexes(i, j));
                }
            }
            if (isFound) break;
        }
    }

    public static void getTripletWithGivenProduct(int[] arr, int product) {
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            indexMap.put(arr[i], i);
        }
        for (int j = 0; j < arr.length - 2; j++) {
            for (int k = j + 1; k < arr.length - 1; k++) {
                if (arr[j] != 0 && arr[k] != 0 && product % (arr[j] * arr[k]) == 0) {
                    int temp = product / (arr[j] * arr[k]);
                    if (indexMap.containsKey(temp) && indexMap.get(temp) > k) {
                        System.out.println(arr[j] + "," + arr[k] + "," + temp);
                    }
                }
            }
        }
    }

    public static void getTripletsWithZeroSum(int[] arr) {
        Arrays.sort(arr);
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        for (int i = 2; i < arr.length; i++) {
            if (hashMap.containsKey(arr[i])) {
                hashMap.get(arr[i]).add(i);
            } else {
                hashMap.put(arr[i], new ArrayList<>(Arrays.asList(i)));
            }
        }
        //-2,-1,-1,-1,0,1,1,5
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                int temp = -arr[j] - arr[i];
                if (hashMap.containsKey(temp)) {
                    ArrayList<Integer> list = hashMap.get(temp);

                    for (int l = 1, k = list.get(list.size() - l); k > j; k--, l++) {
                        System.out.println(arr[i] + "," + arr[j] + "," + temp);
                    }

                }
            }
        }
    }

    public static List<List<String>> anagrams(String[] strings) {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            char[] characters = strings[i].toCharArray();
            Arrays.sort(characters);
            String temp = new String(characters);
            if (hashMap.containsKey(temp)) {
                hashMap.get(temp).add(strings[i]);
            } else {
                hashMap.put(temp, new ArrayList<>(Arrays.asList(temp)));
            }
        }
        List<List<String>> list = new ArrayList<>();
        for (Map.Entry<String, List<String>> entries : hashMap.entrySet()) {
            list.add(entries.getValue());
        }
        return list;
    }

    public static void noOfEmployeesUnderManagers(HashMap<Character, Character> hierarchyMap) {
        HashMap<Character, List<Character>> hashMap = new HashMap<>();
        for (Map.Entry<Character, Character> entries : hierarchyMap.entrySet()) {
            if (entries.getKey() != entries.getValue()) {
                if (hashMap.containsKey(entries.getValue())) {
                    hashMap.get(entries.getValue()).add(entries.getKey());
                } else {
                    hashMap.put(entries.getValue(), new ArrayList<>(Arrays.asList(entries.getKey())));
                }
            }
        }
        for (Map.Entry<Character, Character> entries : hierarchyMap.entrySet()) {
            populateCount(entries.getKey(), hashMap);
        }

    }

    public static int populateCount(Character character, HashMap<Character, List<Character>> hierMap) {
        int count = 0;
        if (!hierMap.containsKey(character)) {
            result.put(character, 0);
            return 0;
        } else if (result.containsKey(character)) {
            return result.get(character);
        } else {
            count = hierMap.get(character).size();
            for (Character temp : hierMap.get(character)) {
                count += populateCount(temp, hierMap);
            }
            result.put(character, count);
        }
        return count;
    }

    public static int pairsDivisible(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] % arr[j] == 0) {
                    System.out.println(arr[i] + "%" + arr[j]);
                    ++count;
                } else if (arr[j] % arr[i] == 0) {
                    System.out.println(arr[j] + "%" + arr[i]);
                    ++count;
                }
            }
        }
        return count;
    }

    static String[][] path = new String[4][4];

    public static Queue<String> constructPath(HashMap<String, String> hashMap) {
        String last = null;
        HashMap<String, String> reverseMap = new HashMap<>();
        for (Map.Entry<String, String> entries : hashMap.entrySet()) {
            reverseMap.put(entries.getValue(), entries.getKey());
        }
        for (Map.Entry<String, String> entries : reverseMap.entrySet()) {
            if (!reverseMap.containsKey(entries.getValue())) {
                last = entries.getValue();
            }
        }
        Queue<String> queue = new LinkedList<>();
        while (last != null) {
            queue.add(last);
            last = hashMap.get(last);
        }
        return queue;
    }

    public static int[][] findEqualPairsOfSum(int[] arr) {
        int pairSum = 0;
        int[][] pairSumArray = new int[2][2];
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                pairSum = arr[i] + arr[j];
                if (hashMap.containsKey(pairSum)) {
                    int first = hashMap.get(pairSum).get(0);
                    int second = hashMap.get(pairSum).get(1);
                    if (!(first == i || first == j) && !(second == j || second == i)) {
                        pairSumArray[0][0] = i;
                        pairSumArray[0][1] = j;
                        pairSumArray[1][0] = first;
                        pairSumArray[1][1] = second;
                    }
                } else {
                    hashMap.put(pairSum, new ArrayList<>(Arrays.asList(i, j)));
                }
            }
        }
        return pairSumArray;
    }

    public static void arrayElemDivAtleastOneAnother(int[] arr) {
        HashSet<Integer> indexSet = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1 + i; j < arr.length; j++) {
                if (arr[j] != 0 && arr[i] % arr[j] == 0) {
                    if (!indexSet.contains(arr[i])) {
                        indexSet.add(arr[i]);
                        System.out.println(arr[i]);
                    }
                }
                if (arr[i] != 0 && arr[j] % arr[i] == 0) {
                    if (!indexSet.contains(arr[j])) {
                        indexSet.add(arr[j]);
                        System.out.println(arr[j]);
                    }
                }
            }
        }
    }

    public static int maxDiffFirstLastIndex(int[] a) {
        int max = 0;
        HashMap<Integer, Pair> indexMap = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (indexMap.containsKey(a[i])) {
                indexMap.get(a[i]).end = i;
                Pair temp = indexMap.get(a[i]);
                if (max < temp.end - temp.start) {
                    max = temp.end - temp.start;
                }
            } else {
                indexMap.put(a[i], new Pair(i));
            }
        }
        return max;
    }

    public static class Pair {
        int start = -1;
        int end = -1;

        public Pair(int start) {
            this.start = start;
        }
    }

    public static int maxDiffHighestLowestFreqIndex(int[] a) {
        int max = 0;
        int min = 0;
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (indexMap.containsKey(a[i])) {
                indexMap.replace(a[i], indexMap.get(a[i]) + 1);
                int temp = indexMap.get(a[i]);
                if (temp > max) max = temp;
            } else {
                indexMap.put(a[i], 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : indexMap.entrySet()) {
            int temp = entry.getValue();
            if (temp == 1) {
                min = 1;
                break;
            }
            if (min > temp) {
                min = temp;
            }
        }
        return max - min;
    }

    public static void getAPTripletsInSortedArray(int[] arr) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int temp : arr) {
            hashSet.add(temp);
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int temp = arr[j] * 2 - arr[i];
                if (hashSet.contains(temp)) {
                    System.out.println(arr[i] + "," + arr[j] + "," + temp);
                }
            }
        }
    }

    public static void getTripletsWithGivenSum(int[] arr, int k) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int temp : arr) {
            hashSet.add(temp);
        }
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                int temp = k - arr[j] - arr[i];
                if (hashSet.contains(temp)) {
                    System.out.println(arr[i] + "," + arr[j] + "," + temp);
                }
            }
        }
    }

    public static void getSmallestRangeFromThreeLists(int[] a, int[] b, int[] c) {
        MaxHeap maxHeap = new MaxHeap(3);
        maxHeap.insert(a[0]);
        maxHeap.insert(b[0]);
        maxHeap.insert(c[0]);
        int minRange = Integer.MAX_VALUE;
        int aIndex = 1;
        int bIndex = 1;
        int cIndex = 1;

    }

    public static int maxDistinctElemAfterKremoval(int[] a, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int temp : a) {
            if (hashMap.containsKey(temp)) {
                hashMap.replace(temp, hashMap.get(temp) + 1);
            } else {
                hashMap.put(temp, 1);
            }
        }
        int maxDis = hashMap.size();
        int diff = a.length - maxDis;
        if (diff >= k) return maxDis;
        return maxDis - (k - diff);

    }

}




