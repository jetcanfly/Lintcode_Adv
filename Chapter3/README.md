# Chapter 3 数据结构(下)

## Heap

**`Heap`: 求集合的最大值**

Heap (`PriorityQueue`)

- `O(logn)` push -> shift up
- `O(logn)` pop -> shift down
- `O(1)`    top
- `O(n)`    delete


HashHeap

`Hash` + `Heap`

- `O(logn)` delete


Heap 实现的原理

`push` 插入：将新元素放到heap[size+1]的位置每次比较它的它父亲元素，如果小于它的父亲，证明现在不满足堆的性质,然后向上Sift Up

```java
void siftup(int id) {
    while (parent(id) > -1) {
        int parentId = parent(id);
        if (comparesamll(heap.get(parentId), head.get(id)) == true) {
            break;
        } else {
            swap(id, parentId);
        }
        id = parentId;
    }
}
```

`pop` 删除：将根节点和最后一个节点进行交换如果该节点大于其中一个儿子，那么将其与其较小的儿子进行交换做Sift Down，直到该节点的儿子均大于它的值，或者它的儿子为空

```java
void siftdown(int id) {
    while (lson(id) < heap.size()) {
        int leftId = lson(id);
        int rightId = rson(id);
        int son;
        if (rightId >= heap.size() || (comparesmall(heap.get(rightId)) == true)) {
            son = leftId;
        } else {
            son = rightId;
        }
        if (comparesmall(heap.get(id), heap(get(son)) == true) {
            swap(id, son);
        }
        id = son;
    }
}
```

#### [Trapping Rain Water](http://www.lintcode.com/en/problem/trapping-rain-water/)

#### [Trapping Rain Water II](http://www.lintcode.com/en/problem/trapping-rain-water-ii/)
矩阵矩阵从外向内遍历技巧

#### [Building Outline](http://www.lintcode.com/en/problem/building-outline/)

#### [Heapify](http://www.lintcode.com/en/problem/heapify/)

#### [Data Stream Median](http://www.lintcode.com/en/problem/data-stream-median/)

#### [Sliding Window Median](http://www.lintcode.com/en/problem/sliding-window-median/)

- 中位数怎么想到用堆`Heap`

```
          median
          /     \
         /       \
 maxHeap           minHeap
(size: n)      (size: n, n+1)

```

- 窗口操作怎么分解
  1. 左边删除一个元素
  2. 右边增加一个元素
  
  

## Deque

**`Deque`: 两端都会有`push`和`pop`**

`Deque<String> deque = new ArrayDeque<String>();  `


**Summary of Deque methods**

|               | **First Element (Head)**   |                 | **Last Element (Tail)**   |               |
| ------------: | :------------------------: |:---------------:| :------------------------:| :-----------: |
|               | Throws exception           | Special value   | Throws exception          | Special value |
| **Insert**    | `addFirst(e)`              | `offerFirst(e)` | `addLast(e)`              | `offerLast(e)`|
| **Remove**    | `removeFirst()`            | `pollFirst()`   | `removeLast()`            | `pollLast()`  |
| **Examine**   | `getFirst()`               | `peekFirst()`   | `getLast()`               | `peekLast()`  |


#### [Sliding Window Maximum](http://www.lintcode.com/en/problem/sliding-window-maximum/)

- Method 1: for loop `O(nk)`
- Method 2: Balancing Binary Search Tree or Heap: `O(nlog(k))`
  1. get max
  2. delete element
  3. insert element
- Method 3: Deque `O(n)`
  1. pop and push at front
  2. pop at end

    
#### Sliding Window Matrix Maximum

Sliding Window Maximum + sub array Sum

