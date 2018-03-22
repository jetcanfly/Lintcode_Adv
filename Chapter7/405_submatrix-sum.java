import java.util.*;

/* O(n^3) */
public class Solution {
    /**
     * @param matrix an integer matrix
     * @return the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return matrix;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] ans = new int[2][2];

        // Prefix sum on rows
        int[][] presum = new int[m + 1][n];
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                presum[i][j] += presum[i - 1][j] + matrix[i - 1][j];
            }
        }

        for (int lx = 0; lx < m; lx++) {
            for (int rx = lx; rx < m; rx++) {
                int[] cols = findSubArraySum(presum, lx, rx);
                if (cols[0] != -1) {
                    ans[0][0] = lx;
                    ans[0][1] = cols[0];
                    ans[1][0] = rx;
                    ans[1][1] = cols[1];
                    break;
                }
            }
        }

        return ans;
    }

    private int[] findSubArraySum(int[][] presum, int lx, int rx) {
        // Get the prefix sum between lx and rx
        int[] nums = new int[presum[0].length];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = presum[rx + 1][i] - presum[lx][i];
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);

        int[] ans = {-1, -1};
        // Prefix sum on columns
        int sumCol = 0;

        for (int i = 0; i < nums.length; i++) {
            sumCol += nums[i];
            if (map.containsKey(sumCol)) {
                ans[0] = map.get(sumCol) + 1;
                ans[1] = i;
                break;
            }
            map.put(sumCol, i);
        }

        return ans;
    }
}
