class Node {
    int idx, h, flag;
    // flag = 1: start
    // flag = 0: end
    Node(int idx, int h, int flag) {
        this.idx = idx;
        this.h = h;
        this.flag = flag;
    }
}

class NodeComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        if (a.idx == b.idx) {
            return a.h - b.h;
        }
        return a.idx - b.idx;
    }
}

class NodeHeightComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        return -(a.h - b.h);    // Maximum heap
    }
}

public class Solution {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        if (buildings == null || buildings.length == 0) {
            return ans;
        }
        if (buildings[0] == null || buildings[0].length == 0) {
            return ans;
        }


        List<Node> nodes = new ArrayList<Node>();
        for (int[] building : buildings) {
            nodes.add(new Node(building[0], building[2], 1));
            nodes.add(new Node(building[1], building[2], 0));
        }
        Collections.sort(nodes, new NodeComparator());

        // Max Heap
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.offer(0);
        int currHeight = 0;

        for (Node node : nodes) {
            if (node.flag == 1) {   // start
                pq.offer(-node.h);  // add current build to pq
            } else if (node.flag == 0) {
                pq.remove(-node.h); // Remove the building from pq
            }
            if (currHeight != pq.peek()) {
                ArrayList<Integer> tmp = new ArrayList<Integer>();
                tmp.add(node.idx);
                tmp.add(-currHeight);
                ans.add(tmp);
                currHeight = pq.peek();
            }
        }
        // ans at this step: [[1,0],[2,3],[4,4],[5,0],[6,1]]
        // desired: [[1,2,3],[2,4,4],[5,6,1]]

        // Merge arrays
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        for (int i = 1; i < ans.size(); i++) {
            // same start point [138,138,345]
            if (ans.get(i - 1).get(0).intValue() == ans.get(i).get(0).intValue()) {
                continue;
            }
            // zero height [4,5,0]
            if (ans.get(i).get(1).intValue() == 0) {
                continue;
            }
            // same height
            // [[1,3,3],[2,3,2],[3,6,3]] --> [1,3,3], [3,6,3] --> [1,6,3]
            if (!rst.isEmpty() && ans.get(i).get(1).intValue() == rst.get(rst.size()-1).get(2)) {
                rst.get(rst.size()-1).set(1, ans.get(i).get(0));
                continue;
            }


            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(ans.get(i - 1).get(0));
            tmp.addAll(ans.get(i));
            rst.add(tmp);
        }

        return rst;
    }
}
