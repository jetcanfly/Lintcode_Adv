// O(n)
public class Solution {
    /**
     * @param nums a list of integer
     * @return void
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return ;
        }

        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1 && nums[i - 1] > nums[i]) {
                swap(nums, i - 1, i);
            }
            if (i % 2 == 0 && nums[i - 1] < nums[i]) {
                swap(nums, i - 1, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}


// O(nlog(n))
public class Solution {
    /**
     * @param nums a list of integer
     * @return void
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return ;
        }

        int len = nums.length;

        Arrays.sort(nums);

        reverse(nums, len/2, len - 1);
        wiggleSwap(nums, 1, len/2);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private void wiggleSwap(int[] nums, int i, int j) {
        while (i < nums.length && j < nums.length) {
            swap(nums, i, j);
            i += 2;
            j += 1;
        }
    }

    private void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }
}
