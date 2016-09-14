package Google;
public class TreeArray {
    public int[] a = new int[100];
    public int lowBit(int i) {return i&-i;}
    public void add(int pos, int i) {
        while (pos < 100) {
            a[pos] += i;
            pos += lowBit(pos);
        }
    }
    public int sum(int pos) {
        int sum = 0;
        while (pos > 0) {
            sum += a[pos];
            pos -= lowBit(pos);
        }
        return sum;
    }
}
