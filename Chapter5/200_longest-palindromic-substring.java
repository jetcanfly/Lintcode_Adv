/* O(n^2) */
public class Solution {
    /**
     * @param s input string
     * @return the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        int maxLen = 1;
        int longestBegin = 0;

        // Define dp[ i, j ]
        // true iff the substring [Si … Sj] is a palindrome, otherwise false.
        boolean[][] dp = new boolean[s.length()][s.length()];


        // dp[i][i] = true
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // dp[i][i+1] = S[i] == S[j]
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                longestBegin = i;
                maxLen = 2;
            }

        }

        // for len > 3
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    longestBegin = i;
                    maxLen = len;
                }
            }
        }

        return s.substring(longestBegin, longestBegin + maxLen);
    }
}


/* O(n^3) */
public class Solution {
    /**
     * @param s input string
     * @return the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int ans = Integer.MIN_VALUE;
        String ansStr = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isValid(s, i, j)) {
                    if (ans < j - i) {
                        ans = j - i;
                        ansStr = s.substring(i, j + 1);
                    }
                }
            }
        }

        return ansStr;
    }

    private boolean isValid(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
