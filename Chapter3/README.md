# Chapter 3 - Data Structure II
## Lecture
- PPT
[3._data_structure_2_20160602.pptx.pdf](3._data_structure_2_20160602.pptx.pdf)
- Video https://www.dropbox.com/s/28elxstf0qixkbr/Chaper3_Data_Structure_II.mov?dl=0

### Heap
- O(logN) Push -> Sift Up
- O(logN) Pop -> Sift Down
- O(1) Top
- O(N) Delete / O(logN) for HashHeap

练习题目 | Links | Video  | Note
:----------|:-----: | :---: | :---:
(363)Trapping Rain Water | [*LintCode*](http://www.lintcode.com/en/problem/trapping-rain-water/) - [*Solution*](http://www.jiuzhang.com/solutions/trapping-rain-water/) - [*File*](363_trapping-rain-water.java) |  |
(364)Trapping Rain Water II | [*LintCode*](http://www.lintcode.com/en/problem/trapping-rain-water-ii/) - [*Solution*](http://www.jiuzhang.com/solutions/trapping-rain-water-ii/) - [*File*](364_trapping-rain-water-ii.java) |  |
(131)Building Outline | [*LintCode*](http://www.lintcode.com/en/problem/building-outline/) - [*Solution*](http://www.jiuzhang.com/solutions/building-outline/) - [*File*](131_building-outline.java) |  | HashHeap支持O(1)删除. 用PQ代替O(n)
(130)Heapify | [*LintCode*](http://www.lintcode.com/en/problem/heapify/) - [*Solution*](http://www.jiuzhang.com/solutions/heapify/) - [*File*](130_heapify.java) |  | Heap用数组实现。 Parent idx `id`, then Children: `id*2 + 1` and `id*2 + 2`. Children idx `id`, then Parent `(id - 1) / 2`
(81)Data Stream Median | [*LintCode*](http://www.lintcode.com/en/problem/data-stream-median/) - [*Solution*](http://www.jiuzhang.com/solutions/data-stream-median/) - [*File*](81_data-stream-median.java) |  |
(360)Sliding Window Median | [*LintCode*](http://www.lintcode.com/en/problem/sliding-window-median/) - [*Solution*](http://www.jiuzhang.com/solutions/sliding-window-median/) - [*File*](360_sliding-window-median.java) |  |


### Deque
  | First Element (Head)     |                 | Last Element (Tail) |             |
--------|--------------------|-----------------|--------------------|--------------
        | *Throws exception* | *Special value* | *Throws exception* | *Special value*
Insert  | `addFirst(e)`      | `offerFirst(e)` | `addLast(e)`       | `offerLast(e)`
Remove  | `removeFirst()`    | `pollFirst()`   | `removeLast()`     | `pollLast()`
Examine | `getFirst()`       | `peekFirst()`   | `getLast()`        | `peekLast()`

练习题目 | Links | Video  | Note
:----------|:-----: | :---: | :---:
(391)Sliding Window Maximum | [*LintCode*](http://www.lintcode.com/en/problem/sliding-window-maximum/) - [*Solution*](http://www.jiuzhang.com/solutions/sliding-window-maximum/) - [*File*](362_sliding-window-maximum.java) |  |
(558)Sliding Window Matrix Maximum | [*LintCode*](http://www.lintcode.com/en/problem/sliding-window-matrix-maximum/) - [*Solution*](http://www.jiuzhang.com/solutions/sliding-window-matrix-maximum/) - [*File*](558_sliding-window-matrix-maximum.java) |  |
