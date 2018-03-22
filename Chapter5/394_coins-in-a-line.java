/* 记忆化搜索 */
public class Solution {
    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        if (n < 0) {
            return false;
        }

        boolean[] flag = new boolean[n + 1];
        // dp[i] 表示还剩i个硬币的时候，先手的最后能否获胜
        boolean[] dp = new boolean[n + 1];

        return search(n, dp, flag);
    }

    private boolean search(int n, boolean[] dp, boolean[] flag) {
        if (flag[n]) {
            return dp[n];
        }

        flag[n] = true;
        if (n == 0) {
            dp[n] = false;
            return dp[n];
        }
        if (n == 1) {
            dp[n] = true;
            return dp[n];
        }
        if (n == 2) {
            dp[n] = true;
            return dp[n];
        }
        if (n == 3) {
            dp[n] = false;
            return dp[n];
        }

        /* Left: pick one coin */
        boolean left_left = search(n - 1 - 1, dp, flag);
        boolean left_right = search(n - 1 - 2, dp, flag);
        boolean left = left_left && left_right;

        /* Right: pick two coins */
        boolean right_left = search(n - 2 - 1, dp, flag);
        boolean right_right = search(n - 2 - 2, dp, flag);
        boolean right = right_left && right_right;

        dp[n] = left || right;
        return dp[n];
    }
}


/* DP */
public class Solution {
    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        if (n == 0) {
            return false;
        }
        if (n <= 2) {
            return true;
        }

        boolean[] flag = new boolean[n + 1];
        // dp[i] 表示还剩i个硬币的时候，先手的最后能否获胜
        boolean[] dp = new boolean[n + 1];

        dp[0] = false;
        dp[1] = true;
        dp[2] = true;
        dp[3] = false;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[(i-1)-1] && dp[(i-1)-2]) || (dp[(i-2)-1] && dp[(i-2)-2]);
            //           |              |                |              |
            //           |              |                |              |__ (i-2) - 2: 先手取两个硬币，后手取两个硬币
            //           |              |                |__ (i-2) - 1: 先手取两个硬币，后手取一个硬币
            //           |              |__ (i-1) - 2: 先手取一个硬币，后手取两个硬币
            //           |__(i-1): 先手取一个硬币， (i-1) -1: 先手取一个硬币，后手取一个硬币
        }

        return dp[n];

    }
}
