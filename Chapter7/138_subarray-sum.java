public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return ans;
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        // Key: prefix sum
        // Value: index
        int prefix = 0;
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            if (map.containsKey(prefix)) {
                ans.add(map.get(prefix) + 1);
                ans.add(i);
                break;
            }
            map.put(prefix, i);
        }

        return ans;
    }
}
