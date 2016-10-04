# Chapter 2 - Data Structure I
## Lecture
- PPT
[2._data_structure_1._2016.04.03.pdf](2._data_structure_1._2016.04.03.pdf)
- Video https://www.dropbox.com/s/g8q7xz2rto4wcu5/Chaper2_Data_Structure_I.mov?dl=0


### Union Find
并查集

一种用来解决**集合查询**合并的数据结构

支持O(1)find/O(1)union

Template
```java
class UnionFind<T> {
    /* Key: Node, Value: Father (Boss) */
    HashMap<T, T> father = new HashMap<T, T>();
    UnionFind() {}

    UnionFind(HashSet<T> set) {
        for (T now : set) {
            father.put(now, now);
        }
    }

    /* O(n) time */
    T find(T x) {   
        T parent = x;
        while (parent != father.get(parent)) {
            parent = father.get(parent);
        }
        return parent;
    }

    void union(T x, T y) {
        T fa_x = find(x);
        T fa_y = find(y);
        if (fa_x != fa_y) {
            father.put(fa_x, fa_y);
        }
    }

    /* O(1) time */
    T compressed_find(T x) {
        T parent = x;
        while (parent != father.get(parent)) {
            parent = father.get(parent);
        }

        while (x != father.get(x)) {
            T next = father.get(x);
            father.put(x, parent);
            x = next;
        }
        return parent;
    }

    void compressed_union(T x, T y) {
        T fa_x = compressed_find(x);
        T fa_y = compressed_find(y);
        if (fa_x != fa_y) {
            father.put(fa_x, fa_y);
        }
    }
}
```

练习题目 | Links | Video  | Note
:----------|:-----: | :---: | :---:
(431)Find the Connected Component in the Undirected Graph | [*LintCode*](http://www.lintcode.com/en/problem/find-the-connected-component-in-the-undirected-graph/) - [*Solution*](http://www.jiuzhang.com/solutions/find-the-connected-component-in-the-undirected-graph/) - [*File*](431_find-the-connected-component-in-the-undirected-graph.java) |  | 强连通块: 无向图一个块中节点你找得到我，我也找得到你
(432)Find the Weak Connected Component in the Directed Graph | [*LintCode*](http://www.lintcode.com/en/problem/find-the-weak-connected-component-in-the-directed-graph/) - [*Solution*](http://www.jiuzhang.com/solutions/find-the-weak-connected-component-in-the-directed-graph/) - [*File*](432_find-the-weak-connected-component-in-the-directed-graph.java) |  | 弱连通块: 有向图一个块中，你找得到我，我可以找不到你
(433)Number of Islands | [*LintCode*](http://www.lintcode.com/en/problem/number-of-islands/) - [*Solution*](http://www.jiuzhang.com/solutions/number-of-islands/) - [*File*](433_number-of-islands.java) |  |
(433)Number of Islands II | [*LintCode*](http://www.lintcode.com/en/problem/number-of-islands-ii/) - [*Solution*](http://www.jiuzhang.com/solutions/number-of-islands-ii/) - [*File*](434_number-of-islands-ii.java) |  |
(178)Graph Valid Tree | [*LintCode*](http://www.lintcode.com/en/problem/graph-valid-tree/) - [*Solution*](http://www.jiuzhang.com/solutions/graph-valid-tree/) - [*File*](178_graph-valid-tree.java) |  |
(477)Surrounded Regions | [*LintCode*](http://www.lintcode.com/en/problem/surrounded-regions/) - [*Solution*](http://www.jiuzhang.com/solutions/surrounded-regions/) - [*File*](477_surrounded-regions.java) |  |


### Trie
- 一个一个字符串遍历
- 需要节约空间
- 查找前缀

```java
/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */
class TrieNode {

    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    char c;
    String s;   // The word generated from root. isString is True
    boolean isString;

    public TrieNode() {}

    public TrieNode(char c) {
        this.c = c;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode curr = root;
        HashMap<Character, TrieNode> currChildren = curr.children;
        char[] wordArr = word.toCharArray();
        for (int i = 0; i < wordArr.length; i++) {
            char c = wordArr[i];
            if (currChildren.containsKey(c)) {
                curr = currChildren.get(c);
            } else {
                TrieNode newNode = new TrieNode(c);
                currChildren.put(c, newNode);
                curr = newNode;
            }
            currChildren = curr.children;
        }
        curr.isString = true;
        curr.s = word;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode pos = searchWordNodePos(word);
        if (pos == null) {
            return false;
        } else if (pos.isString == true) {
            return true;
        }
        return false;

    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode pos = searchWordNodePos(prefix);
        if (pos == null) {
            return false;
        }
        return true;
    }

    private TrieNode searchWordNodePos(String s){
        char[] sArr = s.toCharArray();
        TrieNode curr = root;
        HashMap<Character, TrieNode> currChildren = curr.children;

        for (char c : sArr) {
            if (currChildren.containsKey(c)) {
                curr = currChildren.get(c);
                currChildren = curr.children;
            } else {
                return null;
            }
        }
        return curr;
    }
}
```

练习题目 | Links | Video  | Note
:----------|:-----: | :---: | :---:
(442)Implement Trie | [*LintCode*](http://www.lintcode.com/en/problem/implement-trie/) - [*Solution*](http://www.jiuzhang.com/solutions/implement-trie/) - [*File*](442_implement-trie.java) |  |
(123)Word Search | [*LintCode*](http://www.lintcode.com/en/problem/word-search/) - [*Solution*](http://www.jiuzhang.com/solutions/word-search/) - [*File*](123_word-search.java) |  |
(132)Word Search II | [*LintCode*](http://www.lintcode.com/en/problem/word-search-ii/) - [*Solution*](http://www.jiuzhang.com/solutions/word-search-ii/) - [*File*](132_word-search-ii.java) |  |
(473)Add and Search Word | [*LintCode*](http://www.lintcode.com/en/problem/add-and-search-word/) - [*Solution*](http://www.jiuzhang.com/solutions/add-and-search-word/) - [*File*](473_add-and-search-word.java) |  |

### Sweep Line
练习题目 | Links | Video  | Note
:----------|:-----: | :---: | :---:
(391)Number of Airplanes in the Sky | [*LintCode*](http://www.lintcode.com/en/problem/number-of-airplanes-in-the-sky/) - [*Solution*](http://www.jiuzhang.com/solutions/number-of-airplanes-in-the-sky/) - [*File*](391_number-of-airplanes-in-the-sky.java) |  |
