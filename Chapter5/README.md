# Chapter 5 动态规划(上)

**动态规划的4点要素**

1. 状态 State
 - 灵感，创造力，存储小规模问题的结果
 - 最优解/Maximum/Minimum
 - Yes/No
 - Count(*)
2. 方程 Function
 - 状态之间的联系，怎么通过小的状态，来求得大的状态
3. 初始化 Initialization
 - 最极限的小状态是什么, 起点
4. 答案 Answer
 - 最大的那个状态是什么，终点
 
**滚动数组优化**
先写程序，后加优化。

```java
int[] dp = new int[n];
// Function
dp[i] = Math.max(dp[i - 1], dp[i - 2] + A[i - 1]);
```

`dp[i]` only depends on `dp[i-1]` and `dp[i-2]`. We can use an array with length `2` to represent the dp array. Now, `mod 2` for every `dp[i]`.

```java
int[] dp = new int[n];
dp[i % 2] = Math.max(dp[(i - 1) % 2], dp[(i - 2) % 2] + A[i - 1];
```

二维动态规划空间优化
这类题目特点`f[i][j]` 由`f[i-1]`行来决定状态，第`i`行跟`i-1`行之前毫无关系，所以状态转变为`f[i%2][j]` 由`f[(i-1)%2]`行来决定状态


#### [House Robber](http://lintcode.com/en/problem/house-robber/)

#### [House Robber II](http://lintcode.com/en/problem/house-robber-ii/)
拆环：`[3, 6, 4]` 拆成 `[3, 6]`和`[6, 4]`。 分别解两次`[3, 6]`和`[6, 4]`取两个答案中的最大

#### [House Robber III](http://lintcode.com/en/problem/house-robber-iii/)

#### [Maximal Square](http://lintcode.com/en/problem/maximal-square/)


网格类的题目
- 正方形用右下角作为定位角
- 长方形可以用左上角和右下角作为定位角

#### [Maximal Rectangle](http://lintcode.com/en/problem/maximal-rectangle/)


#### [Unique Paths](http://www.lintcode.com/en/problem/unique-paths/)
#### [Minimum Path Sum](http://www.lintcode.com/en/problem/minimum-path-sum/)
#### [Edit Distance](http://www.lintcode.com/en/problem/edit-distance/)



### 记忆化搜索

- 记忆化搜索本质上就是动态规划
- 动态规划就是解决了重复计算的搜索

**动态规划的实现方式**
 - 循环（从小到大递推）
 - 记忆化搜索(从大到小搜索)
   * 画搜索树
   * 万金油
   
**什么时候用记忆化搜索？**

- 状态转移特别麻烦，不是顺序性。
- 初始化状态不是很容易找到。
   
         
#### [Longest Increasing Subsequence](http://lintcode.com/en/problem/longest-increasing-subsequence/)

#### [Longest Increasing continuous Subsequence 2D](http://www.lintcode.com/en/problem/longest-increasing-continuous -subsequence-ii/) / [Longest Increasing Path in a Matrix](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/)


### 博弈类DP

#### [Coins in a Line](http://lintcode.com/en/problem/coins-in-a-line/)
`dp[i]`现在还剩`i`个硬币，现在**先手**取硬币的人最后输赢状况

#### [Coins in a Line II](http://lintcode.com/en/problem/coins-in-a-line-ii/)
`dp[i]`现在还剩`i`个硬币，现在**先手**取硬币的人最后最多取硬币价值

#### [Coins in a Line III](http://lintcode.com/en/problem/coins-in-a-line-iii/)
`dp[i][j]`现在还第`i`到第`j`的硬币，现在**先手**取硬币的人最后最多取硬币价值