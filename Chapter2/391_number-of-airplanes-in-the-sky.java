/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 */

class Node {
    int time;
    int flag;
    Node(int t, int f) {
        time = t;
        flag = f;
    }
}

class NodeComparator implements Comparator<Node> {
    public int compare(Node x, Node y) {
        // If landing and flying happens at the same time,
        // we consider landing should happen at first.
        if (x.time == y.time) {
            return x.flag - y.flag;
        }
        return x.time - y.time;
    }
}

class Solution {
    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        if (airplanes == null || airplanes.size() == 0) {
            return 0;
        }

        List<Node> nodes = new ArrayList<Node>();
        for (Interval s : airplanes) {
            // If landing and flying happens at the same time,
            // we consider landing should happen at first.
            nodes.add(new Node(s.start, 1));
            nodes.add(new Node(s.end, 0));
        }

        Collections.sort(nodes, new NodeComparator());

        int count = 0;
        int best = Integer.MIN_VALUE;
        for (Node n : nodes) {
            if (n.flag == 1) {  // flying
                count++;
            } else {            // landing
                count--;
            }
            best = Math.max(best, count);
        }

        return best;

    }
}
