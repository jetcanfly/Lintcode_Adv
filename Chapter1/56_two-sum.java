public class Solution {
    /*
     * @param numbers : An array of Integer
     * @param target : target = numbers[index1] + numbers[index2]
     * @return : [index1 + 1, index2 + 1] (index1 < index2)
     */

    private class Element {
        int idx;
        int val;
        public Element(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public class ElementComparator implements Comparator<Element> {
        public int compare(Element A, Element B) {
            return A.val - B.val;
        }
    }

    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        if (numbers == null || numbers.length == 0) {
            return result;
        }

        int n = numbers.length;
        Element[] map = new Element[n];

        for (int i = 0; i < n; i++) {
            map[i] = new Element(numbers[i], i);
        }

        // Sort Array
        Arrays.sort(map, new ElementComparator());

        int i = 0;
        int j = n - 1;

        while (i < j) {
            int sum = map[i].val + map[j].val;
            if (sum == target) {
                int idx1 = map[i].idx + 1;
                int idx2 = map[j].idx + 1;

                result[0] = idx1 < idx2 ? idx1 : idx2;
                result[1] = idx1 > idx2 ? idx1 : idx2;
                break;
            }
            if (sum < target) {
                i++;
            } else {
                j--;
            }
        }

        return result;
    }
}
