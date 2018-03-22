public class Solution {
    /**
     * @param A an integer matrix
     * @return  an integer
     */
    public int longestIncreasingContinuousSubsequenceII(int[][] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        if (A[0] == null || A[0].length == 0) {
            return 0;
        }

        int m = A.length;
        int n = A[0].length;

        boolean[][] flag = new boolean[m][n];
        int[][] dp = new int[m][n];     // 以i,j结尾最长的LCS

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = search(A, dp, flag, i, j);
                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans;
    }

    private int search(int[][] A, int[][] dp, boolean[][] flag, int i, int j) {
        if (flag[i][j]) {
            return dp[i][j];
        }

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int ans = 1;
        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];
            if (nx >= 0 && nx < A.length && ny >= 0 && ny < A[i].length) {
                if (A[i][j] > A[nx][ny]) {
                    ans = Math.max(ans, search(A, dp, flag, nx, ny) + 1);
                }
            }
        }

        dp[i][j] = ans;
        flag[i][j] = true;
        return dp[i][j];
    }
}
