public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */
    public int kSum(int A[], int k, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        if (k <= 0) {
            return 0;
        }
        if (target <= 0) {
            return 0;
        }

        // dp[i][j][t]: 前i个数取j个有多少可以组成和为t
        int[][][] dp = new int[A.length + 1][k + 1][target + 1];

        // 前i个数取0个有多少可以组成和为0
        for (int i = 0; i < A.length + 1; i++) {
            dp[i][0][0] = 1;
        }

        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
                for (int t = 0; t < target + 1; t++) {
                    dp[i][j][t] = dp[i - 1][j][t];
                    if (t - A[i - 1] >= 0) {
                        dp[i][j][t] += dp[i - 1][j - 1][t - A[i - 1]];
                    }
                }
            }
        }

        return dp[A.length][k][target];
    }
}
