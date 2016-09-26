public class Solution {
    /**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    public int twoSum2(int[] nums, int target) {
        // Write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;
        int ans = 0;

        while (left < right) {
            if (nums[left] + nums[right] > target) {
                ans += right - left;
                right--;
            } else {
                left++;
            }
        }

        return ans;
    }
}
