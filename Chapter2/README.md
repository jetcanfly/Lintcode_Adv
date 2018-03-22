# Chapter 2 数据结构(上)

## Union Find 并查集

一种用来解决集合查询合并的数据结构
支持 `O(1)` find / `O(1)` union

**考点**

- 关于集合合并
- 判断在不在同一个集合中

**模板**
```java
class UnionFind<T> {

    private Map<T, T> father = new HashMap<>();

    // Initialization: every one is its own father
    public UnionFind(Set<T> set) {
        for (T v : set) {
            father.put(v, v);
        }
    }

    public T find(T x) {
        T parent = x;
        while (parent != father.get(x)) {
            parent = father.get(x);
        }
        return parent;
    }

    // 均摊时间复杂度 O(1)
    public T compress_find(T x) {
        // find x's father
        T parent = x;
        while (parent != father.get(x)) {
            parent = father.get(x);
        }

        // Update the father along the path
        while (parent != x) {
            T next = father.get(x);
            father.put(x, parent);
            x = next;
        }

        return parent;
    }

    // 均摊时间复杂度 O(1)
    public void union(T x, T y) {
        // Find the father for each
        T fax = compress_find(x);
        T fay = compress_find(y);

        // Union
        if (fax != fay) {
            father.put(fax, fay);
        }
    }
}
```

**题目**

- Find the Connected Component in the Undirected Graph
- Find the Weak Connected Component in the Directed Graph
- Number of Islands II
- Graph Valid Tree
- Surrounded Regions


## Trie 字典树

**Hash vs Trie**

什么样的题目适合Trie?

- 一个一个字符串遍历
- 需要节省空间
- 查找前缀


**模板**
```java
class Trie {
    private TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode curr = root;

        for (char c : word.toCharArray()) {
            curr.children.putIfAbsent(c, new TrieNode(c));
            curr = curr.children.get(c);
        }

        curr.isWord = true;
    }

    public boolean search(String word) {
        TrieNode node = startsWith(word);
        return node == null ? false : node.isWord;
    }

    public TrieNode startsWith(String word) {
        TrieNode curr = root;

        for (char c : word.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                return null;
            }
            curr = curr.children.get(c);
        }

        return curr;
    }
}

class TrieNode {
    char c;
    boolean isWord;
    Map<Character, TrieNode> children = new HashMap<>();

    TrieNode() {}

    TrieNode(char c) {
        this.c = c;
    }
}
```

**题目**

- Implement Trie
- Word Search II
- Add and Search Word

## Sweep-Line 扫描线

**区间拆分**，按“起点”或者“终点”排序

```java
class Node {
    int val;
    int isStart;    // use `int` instead of `boolean` in order to sort by start or end if necessary
}
```

例如：区间`[1, 2]`可以拆分为
`Node(1, 1)` 和 `Node(2, 0)`

当两个区间的`val`相等时，如何排序？ 

- `start` before `end`, or 
- `end` before `start`?


Below is the `Comparator` that sorted by `val` first, then put `end` before `start`
```java
class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node a, Node b) {
        if (a.val == b.val) {
            return a.isStart - b.isStart;
        }
        return a.val - b.val;
    }
}
```

`Collections.sort(nodes, new NodeComparator());`


**题目**

- Number of Airplane in the sky
- [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)