/* It is similar to Largest Sum Contiguous Subarray problem.
 * The only thing to note here is, maximum product can also be obtained by
 * minimum (negative) product ending with the previous element multiplied by
 * this element.
 *
 * For example, in array {12, 2, -3, -5, -6, -2}, when we are at element -2,
 * the maximum product is multiplication of, minimum product ending with -6 and -2.
 */

public class Solution {
    /**
     * @param nums: an array of integers
     * @return: an integer
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp:以第i个结尾最大的乘积
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];

        max[0] = nums[0];
        min[0] = nums[0];
        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {
            max[i] = nums[i];
            min[i] = nums[i];
            if (nums[i] > 0) {
                max[i] = Math.max(max[i], max[i - 1] * nums[i]);
                min[i] = Math.min(min[i], min[i - 1] * nums[i]);
            } else {
                max[i] = Math.max(max[i], min[i - 1] * nums[i]);    // -5 * -2 => 10
                min[i] = Math.min(min[i], max[i - 1] * nums[i]);
            }
            ans = Math.max(ans, max[i]);
        }

        return ans;
    }
}
