public class Solution {
    /**
     * @param s : A string
     * @return : The length of the longest substring
     *           that contains at most k distinct characters.
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (k <= 0) {
            return 0;
        }

        int[] count = new int[256];     // assuming ASCII code
        int j = 0;
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < s.length(); i++) {
            while (j < s.length()) {
                count[s.charAt(j)]++;
                j++;

                if (getNum(count) <= k) {
                    ans = Math.max(ans, j - i);
                } else {
                    break;
                }
            }
            count[s.charAt(i)]--;
        }

        return ans;
    }

    private int getNum(int[] count) {
        int ans = 0;
        for (int i = 0; i < 256; i++) {
            if (count[i] > 0) {
                ans++;
            }
        }
        return ans;
    }
}
