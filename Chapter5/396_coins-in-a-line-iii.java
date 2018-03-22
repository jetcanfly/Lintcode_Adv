public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        }

        // dp[i][j] 表示还剩第i个到第j个硬币时，先手最后取得的最大价值
        int[][] dp = new int[values.length + 1][values.length + 1];
        boolean[][] flag = new boolean[values.length + 1][values.length + 1];

        int firstValues = search(0, values.length - 1, values, dp, flag);

        int sum = 0;
        for (int v : values) {
            sum += v;
        }
        return firstValues > (sum - firstValues);
    }

    private int search(int i, int j, int[] values, int[][] dp, boolean[][] flag) {
        if (flag[i][j]) {
            return dp[i][j];
        }
        flag[i][j] = true;

        if (i > j) {
            dp[i][j] = 0;
            return dp[i][j];
        }
        if (i == j) {
            dp[i][j] = values[i];
            return dp[i][j];
        }
        if (i + 1 == j) {
            dp[i][j] = Math.max(values[i], values[j]);
            return dp[i][j];
        }

        int left_left = search(i + 2, j, values, dp, flag);
        int left_right = search(i + 1, j - 1, values, dp, flag);
        int left = Math.min(left_left, left_right) + values[i];

        int right_left = search(i + 1, j - 1, values, dp, flag);
        int right_right = search(i, j - 2, values, dp, flag);
        int right = Math.min(right_left, right_right) + values[j];

        dp[i][j] = Math.max(left, right);
        return dp[i][j];
    }
}
