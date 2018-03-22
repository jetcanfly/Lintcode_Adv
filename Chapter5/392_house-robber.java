/* 滚动数组优化 */
public class Solution {
    /**
     * @param A: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        // state dp表示前i个房子，最多能抢到多少价值
        long[] dp = new long[2];

        // initialization
        dp[0] = 0;
        dp[1] = A[0];

        // functional
        for (int i = 2; i < A.length + 1; i++) {
            dp[i%2] = Math.max(dp[(i - 1)%2], dp[(i - 2)%2] + A[i - 1]);
        }

        // answer
        return dp[A.length%2];
    }
}

/* O(n) memory */
public class Solution {
    /**
     * @param A: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        // state dp表示前i个房子，最多能抢到多少价值
        long[] dp = new long[A.length + 1];

        // initialization
        dp[0] = 0;
        dp[1] = A[0];

        // functional
        for (int i = 2; i < A.length + 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + A[i - 1]);
        }

        // answer
        return dp[A.length];
    }
}
