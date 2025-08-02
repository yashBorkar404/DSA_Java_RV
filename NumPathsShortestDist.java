import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

class Pair{
    int node;
    int distance;

    Pair(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}
public class NumPathsShortestDist {
    public int numPaths(int n, List<List<Integer>> edges, int start, int end) {
        if (start == end) return 0;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for(int i= 0; i < edges.size(); i++) {
            adj.get(edges.get(i).get(0)).add(new Pair(edges.get(i).get(1), 1));
            adj.get(edges.get(i).get(1)).add(new Pair(edges.get(i).get(0), 1));
        }

        

        PriorityQueue<Pair> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.distance, b.distance));
        queue.offer(new Pair(start, 0));
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        int ways[] = new int[n];
        Arrays.fill(ways, 0);
        ways[start] = 1;
        int mod = (int)1e9 + 7;
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            int u = curr.node;
            int d = curr.distance;

            if (d > dist[u]) continue;

            for (Pair p : adj.get(u)) {
                int v = p.node;
                int adjDist  = p.distance;
                if (dist[v] > d + adjDist) {
                    dist[v] = d + adjDist;
                    queue.offer(new Pair(v, dist[v]));
                    ways[v] = ways[u]; // Reset ways to the number of ways to reach u
                } else if (dist[v] == d + adjDist) {
                    ways[v] = (ways[v] + ways[u]) % mod; // Add the number of ways to reach u
                }
            }
        }

        return ways[end];
    }
} 
    
