/* 记忆化搜索 */
public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        }
        if (values.length <= 2) {
            return true;
        }

        int sum = 0;
        for (int v : values) {
            sum += v;
        }

        int[] dp = new int[values.length + 1];
        boolean[] flag = new boolean[values.length + 1];

        int firstValue = search(values.length, values, dp, flag);
        return firstValue > (sum - firstValue);
    }

    private int search(int n, int[] values, int[] dp, boolean[] flag) {
        if (flag[n]) {
            return dp[n];
        }

        flag[n] = true;
        if (n == 0) {
            dp[n] = 0;
            return dp[n];
        }
        if (n == 1) {
            dp[n] = values[values.length - 1];
            return dp[n];
        }
        if (n == 2) {
            dp[n] = values[values.length - 1] + values[values.length - 2];
            return dp[n];
        }
        if (n == 3) {
            dp[n] = values[values.length - 2] + values[values.length - 3];
            return dp[n];
        }

        // Left (pick 1 coin)
        int left_left = search(n - 1 - 1, values, dp, flag);
        int left_right = search(n - 1 - 2, values, dp, flag);
        int left = Math.min(left_left, left_right) + values[values.length - n];
        int right_left = search(n - 2 - 1, values, dp, flag);
        int right_right = search(n - 2 - 2, values, dp, flag);
        int right = Math.min(right_left, right_right) + values[values.length - n] + values[values.length - n + 1];
        dp[n] = Math.max(left, right);          // 先手取得使先手价值最大

        return dp[n];
    }
}


/* DP */
public class Solution {
    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        }
        if (values.length <= 2) {
            return true;
        }

        int sum = 0;
        for (int v : values) {
            sum += v;
        }

        int n = values.length;
        // dp: 还剩i个硬币的时候，先手可以获得的最大价值
        int[] dp = new int[n + 1];

        // Initialization （从左向右取）
        dp[0] = 0;
        dp[1] = values[n - 1];  // 还剩最后一个硬币的价值
        dp[2] = values[n - 1] + values[n - 2];
        dp[3] = values[n - 2] + values[n - 3];

        // Functional
        for (int i = 4; i < n; i++) {
            // Left (pick 1)
            int left_left = dp[i - 1 - 1];
            int left_right = dp[i - 1 - 2];
            int left = Math.min(left_left, left_right) + values[n - i]; // 后手取得使先手价值最小
            // Right (pick 2)
            int right_left = dp[i - 2 - 1];
            int right_right = dp[i - 2 - 2];
            int right = Math.min(right_left, right_right) + values[n - i] + values[n - i + 1];  // 后手取得使先手价值最小

            dp[i] = Math.max(left, right);          // 先手取得使先手价值最大
        }

        return dp[n] > (sum - dp[n]);
    }
}
