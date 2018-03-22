/* O（状态的个数 * 每个状态的转移复杂度）
   首先我们看状态的个数，visit[start1][start2][k],显然这是一个N^3个数的状态，每个状态搜索的时候，里面是一个for循环，该for循环就是状态转移，所以状态转移的复杂度是N.
 */
public class Solution {
    /**
     * @param s1 A string
     * @param s2 Another string
     * @return whether s2 is a scrambled string of s1
     */
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() == 0 && s2.length() == 0) {
            return true;
        }
        if (s1.length() != s2.length()) {
            return false;
        }

        int n = s1.length();

        boolean[][][] dp = new boolean[n][n][n+1];
        boolean[][][] flag = new boolean[n][n][n+1];

        return search(s1, s2, 0, 0, n, dp, flag);
    }

    private boolean search(String s1, String s2, int x, int y, int k, boolean[][][] dp, boolean[][][] flag) {
        if (flag[x][y][k]) {
            return dp[x][y][k];
        }

        flag[x][y][k] = true;
        if (s1.equals(s2)) {
            dp[x][y][k] = true;
            return dp[x][y][k];
        }
        if (!isValid(s1, s2)) {
            dp[x][y][k] = false;
            return dp[x][y][k];
        }

        dp[x][y][k] = false;
        int len = s1.length();
        for (int i = 1; i < len; i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i, len);

            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, len);
            String s23 = s2.substring(0, len - i);
            String s24 = s2.substring(len - i, len);

            boolean s11_s21 = search(s11, s21, x, y, i, dp, flag);
            boolean s12_s22 = search(s12, s22, x+i, y+i, k-i, dp, flag);

            if (s11_s21 && s12_s22) {
                dp[x][y][k] = true;
                return dp[x][y][k];
            }

            boolean s11_s24 = search(s11, s24, x, y+k-i , i, dp, flag);
            boolean s12_s23 = search(s12, s23, x+i, y, k-i, dp, flag);

            if (s11_s24 && s12_s23) {
                dp[x][y][k] = true;
                return dp[x][y][k];
            }
        }
        return dp[x][y][k];
    }

    private boolean isValid(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        s1 = new String(arr1);
        s2 = new String(arr2);

        return s1.equals(s2);
    }
}
