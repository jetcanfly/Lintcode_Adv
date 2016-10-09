public class Solution {
    /**
     * @param heights: an array of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int leftHeight = heights[0];
        int rightHeight = heights[heights.length - 1];
        int left = 0;
        int right = heights.length - 1;
        int sum = 0;

        while (left < right) {
            if (leftHeight < rightHeight) {
                left++;
                if (leftHeight < heights[left]) {
                    leftHeight = heights[left];
                } else {
                    sum += leftHeight - heights[left];
                }
            } else {
                right--;
                if (rightHeight < heights[right]) {
                rightHeight = heights[right];
                } else {
                    sum += rightHeight - heights[right];
                }
            }
        }

        return sum;
    }
}
