class Solution {
    /**
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        List<Integer> ans = new ArrayList<Integer>();
        if (A == null || A.length == 0) {
            return ans;
        }
        if (A[0] == null || A[0].length == 0) {
            return ans;
        }


        // Binary search on row O(n * logn)
        // Find the maximum number in a row
        // Compare the maximum number with its up and down values
        // Split into half size problem


        int start = 0;
        int end = A.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // Find the maximum in current row
            int maxIdx = getMaxCol(A[mid]);
            if (A[mid - 1][maxIdx] > A[mid][maxIdx]) {
                end = mid;
            } else if (A[mid][maxIdx] < A[mid + 1][maxIdx]) {
                start = mid;
            } else {
                ans.add(mid);
                ans.add(maxIdx);
                break;
            }
        }
        return ans;

    }

    private int getMaxCol(int[] nums) {
        int maxIdx = -1;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (maxVal <= nums[i]) {
                maxIdx = i;
                maxVal = nums[i];
            }
        }
        return maxIdx;
    }
}
