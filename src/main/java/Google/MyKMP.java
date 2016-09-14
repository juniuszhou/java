package Google;
public class MyKMP {
    public static void main(String[] args) {

        String str = "aaaab";
        String str2 = "asdfa" + str + "de" + str;
        int[] a = pattern(str);
        int j = 0;
        for (int i = 0; i < str2.length(); i++){
            while(j > 0 && str2.charAt(i) != str.charAt(j)) j = a[j];
            if (str2.charAt(i) == str.charAt(j)) j++;
            if (j == str.length() - 1) {
                System.out.println(""+(i-j));
                j = a[j];
            }
        }
        System.out.println("over");
    }

    // standard approach same with classic book.
    public int[] getNext(String b)
    {
        int len=b.length();
        int j=0;

        int next[]=new int[len+1];//next表示长度为i的字符串前缀和后缀的最长公共部分，从1开始
        next[0]=next[1]=0;

        for(int i=1;i<len;i++)//i表示字符串的下标，从0开始
        {//j在每次循环开始都表示next[i]的值，同时也表示需要比较的下一个位置
            while(j>0&&b.charAt(i)!=b.charAt(j))j=next[j];
            if(b.charAt(i)==b.charAt(j))j++;
            next[i+1]=j;
        }

        return next;
    }

    public static int[] pattern2(String str) {
        int[] next = new int[str.length()];
        int j = 0;
        for( int i =1;i<str.length();i++) {
            while (j > 0 && str.charAt(j) != str.charAt(i)) j = next[j-1];
            if (str.charAt(i) == str.charAt(j)) j++;
            next[i] = j;
        }
        return next;
    }

    public static int[] pattern(String str) {
        int[] result = new int[str.length()];
        result[0] = -1;
        int j = -1;
        int i = 0;
        while (i < str.length() - 1) {
            if (j == -1 || str.charAt(i) == str.charAt(j)) {
                ++i;
                ++j;
//                if (str.charAt(i) != str.charAt(j))
//                    result[i] = j;
//                else
                    result[i] = result[j];
            } else j = result[j];
        }
        return result;
    }
}
