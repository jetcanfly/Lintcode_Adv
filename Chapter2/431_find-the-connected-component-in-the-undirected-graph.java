/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

class UnionFind<T> {

    HashMap<T, T> father = new HashMap<T, T>();

    UnionFind(HashSet<T> set) {
        for (T now : set) {
            father.put(now, now);
        }
    }

    T find(T x) {
        T parent = x;
        while (parent != father.get(parent)) {
            parent = father.get(parent);
        }
        return parent;
    }

    void union(T x, T y) {
        T fax = find(x);
        T fay = find(y);
        if (fax != fay) {
            father.put(fax, fay);
        }
    }
}

public class Solution {
    /**
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        if (nodes == null || nodes.size() == 0) {
            return new ArrayList<List<Integer>>();
        }

        // Put everything in a hash set
        HashSet<Integer> map = new HashSet<Integer>();
        for (UndirectedGraphNode node : nodes) {
            map.add(node.label);
            for (UndirectedGraphNode neighbor : node.neighbors) {
                map.add(neighbor.label);
            }
        }

        // Initial UnionSet
        UnionFind<Integer> uf = new UnionFind<Integer>(map);

        for (UndirectedGraphNode node : nodes) {
            for (UndirectedGraphNode neighbor : node.neighbors) {
                uf.union(node.label, neighbor.label);
            }
        }

        // Print Result
        return print(uf, map);
    }

    private List<List<Integer>> print(UnionFind<Integer> uf, HashSet<Integer> set) {
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

        for (int now : set) {
            int fa = uf.find(now);
            List<Integer> temp;
            if (map.containsKey(fa)) {
                temp = map.get(fa);
            } else {
                temp = new ArrayList<Integer>();
            }
            temp.add(now);
            map.put(fa, temp);
        }

        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        for (List<Integer> now : map.values()) {
            Collections.sort(now);
            ans.add(now);
        }
        return ans;
    }
}
