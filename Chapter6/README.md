# Chapter 6 动态规划(下)

### 区间类DP 

**区间类DP特点**
- 求一段区间的解max/min/count
- 转移方程通过区间更新 
- 从大到小的更新


**区间类DP总结**
- 这种题目共性就是区间最后求[0,n-1] 这样一个区间
- 逆向思维分析 从大到小就能迎刃而解
- 逆向 -> 分治类似

#### [Stone Game](http://www.lintcode.com/en/problem/stone-game/)
#### [Burst Balloons](http://lintcode.com/en/problem/burst-balloons/)
#### [Scramble String](http://www.lintcode.com/en/problem/scramble-string/)


### 背包类DP

- 用值作为DP维度
- DP过程就是填写矩阵
- 可以滚动数组优化


#### [Back Pack](http://lintcode.com/en/problem/backpack/)
#### [Back Pack II](http://lintcode.com/en/problem/backpack-ii/)
#### [K Sum](http://www.lintcode.com/en/problem/k-sum/)
#### [Minimum Adjustment Cost](http://www.lintcode.com/en/problem/minimum-adjustment-cost/) 