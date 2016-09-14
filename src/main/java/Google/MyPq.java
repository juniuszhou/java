package Google;

import java.util.PriorityQueue;

public class MyPq {
    public int[] a = new int[100];
    public int left(int i) {return i*2;}
    public int right(int i) {return i*2+1;}
    public int parent(int i) {return i / 2;}

    public void swap(int i, int j) {
        int  tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public int length = 0;
    public void add(int i) {
        length++;
        a[length] = i;
        siftUp(length);
    }
    //pq also need method sift up for add a new element.
    //when siftup, just compare with parent element.

    public void siftUp(int i) {
        int k = a[i];
        while (i>1) {
            int p = parent(i);
            if (a[i] >= a[p]) return ;
            else {
                a[i] = a[p];
                i = p;
            }
        }
        a[i] = k;
    }

    // heapify is a process top down. alias sift down.
    public void justify(int p) {

        int left = left(p);
        int right = right(p);
        if (left > length ) return;
        else if (right > length) {
            if (a[p] < a[left]) return;
            else {
                swap(p, left);
                justify(left);
            }
        } else {
            if (Math.min(a[left], a[right]) <= a[p]) return;
            if (a[left] < a[right]) {
                swap(p,left);
            } else {
                swap(p,right);
            }
        }
    }

    public int remove(int p) {

        int tmp = a[1];
        a[1] = a[length];
        length--;
        justify(1);
        return 0;

    }

    public static void main(String[] args) {
        MyPq my = new MyPq();
//        int i = 0;  int j = 100;
//        my.swap(i, j);
//        System.out.println(i);
//        System.out.println(j);

        Integer i = 0;  Integer j = 100;
        my.swap(i, j);
        System.out.println(i);
        System.out.println(j);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(1); pq.add(1);

        System.out.println(pq.peek());
        System.out.println(pq.size());
    }
}
