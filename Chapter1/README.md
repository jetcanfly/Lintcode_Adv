# Chapter 1 透析热门互联网公司中的FollowUp面试题

## Two Sum 模板

```java
// 给一个数组A
int left = 0;
int right = nums.length - 1;

while (left < right) {
    if (A[left] 和 A[right] 满足某一个条件) {
        // 做一些事情
        right--;    // 不用考虑[left+1, right-1] 和 right组成的pair
    } else if (A[left] 和 A[right] 不满足某一个条件) {
        // 做一些事情
        left++;     // 不用考虑left 和 [left+1, right-1]组成的pair
    }
}
```

## Tips

- 见到需要维护一个集合的最小值/最大值的时候要想到用堆(`Heap`)
- 第k小的元素，`Heap`用来维护当前**候选集合**
- 见到数组要想到先排序