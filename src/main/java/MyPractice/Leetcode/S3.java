package Leetcode;

/**
 * Created by juzhou on 2/28/2015.
 */
public class S3 {
    public static class Solution {
        public int lengthOfLongestSubstring(String s) {
            int[] a = new int[256 * 256];
            for(int i = 0; i < a.length; ++i)
                a[i] = -1;

            int max = 0;
            int pos = 0;
            for(int i = 0; i < s.length(); ++i){
                char c = s.charAt(i);
                int old = a[c];
                System.out.println(c + " " + old);
                if (old == -1){
                    if (pos > 0){
                        max = Math.max(i-pos, max);
                    }
                    else {
                        max++;
                    }
                    System.out.println("max " + max + " from " + pos + " to " + i);
                    a[c] = i;
                }
                else{
                    if (old > pos)
                        pos = old;


                    System.out.println("pos " + pos + " i-pos " + (i - pos));
                    max = Math.max(max, i - pos);
                    a[c] = i;
                }
            }
            return max;
        }
    }
    public static void main(String[] args){
        Solution s = new Solution();

        System.out.println( s.lengthOfLongestSubstring("kqsgwtzvtzrxaljqvlqawry"));
    }
}
