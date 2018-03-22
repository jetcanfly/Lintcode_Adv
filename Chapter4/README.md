# Chapter 4 Two Pointers

### 一个数组，从两边往中间移动(对撞型)

通过对撞型指针优化算法，根本上其实要证明就是不用扫描多余状态

```java
if (考虑A[i]和A[j]满足某种条件) {
    // do something
    j--;    // 不用考虑[i+1, j-1]和j组成的pair
} else if (考虑A[i]和A[j]不满足某种条件 {
    // do something
    i++;    // 不用考虑i和[i+1, j-1]组成的pair
} else {
    // do something
    i++ or j++;
}
```

#### Two Sum类题型
##### [Two Sum II](http://lintcode.com/en/problem/two-sum-greater-than-target/)
##### [Triangle Count](http://lintcode.com/en/problem/triangle-count/)

#### 灌水类题型

##### [Trapping Rain Water](http://lintcode.com/en/problem/trapping-rain-water/)

#### Partition类

Partition模板
```java
/* Partition array. Everything <= pivot go before pivot. Everything >= pivot go after pivot */
int partition(int[] nums, int left, int right) {
    int pivot = nums[left];

    while (left < right) {
        while (left < right && pivot <= nums[right]) {
            right--;
        }
        nums[left] = nums[right];

        while (left < right && nums[left] <= pivot) {
            left++;
        }
        nums[right] = nums[left];
    }

    nums[left] = pivot;
    return left;
}
```

#### [Kth Largest Element / Quick select](http://lintcode.com/en/problem/kth-largest-element/)

PriorityQueue

- 时间复杂度`O(nlogk)`
- 更适合Topk

QuickSelect
- 时间复杂度`O(n)`
- 更适合第k大

#### [Nuts & Bolts Problem](http://lintcode.com/en/problem/nuts-bolts-problem/)


**Two sum类**


* 3 Sum Closest
* 4 Sum
* 3 Sum
* k sum
* Two sum II
* Triangle Count
* Trapping Rain Water
* Container With Most Water

**Partition类**

* Partition-array
* Sort Colors
* Partition Array by Odd and Even
* Sort Letters by Case
* Valid Palindrome
* quick sort/ quick select/ nuts bolts problem/wiggle sort II

### 一个数组，同时向前移动(前向型)

优化：

- 优化思想通过两层for循环而来
- 外层指针依然是依次遍历
- 内层指针证明是否需要回退


**窗口类指针移动模板 `O(2n)`**
```java
// 通过两层for循环改进算法
// 不同于sliding window
int j = 0;
for (int i = 0; i < n; i++) {
    while (j < n) {
        if (满足某种条件) {
            j++;
            更新j状态
        } else {    // 不满足条件
            break;
        }
    }
    更新i状态
}
```

#### 题目

**窗口类**

- [Minimum Size Subarray Sum](http://lintcode.com/en/problem/minimum-size-subarray-sum/)
- [Longest Substring Without Repeating Characters](http://lintcode.com/en/problem/longest-substring-without-repeating-characters/)
- [Minimum Window Substring](http://lintcode.com/en/problem/minimum-window-substring/)
- [Longest Substring with At Most K (two) Distinct Characters](http://www.lintcode.com/en/problem/longest-substring-with-at-most-k-distinct-characters/) 

**快慢类**

- Find the Middle of Linked List
- Linked List Cycle I, II



### 两个数组（并行型）

两个数组各找一个元素, 使得和等于target

1. 找一种
2. 找全部种类


- [The Smallest Difference](http://lintcode.com/en/problem/the-smallest-difference/)
- [Merge Two Sorted Lists](http://www.lintcode.com/en/problem/merge-two-sorted-lists/)