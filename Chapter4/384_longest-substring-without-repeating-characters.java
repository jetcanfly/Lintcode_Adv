public class Solution {
    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        boolean[] visited = new boolean[256];

        int j = 0;
        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            while (j < s.length()) {
                char c = s.charAt(j);
                if (visited[c]) {
                    break;
                }
                maxLen = Math.max(maxLen, j - i + 1);
                visited[c] = true;
                j++;
            }
            visited[s.charAt(i)] = false;
        }

        return maxLen;
    }
}
