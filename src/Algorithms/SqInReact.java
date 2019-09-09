package Algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Harish T
 */
public class SqInReact {

    public static List<Integer> sqInReact(int lng, int wdth) {
        List<Integer> list = new ArrayList<>();
        if (lng == wdth) {
            list.add(lng);
            return list;
        }
        if (lng > wdth) {
            list.add(wdth);
            list.addAll(sqInReact(wdth, lng - wdth));
            return list;
        }
        list.add(lng);
        list.addAll(sqInReact(lng, wdth - lng));
        return list;
    }

    public static List<Integer> sqInRect(int lng, int wdth) {
        if (lng == wdth) {
            return new ArrayList<Integer>();
        } else return sqInReact(lng, wdth);
    }

    public static void main(String[] args) {
        for (Integer tmp : sqInReact(9, 6)) {
            System.out.println(tmp);
        }
    }
}


