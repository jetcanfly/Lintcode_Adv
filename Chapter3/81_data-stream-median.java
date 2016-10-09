public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        int[] ans = new int[nums.length];
        if (nums == null || nums.length == 0) {
            return ans;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>();
        ans[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int mid = ans[i - 1];
            if (nums[i] < mid) {
                maxHeap.offer(-nums[i]);
            } else {
                minHeap.offer(nums[i]);
            }
            // Adjust Heap size
            if (maxHeap.size() + 1 < minHeap.size()) {
                maxHeap.offer(-mid);
                mid = minHeap.poll();
            } else if (maxHeap.size() > minHeap.size()) {
                minHeap.offer(mid);
                mid = -maxHeap.poll();
            }
            ans[i] = mid;
        }

        return ans;
    }
}
