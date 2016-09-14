package Google;

public class Solution {
    public boolean isMatch(String s, String p) {
       int i = 0;
        int j = 0;
       for(;i<Math.min(s.length(), p.length()); i++) {
           if (i<p.length()-1 && p.charAt(i+1) == '*') {
                if (s.charAt(i) == p.charAt(i) || p.charAt(i) == '.') {
                    if (i < p.length() - 2 && isMatch(s.substring(i, s.length()), p.substring(i+2, p.length())))
                        return true;
                    else return isMatch(s.substring(i+1, s.length()), p.substring(i, p.length()));

                } else {
                    if (i < p.length() - 2)
                        return isMatch(s.substring(i, s.length()), p.substring(i+2, p.length()));
                    else return (i == s.length() - 1);
                }
           } else if (p.charAt(i) == '.') continue;
           else if (p.charAt(i) != s.charAt(i)) return false;
        }
        // if all left in p can skip

        while (i < p.length()) {
            if (i < p.length() - 1 && p.charAt(i+1) == '*')
                i += 2;
            else return false;
        }

        return s.length() == p.length();

    }

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] testArray = {3,1,5,8};
        String testStr = "adinqefd";

        // from the case , we can see we should deal with * ahead.
        System.out.println(s.isMatch("aa", "a*"));

    }
}
