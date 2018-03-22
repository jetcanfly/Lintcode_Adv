/* O(n) */
public class Solution {
    /**
     * @param A an integer array
     * @return  A list of integers includes the index of the first number and the index of the last number
     */
    public ArrayList<Integer> continuousSubarraySum(int[] A) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (A == null || A.length == 0) {
            return ans;
        }
        ans.add(0);
        ans.add(0);

        int len = A.length;
        int start = 0;
        int end = 0;
        int sum = 0;
        int best = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            if (sum < 0) {
                sum = A[i];
                start = i;
                end = i;
            } else {
                sum += A[i];
                end = i;
            }
            if (sum >= best) {
                best = sum;
                ans.set(0, start);
                ans.set(1, end);
            }
        }

        return ans;
    }
}

/* O(n^2) */
public class Solution {
    /**
     * @param A an integer array
     * @return  A list of integers includes the index of the first number and the index of the last number
     */
    public ArrayList<Integer> continuousSubarraySum(int[] A) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (A == null || A.length == 0) {
            return ans;
        }

        int[] prefix = new int[A.length + 1];
        for (int i = 1; i <= A.length; i++) {
            prefix[i] = prefix[i - 1] + A[i - 1];
        }

        int best = Integer.MIN_VALUE;
        int start = -1;
        int end = -1;
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                int diff = prefix[j + 1] - prefix[i];
                if (best < diff) {
                    start = i;
                    end = j;
                    best = diff;
                }
            }
        }

        ans.add(start);
        ans.add(end);
        return ans;

    }
}
