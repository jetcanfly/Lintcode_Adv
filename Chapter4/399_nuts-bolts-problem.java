/**
 * public class NBCompare {
 *     public int cmp(String a, String b);
 * }
 * You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
 * if "a" is bigger than "b", it will return 1, else if they are equal,
 * it will return 0, else if "a" is smaller than "b", it will return -1.
 * When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
*/
public class Solution {
    /**
     * @param nuts: an array of integers
     * @param bolts: an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        if (nuts == null || nuts.length == 0) {
            return ;
        }
        if (bolts == null || bolts.length == 0) {
            return ;
        }
        if (nuts.length != bolts.length) {
            return ;
        }
        quickSort(nuts, bolts, compare, 0, nuts.length - 1);
    }

    // quickSort
    private void quickSort(String[] nuts, String[] bolts, NBComparator compare, int left, int right) {
        if (left >= right) {
            return ;
        }
        // find the partition index for nuts with bolts[left]
        int positionNut = partition(nuts, bolts[left], compare, left, right);
        // partition bolts with nuts[part_inx]
        int positionBolt = partition(bolts, nuts[positionNut], compare, left, right);
        // positionNut == positionBolt ? True

        // quickSort Recursively
        quickSort(nuts, bolts, compare, left, positionBolt - 1);
        quickSort(nuts, bolts, compare, positionBolt + 1, right);
    }

    // sort in descending order
    private int partition(String[] str, String pivot, NBComparator compare, int left, int right) {

        // find pivot
        for (int i = left; i <= right; i++) {
            String s = str[i];
            if (compare.cmp(pivot, s) == 0 || compare.cmp(s, pivot) == 0) {
                swap(str, left, i);
                break;
            }
        }
        String pivotStr = str[left];    // pivot is nut, pivotStr is the corresponding bolt

        while (left < right) {
            // compare.cmp(pivot, str[right]) == 1. bolts is smaller than nut
            // compare.cmp(str[right], pivot) == -1 nuts is smaller than bolt (pivot)
            while (left < right && (compare.cmp(pivot, str[right]) == 1 || compare.cmp(str[right], pivot) == -1)) {
                right--;
            }
            str[left] = str[right];
            while (left < right && (compare.cmp(pivot, str[left]) == -1 || compare.cmp(str[left], pivot) == 1)) {
                left++;
            }
            str[right] = str[left];
        }
        str[left] = pivotStr;
        return left;
    }

    private void swap(String[] str, int i, int j) {
        String tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;

    }
};
