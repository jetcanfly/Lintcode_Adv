public class Solution {
    /**
     * @param A an integer array
     * @return an integer
     */
    public int stoneGame(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;
        // sum[i][j]表示第i个石子到第j个石子的总价值
        int[][] sum = new int[n][n];
        for (int i = 0; i < n; i++) {
            sum[i][i] = A[i];
            for (int j = i + 1; j < n; j++) {
                sum[i][j] = sum[i][j - 1] + A[j];
            }
        }

        // dp[i][j]表示把第i个石子到第j个石子合并到一起的总花费
        int[][] dp = new int[n][n];
        boolean[][] flag = new boolean[n][n];

        return search(0, n - 1, dp, flag, sum);
    }

    private int search(int l, int r, int[][] dp, boolean[][] flag, int[][] sum) {
        if (flag[l][r]) {
            return dp[l][r];
        }

        flag[l][r] = true;
        if (l > r) {
            dp[l][r] = 0;
            return dp[l][r];
        }
        if (l == r) {
            dp[l][r] = 0;
            return dp[l][r];
        }

        dp[l][r] = Integer.MAX_VALUE;

        for (int k = l; k < r; k++) {
            dp[l][r] = Math.min(dp[l][r], search(l, k, dp, flag, sum) + search(k + 1, r, dp, flag, sum) + sum[l][r]);
        }

        return dp[l][r];
    }
}
