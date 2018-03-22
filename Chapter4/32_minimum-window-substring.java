public class Solution {
    /**
     * @param source: A string
     * @param target: A string
     * @return: A string denote the minimum window
     *          Return "" if there is no such a string
     */
    public String minWindow(String source, String target) {
        if (source == null || target == null) {
            return "";
        }
        if (source.length() == 0 || target.length() == 0) {
            return "";
        }

        int[] targetHash = new int[256]; // count numbers of character in target string
        int[] sourceHash = new int[256];

        for (int i = 0; i < target.length(); i++) {
            targetHash[target.charAt(i)]++;
        }

        int j = 0;
        int ans = Integer.MAX_VALUE;;
        String ansStr = "";
        for (int i = 0; i < source.length(); i++) {
            while (j < source.length() && !valid(sourceHash, targetHash)) {
                sourceHash[source.charAt(j)]++;
                j++;
            }
            if (valid(sourceHash, targetHash)) {
                if (ans > j - i) {
                    ans = j - i;
                    ansStr = source.substring(i, j);
                }
            }
            sourceHash[source.charAt(i)]--;
        }

        return ansStr;
    }

    private boolean valid(int[] source, int[] target) {
        for (int i = 0; i < 256; i++) {
            if (source[i] < target[i]) {
                return false;
            }
        }
        return true;
    }
}
