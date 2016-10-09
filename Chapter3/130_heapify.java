public class Solution {
    /**
     * @param A: Given an integer array
     * @return: void
     */
    public void heapify(int[] A) {
        if (A == null || A.length == 0) {
            return ;
        }

        /*
        for (int i = 0; i < A.length; i++) {
            siftup(A, i);
        }
        */

        for (int i = A.length / 2; i >= 0; i--) {
            siftdown(A, i);
        }
    }

    // O(n)
    private void siftdown(int[] A, int k) {
        while (k < A.length) {
            int smallestIdx = k;
            // left child
            if (k * 2 + 1 < A.length && A[k * 2 + 1] < A[smallestIdx]) {
                smallestIdx = k * 2 + 1;
            }
            // right child
            if (k * 2 + 2 < A.length && A[k * 2 + 2] < A[smallestIdx] ) {
                smallestIdx = k * 2 + 2;
            }
            if (k == smallestIdx) {
                break;
            }
            int tmp = A[smallestIdx];
            A[smallestIdx] = A[k];
            A[k] = tmp;
            k = smallestIdx;
        }
    }

    // O(log n)
    private void siftup(int[] A, int k) {
        while (k != 0) {
            int father = (k - 1)  / 2;
            if (A[father] < A[k]) {
                break;
            }
            int tmp = A[father];
            A[father] = A[k];
            A[k] = tmp;
            k = father;
        }
    }
}
