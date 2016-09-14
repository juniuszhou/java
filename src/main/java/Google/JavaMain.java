package Google;

import java.util.*;

public class JavaMain {
    public int binarySearch(int begin, int end, int[] a, int key) {
        if (begin >= end) return -1;
        if (begin == end - 1) {
            if (a[begin] == key) return begin;
            else return -1;
        }
        while (begin < end) {
            int mid = begin + (end - begin) / 2;
            if (a[mid] == key) return mid;
            else if (a[mid] > key) end = mid - 1;
            else begin = mid + 1;
        }
        return -1;
    }

    public void next(String str) {
        int[] a = new int[str.length() + 1];
        a[0] = 0;
        a[1] = 0;
        int j = -1;

     }

    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        String str = "";

    }
}
