public class Solution {
    /**
     * @param nums: A list of integers.
     * @return: The maximum number inside the window at each moving.
     */
    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return ans;
        }

        if (k < 0 || k > nums.length) {
            return ans;
        }

        Deque<Integer> q = new LinkedList<Integer>();

        for (int i = 0; i < k - 1; i++) {
            inQueue(q, nums[i]);
        }

        for (int i = k - 1; i < nums.length; i++) {
            inQueue(q, nums[i]);
            ans.add(q.peekFirst());
            outQueue(q, nums[i - (k - 1)]);
        }

        return ans;
    }

    private void inQueue(Deque<Integer> q, int num) {
        while (!q.isEmpty() && q.peekLast() < num) {
            q.pollLast();
        }
        q.offerLast(num);
    }

    private void outQueue(Deque<Integer> q, int num) {
        if (q.peekFirst() == num) {
            q.pollFirst();
        }
    }
}
