/* 拆环: [3,6,4] --> [3,6] && [6,4] */
public class Solution {
    /**
     * @param nums: An array of non-negative integers.
     * return: The maximum amount of money you can rob tonight
     */
    public int houseRobber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        // 拆环: [3,6,4] --> [3,6] && [6,4]
        int[] arr = new int[nums.length - 1];

        // case: [3,6]
        for (int i = 0; i < nums.length - 1; i++) {
            arr[i] = nums[i];
        }
        int maxValue1 = getMaxValue(arr);


        // case: [6,4]
        for (int i = 1; i < nums.length; i++) {
            arr[i - 1] = nums[i];
        }
        int maxValue2 = getMaxValue(arr);

        // Return the maximum of two cases
        return Math.max(maxValue1, maxValue2);
    }

    private int getMaxValue(int[] arr) {
        // state dp[i]表示前i个房子，最多可以抢的多少价值
        int[] dp = new int[arr.length + 1];

        dp[0] = 0;
        dp[1] = arr[0];

        for (int i = 2; i < arr.length + 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i - 1]);
        }

        return dp[arr.length];
    }
}
