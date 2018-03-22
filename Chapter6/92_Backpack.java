public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        
        int n = A.length;
        
        // State: dp[i][j]: 取前i个放入大小为j的背包的最大体积
        int[][] dp = new int[2][m + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j - A[i - 1] >= 0) {    // 是否可以拿当前的item.
                    dp[i % 2][j] = dp[(i - 1) % 2][j - A[i - 1]] + A[i - 1];
                }
                // 取拿或不拿之中较大的。 dp[i - 1][j] = 不拿
                dp[i % 2][j] = Math.max(dp[i % 2][j], dp[(i - 1) % 2][j]);
            }
        }
        
        return dp[n % 2][m];
    }
}