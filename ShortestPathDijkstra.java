
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Collections;

class Pair {
    int node;
    int distance;

    Pair(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}
public class ShortestPathDijkstra {
       public List<Integer> dijkstra(ArrayList<ArrayList<ArrayList<Integer>>> adj, int src) {
        int n = adj.size();
        int[] dist = new int[n];
        int[] parent = new int[n];
        ArrayList<Integer> path = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y) -> x.distance - y.distance);
        for(int i=0;i<n;i++) {
            parent[i] = i; // Initialize parent to itself
            dist[i] = Integer.MAX_VALUE; // Initialize distances to infinity
        }
        dist[src] = 0;
        pq.offer(new Pair(src, 0));
        parent[src] = src; // Source is its own parent
        dist[src] = 0;
        while(!pq.isEmpty()) {
            Pair curr = pq.poll();
            int u = curr.node;

            for(int i=0;i<adj.get(u).size();i++) {
                int v = adj.get(u).get(i).get(0);
                int weight = adj.get(u).get(i).get(1);

                if(dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    pq.offer(new Pair(v, dist[v]));
                }
            }
        }
    
        int i = n;
        if(dist[n] == Integer.MAX_VALUE) {
            path.add(-1); // Indicate no path exists
            return path; // Return empty path if no path exists
        }
        while(parent[i] != i) {
            path.add(i);
            i = parent[i];
        }
        path.add(src);
        Collections.reverse(path);
        return path;
    }
    }