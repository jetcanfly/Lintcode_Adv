/*
 * kth largest element
 * Partition sort in descending order
 */
class Solution {
    //param k : description of k
    //param numbers : array of numbers
    //return: description of return
    public int kthLargestElement(int k, int[] numbers) {
        if (k <= 0) {
            return 0;
        }
        if (numbers == null || numbers.length == 0) {
            return 0;
        }

        // partition into increasing order.
        // kth largest == numbers.length+1 - k th smallest
        return helper(numbers, 0, numbers.length - 1, k);
    }

    private int helper(int[] numbers, int left, int right, int k) {
        if (left == right) {
            return numbers[left];
        }

        int position = partition(numbers, left, right);
        if (position + 1 == k) {    // kth = index + 1
            return numbers[position];
        } else if (position + 1 < k) {
            return helper(numbers, position + 1, right, k);
        } else {
            return helper(numbers, left, position - 1, k);
        }
    }

    /* sort in descending order */
    private int partition(int[] numbers, int left, int right) {
        int pivot = numbers[left];
        while (left < right) {
            while (left < right && numbers[right] <= pivot) {
                right--;
            }
            numbers[left] = numbers[right];
            while (left < right && numbers[left] >= pivot) {
                left++;
            }
            numbers[right] = numbers[left];
        }
        numbers[left] = pivot;
        return left;
    }
};


/*
 * kth largest element
 * Partition sort in ascending order
 * Convert find kth largest to find (numbers.length+1-k)th smallest
 */

class Solution {
    //param k : description of k
    //param numbers : array of numbers
    //return: description of return
    public int kthLargestElement(int k, int[] numbers) {
        if (k <= 0) {
            return 0;
        }
        if (numbers == null || numbers.length == 0) {
            return 0;
        }

        // partition into increasing order.
        // kth largest == numbers.length+1 - k th smallest
        return helper(numbers, 0, numbers.length - 1, numbers.length - k + 1);
    }

    private int helper(int[] numbers, int left, int right, int k) {
        if (left == right) {
            return numbers[left];
        }

        int position = partition(numbers, left, right);
        if (position + 1 == k) {    // kth = index + 1
            return numbers[position];
        } else if (position + 1 < k) {
            return helper(numbers, position + 1, right, k);
        } else {
            return helper(numbers, left, position - 1, k);
        }
    }

    /* sort in ascending order */
    private int partition(int[] numbers, int left, int right) {
        int pivot = numbers[left];
        while (left < right) {
            while (left < right && pivot <= numbers[right]) {
                right--;
            }
            numbers[left] = numbers[right];
            while (left < right && numbers[left] <= pivot) {
                left++;
            }
            numbers[right] = numbers[left];
        }
        numbers[left] = pivot;
        return left;
    }
};
