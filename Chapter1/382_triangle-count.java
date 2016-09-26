public class Solution {
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int S[]) {
        // write your code here
        if (S == null || S.length == 0) {
            return 0;
        }

        Arrays.sort(S);
        int ans = 0;

        for (int k = 0; k < S.length; k++) {
            int left = 0;
            int right = k - 1;
            while (left < right) {
                if (S[left] + S[right] > S[k]) {
                    ans += right - left;
                    right--;
                } else  {
                    left++;
                }
            }
        }

        return ans;
    }
}
