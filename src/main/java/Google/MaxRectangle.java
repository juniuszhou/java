package Google;

public class MaxRectangle {
    public void scanLeft(int[] a, int[] left) {
        int[] tempArr = new int[a.length+2];
        tempArr[0] = -1;
        tempArr[a.length+1] = -1;
        for(int i=0;i<a.length;i++) tempArr[i+1] = a[i];
        left[0] = 0;
        for (int i=1;i<a.length;i++) {
            int k = i;
            while( tempArr[i] <= tempArr[k-1])
            k = left[k-1];
            left[i] = k;
        }
    }
    public void scanRight(int[] a, int[] right) {
        right[a.length-1] = a.length-1;
        for(int i=a.length-2;i>=0;i--) {
            if (a[i] >= a[i+1]) right[i] = right[i+1];
            else right[i] = i;
        }
    }

    public int max(int[] a) {
        int[] left = new int[a.length];
        int[] right = new int[a.length];
        scanLeft(a, left);scanRight(a, right);
        int result = Integer.MIN_VALUE;
        for(int i=0;i<a.length;i++) {
            result = Math.max(result, a[i]*(right[i]-left[i]+1));
        }
        return result;
    }


}
