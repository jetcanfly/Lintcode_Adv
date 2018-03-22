public class Solution {
    /**
     * @param nums a list of integer
     * @return void
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return ;
        }

        int median = findKthLargest(nums, 0, nums.length - 1, nums.length / 2 + 1);
        // Sort by median number. left <= median < right
        // sort(nums, median);

        // Wiggle Sort
        wiggle(nums, median);
    }

    // Find the Kth (index + 1) largest element in an array without sorting it. O(n)
    private int findKthLargest(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }

        int position = partition(nums, left, right);
        if (position + 1 == k) {
            return nums[position];
        }
        if (position + 1 < k) {
            return findKthLargest(nums, position + 1, right, k);
        } else {
            return findKthLargest(nums, left, position - 1, k);
        }
    }

    // Partiion by descending order
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && pivot >= nums[right]) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && pivot <= nums[left]) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

    private void wiggle(int[] nums, int mid) {
        int len = nums.length;
        int[] ans = new int[len];

        for (int i = 0; i < len; i++) {
            ans[i] = mid;
        }

        int l, r;
        if (len % 2 == 0) {
            l = len - 2;
            r = 1;
            for (int i = 0; i < len; i++) {
                if (nums[i] < mid) {
                    ans[l] = nums[i];
                    l -= 2;
                } else if (nums[i] > mid) {
                    ans[r] = nums[i];
                    r += 2;
                }
            }
        } else {
            l = 0;
            r = len - 2;
            for (int i = 0; i < len; i++) {
                if (nums[i] < mid) {
                    ans[l] = nums[i];
                    l += 2;
                } else if (nums[i] > mid) {
                    ans[r] = nums[i];
                    r -= 2;
                }
            }

        }

        // Copy back
        for (int i = 0; i < len; i++) {
            nums[i] = ans[i];
        }
    }


    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // Sort by pivot: left <= pivot < right
    // three-way partitioning
    // https://en.wikipedia.org/wiki/Dutch_national_flag_problem#Pseudocode
    private void sort(int[] nums, int pivot) {
        int left = 0;
        int right = nums.length - 1;
        int i = 0;

        while (i < right) {
            if (nums[i] < pivot) {
                swap(nums, i, left);
                i++;
                left++;
            } else if (nums[i] > pivot) {
                swap(nums, i, right);
                right--;
            } else {
                i++;
            }
        }
    }
}
