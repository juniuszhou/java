package Google;

public class Manacher {
    public void manacher(String str) {
        int boundary = 0;
        int center = 0;
        int[] dp= new int[str.length()+1];
        for(int i = 1;i<str.length();i++) {
            if (boundary > i) dp[i] = Math.min(dp[2*center -i], (boundary-i));
            else dp[i] = 1;
            while (str.charAt(i-dp[i]) == str.charAt(i+dp[i])) dp[i]++;
            if (i+dp[i] > boundary) {
                boundary = i+dp[i];
                center = i;
            }
        }
    }
}
