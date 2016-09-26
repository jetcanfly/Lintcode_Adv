# Chapter 1 - Follow up in Coding Interview
## Lecture
- PPT
[Chapter1/1._SeniorAlgorithm_Crack_IT_company.pptx2.pdf](Chapter1/1._SeniorAlgorithm_Crack_IT_company.pptx2.pdf)
- Video https://www.dropbox.com/s/rk573bus2rrxojw/Chapter1.mov?dl=0


### Two Sum
Two pointers

**Key**
- 排序
- 内层循环优化

练习题目 | Links | Video  | Note
:----------|:-----: | :---: | :---:
(56)Two Sum | [*LintCode*](http://www.lintcode.com/en/problem/two-sum/) - [*Solution*](http://www.jiuzhang.com/solutions/two-sum/) - [*File*](56_two-sum.java) |  |
(443)Two Sum II | [*LintCode*](http://www.lintcode.com/en/problem/two-sum-ii/) - [*Solution*](http://www.jiuzhang.com/solutions/two-sum-ii/) - [*File*](443_two-sum-ii.java) |  | two pointers
(382)Triangle Count | [*LintCode*](http://www.lintcode.com/en/problem/triangle-count/) - [*Solution*](http://www.jiuzhang.com/solutions/triangle-count/) - [*File*](382_triangle-count.java) |  | reduce to Two Sum II

### Kth Smallest Number in Sorted Matrix
见到集合求Min/Max就要想到堆

练习题目 | Links | Video  | Note
:----------|:-----: | :---: | :---:
(401)Kth Smallest Number in Sorted Matrix | [*LintCode*](http://www.lintcode.com/en/problem/kth-smallest-number-in-sorted-matrix/) - [*Solution*](http://www.jiuzhang.com/solutions/kth-smallest-number-in-sorted-matrix/) - [*File*](401_kth-smallest-number-in-sorted-matrix.java) |  | heap/priority queue
(543) Kth Largest in N Arrays | [*LintCode*](http://www.lintcode.com/en/problem/kth-largest-in-n-arrays/) - [*Solution*](http://www.jiuzhang.com/solutions/kth-largest-in-n-arrays/) - [*File*](543_kth-largest-in-n-arrays.java) |  | 拿到数组先排序
(465) Kth Smallest Sum In Two Sorted Arrays | [*LintCode*](http://www.lintcode.com/en/problem/kth-smallest-sum-in-two-sorted-arrays/) - [*Solution*](http://www.jiuzhang.com/solutions/kth-smallest-sum-in-two-sorted-arrays/) - [*File*](465_kth-smallest-sum-in-two-sorted-arrays.java) |  | use class `Node implement Comparable<Node>`. Override `public int compareTo(Node another)`, `public boolean equals(Object obj)`, `public int hashCode()`
