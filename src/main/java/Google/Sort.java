package Google;
public class Sort {
    public void swap(int i, int j) {
        int tmp = i;
        i = j;
        j = tmp;
    }
    public void mergeSort(int[] a) {
         if (a == null || a.length < 2) return ;
        else if (a.length < 3) {
             if (a[0] <= a[1]) return ;
             else swap(a[0], a[1]);
         } else {
            int mid = a.length / 2;
             int[] left = new int[mid];
             int[] right = new int[a.length - mid];
             for(int i=0;i<mid;i++) left[i] = a[i];
             for(int i=0;i<a.length;i++)  right[i] = a[mid+i];
             mergeSort(left);mergeSort(right);
             int i=0; int l=0; int r=0;
             while (i < a.length) {
                 if (r >= a.length-mid || (l < mid && left[l] < right[r])) {
                     a[i] = left[l];
                     l++;
                 } else {
                     a[i] = right[r];
                     r++;
                 }
                 i++;
             }
         }
    }

    public void quickSort(int[] a, int l, int r) {
        if (r <= l) return ;
        if (r == l + 1) {
            if (a[l] <= a[r]) return ;
            int tmp = a[l];
            a[l] = a[r];
            a[r] = tmp;
        } else {
            int pivot = a[r];
            int begin = l;
            int end = r;

            while (l < r) {
                while (l < r && a[l] < pivot) l++;
                if (l < r) {
                    a[r] = a[l];
                    r--;
                }
                while (l < r && a[r] > pivot) r--;
                if (l < r) {
                    a[l] = a[r];
                    l++;
                }

            }

            a[l] = pivot;
            quickSort(a, begin, l-1);
            quickSort(a, l+1, end);
        }
    }

    // if you set the last one as mid then loop from left to right.
    // if you set the first one as mid, loop from right to left easier to implement.
    public void quickTwo(int[] a, int l, int r) {
        int p = l;
        int mid = a[r];

        // p will be last one smaller than mid.
        while (l < r) {
            if (a[r] >= mid) {
                l++;
            } else {
                swap(p, l);
                p++;
            }
        }
        swap(p, r);
        quickSort(a, l, p);
        quickSort(a, p+1, r);
    }
    public static void main(String[] args) {
        String[][] a = {{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
        int[] j = {1,-1,-2,-3,9,8,7,4,5,6};
        int[] i = {1,2};
        new Sort().quickSort(j,0, j.length-1);
        System.out.println("");
    }
}
