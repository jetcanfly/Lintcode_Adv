public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: The median of the element inside the window at each moving.
     */
    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        if (k < 0 || k > nums.length) {
            return ans;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>();

        // Get the first median in the first sliding window
        int median = nums[0];
        for (int i = 1; i < k; i++) {
            median = addNumber(nums, i, median, minHeap, maxHeap);
        }
        ans.add(median);

        for (int i = k; i < nums.length; i++) {
            median = addNumber(nums, i, median, minHeap, maxHeap);
            median = removeNumber(nums, i - k, median, minHeap, maxHeap);
            ans.add(median);
        }

        return ans;
    }

    private int addNumber(int[] nums,
                             int i,
                             int median,
                             PriorityQueue<Integer> minHeap,
                             PriorityQueue<Integer> maxHeap) {

            if (nums[i] < median) {
                maxHeap.offer(-nums[i]);
            } else {
                minHeap.offer(nums[i]);
            }

            median = balance(minHeap, maxHeap, median);
            return median;
    }

    private int removeNumber(int[] nums,
                             int i,
                             int median,
                             PriorityQueue<Integer> minHeap,
                             PriorityQueue<Integer> maxHeap) {
            if (nums[i] < median) {
                maxHeap.remove(-nums[i]);
            } else if (nums[i] > median) {
                minHeap.remove(nums[i]);
            } else if (nums[i] == median) {
                median = minHeap.poll();
            }

            median = balance(minHeap, maxHeap, median);
            return median;
    }

    private int balance(PriorityQueue<Integer> minHeap,
                        PriorityQueue<Integer> maxHeap, int median) {
        while (maxHeap.size() + 1 < minHeap.size()) {
            maxHeap.offer(-median);
            median = minHeap.poll();
        }
        while (maxHeap.size() > minHeap.size()) {
            minHeap.offer(median);
            median = -maxHeap.poll();
        }
        return median;
    }
}
