public class Solution {
    /**
     * @param nums a list of integer
     * @return an integer, maximum coins
     */
    public int maxCoins(int[] nums) {
        /* https://www.youtube.com/watch?v=IFNibRVgFBo */
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        // Extend the array to have ones on the begining and end of the array
        int[] numsExt = new int[n + 2];
        for (int i = 0; i < n; i++) {
            numsExt[i + 1] = nums[i];
        }
        numsExt[0] = 1;
        numsExt[n + 1] = 1;

        // DP Arrays
        int[][] dp = new int[n + 2][n + 2];
        boolean[][] flag = new boolean[n + 2][n + 2];

        return search(1, n, numsExt, dp, flag);
    }

    private int search(int l, int r, int[] nums, int[][] dp, boolean[][] flag) {
        if (flag[l][r]) {
            return dp[l][r];
        }

        flag[l][r] = true;
        dp[l][r] = 0;

        for (int k = l; k <= r; k++) {
            int leftValue = search(l, k - 1, nums, dp, flag);
            int rightValue = search(k + 1, r, nums, dp, flag);
            int midValue = nums[l - 1] * nums[k] * nums[r + 1];
            // DP function based on which one to burst last
            dp[l][r] = Math.max(dp[l][r], leftValue + midValue + rightValue);
        }

        return dp[l][r];
    }
}
