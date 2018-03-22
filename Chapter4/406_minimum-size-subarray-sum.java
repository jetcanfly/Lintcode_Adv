// O(n)
public class Solution {
    /**
     * @param nums: an array of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (s < 0) {
            return -1;
        }

        int sum = 0;
        int j = 0;
        int count = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length && sum < s) {
                sum += nums[j];
                j++;
            }
            if (sum >= s) { // now from [i, j], sum >= s (due to while)
                count = Math.min(count, j - i);
            }
            sum -= nums[i];
        }

        if (count == Integer.MAX_VALUE) {
            return -1;
        }

        return count;
    }
}

// O(n^2)
public class Solution {
    /**
     * @param nums: an array of integers
     * @param s: an integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (s < 0) {
            return -1;
        }

        int sum = 0;
        int count = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum = nums[i];
            /* Remeber to check this */
            // Input: [1,1,2,3,1,5], 5
            // Output: 1
            if (sum >= s) {
                count = 1;
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    count = Math.min(count, j - i + 1);
                    break;
                }
            }
        }

        if (count == Integer.MAX_VALUE) {
            return -1;
        }

        return count;
    }
}
