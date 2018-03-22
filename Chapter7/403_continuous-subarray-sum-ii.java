/* 循环数组：取反
 * 找子数组和最大可以拆分为： a. 非循环数组最大 b. 数组sum - 非循环数组最小.
 * Answer = max(a, b);
 */

// O(n)
public class Solution {
    /**
     * @param A an integer array
     * @return  A list of integers includes the index of the first number and the index of the last number
     */
    public ArrayList<Integer> continuousSubarraySumII(int[] A) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (A == null || A.length == 0) {
            return ans;
        }

        /* 循环数组：取反
         * 找子数组和最大可以拆分为： a. 非循环数组最大 b. 数组sum - 非循环数组最小.
         * Answer = max(a, b);
         */

        int len = A.length;

        // a. 非循环数组最大
        int sum = 0;
        int total = 0;
        int start = 0;
        int end = 0;
        int best = Integer.MIN_VALUE;
        ans.add(0);
        ans.add(0);

        for (int i = 0; i < len; i++) {
            total += A[i];
            if (sum < 0) {
                sum = A[i];
                start = end = i;
            } else {
                sum += A[i];
                end = i;
            }
            if (sum >= best) {
                best = sum;
                ans.set(0, start % len);
                ans.set(1, end % len);
            }
        }

        // b. 数组sum - 非循环数组最小
        start = 0;
        end = 0;
        sum = 0;

        for (int i = 0; i < len; i++) {
            if (sum > 0) {
                sum = A[i];
                start = end = i;
            } else {
                sum += A[i];
                end = i;
            }
            // [-101,-33,-44,-55,-67,-78,-101,-33,-44,-55,-67,-78,-100,-200,-1000,-22,-100,-200,-1000,-22]
            // Case for all negative values
            // start = 0, end = len - 1, total - sum = 0.
            if (start == 0 && end == len - 1) {
                continue;
            }
            if ((total - sum) > best) {
                best = total - sum;
                ans.set(0, (end + 1) % len);
                ans.set(1, (start - 1 + len) % len);
            }
        }

        return ans;
    }
}
