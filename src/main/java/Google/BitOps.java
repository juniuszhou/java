package Google;

public class BitOps {
    public static void add(int i, int j) {

    }

    public static boolean threeTimes(int i) {
        if (i < 0) i = -i;
        if (i < 0) return false;

        int even = 0;
        int odd = 0;
        while (i > 0) {
            if ((i & 1) == 1) even += 1;
            i = i >> 1;
            if ((i & 1) == 1) odd += 1;
            i = i >> 1;
        }
        return ( even - odd ) % 3 == 0;
    }

    public static void call(int i) {
        System.out.println(Integer.toHexString(i));
        int ones = 0;
        int twos = 0;

        int x = i;
        twos |= ones & x;
        ones ^= x;
        int not_threes = ~(ones & twos);
        ones = ones & not_threes;
        twos = twos & not_threes;
        System.out.println(Integer.toHexString(ones));
        System.out.println(Integer.toHexString(twos));
    }

    public static void main(String[] str) {
        System.out.println(threeTimes(3));
        System.out.println(threeTimes(-3));
        System.out.println(threeTimes(Integer.MAX_VALUE));
        System.out.println(threeTimes(Integer.MIN_VALUE));

    }
}
