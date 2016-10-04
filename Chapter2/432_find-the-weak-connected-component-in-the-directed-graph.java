/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
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


public class Solution {
    /**
     * @param nodes a array of Directed graph node
     * @return a connected set of a directed graph
     */
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        if (nodes == null || nodes.size() == 0) {
            return new ArrayList<List<Integer>>();
        }

        // Put all in a set
        HashSet<Integer> set = new HashSet<Integer>();

        for (DirectedGraphNode node : nodes) {
            set.add(node.label);
            for (DirectedGraphNode neighbor : node.neighbors) {
                set.add(neighbor.label);
            }
        }

        // Union
        UnionFind<Integer> uf = new UnionFind<Integer>(set);

        for (DirectedGraphNode node : nodes) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                uf.compressed_union(node.label, neighbor.label);
            }
        }

        return print(uf, set);
    }

    private List<List<Integer>> print(UnionFind<Integer> uf, HashSet<Integer> set) {
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

        for (int now : set) {
            int fa = uf.compressed_find(now);
            if (!map.containsKey(fa)) {
                map.put(fa, new ArrayList<Integer>());
            }
            List<Integer> temp = map.get(fa);
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
