public class Solution {
    /**
     * @param nums an integer array and all positive numbers, no duplicates
     * @param target an integer
     * @return an integer
     */
    public int backPackIV(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (target <= 0) {
            return 0;
        }
        
        int[][] dp = new int[nums.length + 1][target + 1];
        
        dp[0][0] = 1;
        
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] += dp[i][j - nums[i - 1]];    
                }
            }
        }
        
        return dp[nums.length][target];
    }
}