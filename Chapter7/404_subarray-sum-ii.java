/* O(nlog(n))*/
public class Solution {
    /**
     * @param A an integer array
     * @param start an integer
     * @param end an integer
     * @return the number of possible answer
     */
    public int subarraySumII(int[] A, int start, int end) {
        if (A == null || A.length == 0) {
            return 0;
        }
        if (start > end) {
            return 0;
        }

        int N = A.length;

        // Prefix Sum
        int[] prefix = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i - 1] + A[i - 1];
        }
        Arrays.sort(prefix);

        int count = 0;
        for (int j = 1; j <= N; j++) {
            if (start <= prefix[j] && prefix[j] <= end) {
                count++;
            }

            // Originally: start <= prefix[j+1] - prefix[i] <= end, for i = 0-> n, j = i -> n.
            // Now loop j first, then i, we have
            //      prefix[j+1] - end <= prefix[i] <= prefix[j+1] - start, for j = 0-> n, i = 0 -> j

            int l = prefix[j] - end;
            int r = prefix[j] - start;

            /* count = 第一个比r大的index - 第一个比l小的index + 1
               --> 第一个比r+1小的index - 第一个比l小的index */
            count += find(prefix, r + 1) - find(prefix, l);
        }

        return count;
    }

    /* find第一个比target小的index */
    private int find(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target <= nums[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] < target) {
            return end;
        }
        return start;
    }
}

/* O(n^2) */
public class Solution {
    /**
     * @param A an integer array
     * @param start an integer
     * @param end an integer
     * @return the number of possible answer
     */
    public int subarraySumII(int[] A, int start, int end) {
        if (A == null || A.length == 0) {
            return 0;
        }
        if (start > end) {
            return 0;
        }

        int N = A.length;

        // Prefix Sum
        int[] prefix = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i - 1] + A[i - 1];
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int diff = prefix[j + 1] - prefix[i];
                if (start <= diff && diff <= end) {
                    count++;
                }
            }
        }
        return count;
    }
}
