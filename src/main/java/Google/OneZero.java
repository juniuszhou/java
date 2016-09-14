package Google;

public class OneZero {
    public void simple(int[] a, int sum) {
        boolean[][] dp = new boolean[sum+1][a.length+1];
        for(int i=0;i<=a.length;i++) dp[0][i] = true;

        for(int i=1;i<=sum;i++)
            for(int j=1;j<=a.length;j++) {
                dp[i][j] = dp[i][j-1];
                if (!dp[i][j] && i-a[j-1] > 0)
                    dp[i][j] = dp[i-a[j-1]][j-1];
            }
    }

    public int One(int[] value, int[] cost, int w) {
        int[][] dp = new int[w+1][value.length+1];
        for(int i=0;i<=w;i++) dp[i][0] = 0;
        for(int i=0;i<=value.length;i++) dp[0][i] = 0;

        for(int i=1;i<=value.length;i++) {
            for(int j=1;j<=w;j++) {
                dp[j][i] = dp[j][i-1];
                if (j > cost[i])
                    dp[j][i] = Math.max(dp[j][i-1], dp[j-cost[i]][i-1]+value[i]);
            }
        }
        return dp[w][value.length];
    }

    public int SingleOne(int[] value, int[] cost, int w) {
        int[][] dp = new int[w+1][value.length+1];
        for(int i=0;i<=w;i++) dp[i][0] = 0;
        for(int i=0;i<=value.length;i++) dp[0][i] = 0;

        for(int i=1;i<=value.length;i++) {
            for(int j=w;j>=0;j--) {
                dp[j][i] = dp[j][i-1];
                if (j > cost[i])
                    dp[j][i] = Math.max(dp[j][i-1], dp[j-cost[i]][i-1]+value[i]);
            }
        }
        return dp[w][value.length];
    }



    public static void main(String[] args) {

    }
}
