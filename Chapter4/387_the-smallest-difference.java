// Sort O(n log n) + while O(n)
public class Solution {
    /**
     * @param A, B: Two integer arrays.
     * @return: Their smallest difference.
     */
    public int smallestDifference(int[] A, int[] B) {
        if (A == null || B == null) {
            return -1;
        }
        if (A.length == 0 && B.length == 0) {
            return 0;
        }
        if (A.length == 0 || B.length == 0) {
            return -1;
        }

        // Sort Array O(n log n)
        Arrays.sort(A);
        Arrays.sort(B);

        // [3,4,6,7] & [2,3,8,9]
        int i = 0;
        int j = 0;
        int ans = Integer.MAX_VALUE;

        while (i < A.length && j < B.length) {
            ans = Math.min(ans, Math.abs(A[i] - B[j]));
            if (A[i] < B[j]) {
                i++;
            } else {
                j++;
            }
        }

        return ans;
    }
}


// Sort O(n log n) + (for loop with Binary Search O(n * log n))
public class Solution {
    /**
     * @param A, B: Two integer arrays.
     * @return: Their smallest difference.
     */
    public int smallestDifference(int[] A, int[] B) {
        if (A == null || B == null) {
            return -1;
        }
        if (A.length == 0 && B.length == 0) {
            return 0;
        }
        if (A.length == 0 || B.length == 0) {
            return -1;
        }

        // Sort Array O(n log n)
        Arrays.sort(A);
        Arrays.sort(B);

        // [3,4,6,7] & [2,3,8,9]
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            int curr = A[i];
            int diff = getDiff(curr, B);
            ans = Math.min(ans, diff);
        }

        return ans;
    }

    private int getDiff(int num, int[] B) {
        // find the last # less than num
        int prev = getPrev(num, B);
        // find the first # greater than num
        int next = getNext(num, B);

        return Math.min(Math.abs(num - prev), Math.abs(num - next));
    }

    /* find the last # less than num */
    private int getPrev(int num, int[] B) {
        // B is sorted.

        int start = 0;
        int end = B.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (num <= B[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (B[end] <= num) {
            return B[end];
        }
        return B[start];
    }

    /* find the first # greater than num */
    private int getNext(int num, int[] B) {
        int start = 0;
        int end = B.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (B[mid] <= num) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (num <= B[start]) {
            return B[start];
        }
        return B[end];
    }

}


// O(n^2)
public class Solution {
    /**
     * @param A, B: Two integer arrays.
     * @return: Their smallest difference.
     */
    public int smallestDifference(int[] A, int[] B) {
        if (A == null || A.length == 0) {
            return -1;
        }
        if (B == null || B.length == 0) {
            return -1;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int diff = Math.abs(A[i] - B[j]);
                ans = Math.min(ans, diff);
            }
        }

        return ans;
    }
}
