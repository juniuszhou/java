package Algorithm;

public class MyHeap {
    public int[] a = new int[100];
    public int len = 0;
    public int left(int i) { return i*2; }
    public int right(int i) {return i*2+1;}
    public int parent(int i) {return i/2;}

    public void swap(int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public void add(int i) {
        len++;
        a[len] = i;
        siftUp(len);
    }

    public int remove() throws Exception{
        if (len < 1) throw new Exception();
        int result = a[1];
        a[1] = a[len];
        len--;
        heapify(1);
        return result;
    }

    public void siftUp(int i) {
        int tmp = a[i];
        while (i > 1) {
            if (a[i] < a[parent(i)]) {
                a[i] = a[parent(i)];
            } else break;
        }
        a[i] = tmp;
    }

    public void heapify(int i) {
        int l = left(i);
        int r = right(i);
        if (l > len) return;
        else if (r > len) {
            if (a[i] > a[l]) swap(i, l);
        } else {
            if (a[i] > Math.min(a[l], a[r])) {
                if (a[l] > a[r]) {
                    swap(i, l);
                    heapify(l);
                } else {
                    swap(i, r);
                    heapify(r);
                }
            }
        }
    }

    public static void main(String[] args) {
        MyHeap heap = new MyHeap();
        heap.add(1);
        try {
            heap.remove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
