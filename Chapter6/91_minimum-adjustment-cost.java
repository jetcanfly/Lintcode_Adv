public class Solution {
    /**
     * @param A: An integer array.
     * @param target: An integer.
     */
    public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        if (A == null || A.size() == 0) {
            return 0;
        }
        if (target < 0) {
            return -1;
        }

        // dp[i][j] defines minimal adjustment cost on changing A[i] to j
        int[][] dp = new int[A.size() + 1][101];

        dp[0][0] = 0;
        for (int i = 1; i <= A.size(); i++) {
            dp[i][0] = A.get(i - 1);
        }

        for (int i = 1; i <= A.size(); i++) {
            for (int v = 0; v <= 100; v++) {
                dp[i][v] = Integer.MAX_VALUE;
                // Find the previous minimum adjustment cost that satisfies constraint
                for (int v_ = 0; v_ <= 100; v_++) {
                    if (Math.abs(v - v_) <= target) {
                        dp[i][v] = Math.min(dp[i][v], dp[i - 1][v_]);
                    }
                }
                // Previos minimum adjustment cost + current adjustment cost
                dp[i][v] += Math.abs(A.get(i - 1) - v);
            }
        }

        int best = Integer.MAX_VALUE;
        for (int v = 0; v <= 100; v++) {
            best = Math.min(best, dp[A.size()][v]);
        }

        return best;
    }
}
