public class Solution {
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        // f[i][j] 表示以i和j作为正方形右下角可以拓展的最大边长
        int[][] dp = new int[m][n];

        // Initialization
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0];
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
        }

        // Functional
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    int left = dp[i][j - 1];
                    int up = dp[i - 1][j];
                    int corner = dp[i - 1][j - 1];
                    dp[i][j] = Math.min(left, up);
                    dp[i][j] = Math.min(dp[i][j], corner);
                    dp[i][j] += 1;  // add current one
                }
            }
        }

        // Answer
        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen * maxLen;
    }
}
