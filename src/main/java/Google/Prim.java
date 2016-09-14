package Google;

import java.util.Comparator;
import java.util.PriorityQueue;

class PrimNode {
    int from;
    int distance;
    int to;
    public PrimNode(int i, int d, int p) {
        from = i;
        distance = d;
        to = p;
    }
}

public class Prim {
    public void prim(int[][] g, int s) {
        int[] distances = new int[g.length]; // already in found graph.
        int[] prev = new int[g.length];

        PriorityQueue<PrimNode> pq = new PriorityQueue<PrimNode>(new Comparator<PrimNode>() {
            @Override
            public int compare(PrimNode o1, PrimNode o2) {
                return o1.distance-o2.distance;
            }
        });

        pq.add(new PrimNode(s, 0, s));


        while (!pq.isEmpty()) {
            PrimNode node = pq.peek();
            for(int i=0;i<g.length;i++) {
                if (g[i][node.from] > 0) {
                    if (distances[i] > distances[node.to] + g[i][node.from])
                        pq.add(new PrimNode(node.to, distances[node.to] + g[i][node.from], i));
                }
            }
        }
    }
}
