package Algorithms;

import java.util.ArrayList;

class PowerSet {
    public static void main(String[] args) {
        String[] arr = {"1", "2", "3", "4"};
        ArrayList<String> sublist = new ArrayList<>();
        sublist.add("");
        subsets(arr, sublist, 0);
        int i=0;
        for (String temp : sublist) {
            System.out.println(i+" "+temp);
            i++;

        }
    }

    public static void subsets(String[] arr, ArrayList<String> sublist, int index) {
        if (index < arr.length) {
            int length = sublist.size();
            for (int i = 0; i < length; i++) {
                sublist.add(sublist.get(i).concat(arr[index]));
            }
            subsets(arr,sublist,++index);
        }
    }
}