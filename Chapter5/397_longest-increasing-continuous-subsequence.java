public class Solution {
    /**
     * @param A an array of Integer
     * @return  an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int ans = 1;

        // From left to right
        int length = 1;         // Just A[0] itself
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] < A[i]) {
                length++;
            } else {
                length = 1;
            }
            ans = Math.max(ans, length);
        }

        // From right to left
        length = 1;
        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i + 1] < A[i]) {
                length++;
            } else {
                length = 1;
            }
            ans = Math.max(ans, length);
        }

        return ans;
    }
}
