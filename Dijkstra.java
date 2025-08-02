import java.util.*;

class Pair{
    int node;
    int distance;

    Pair(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}
public class Dijkstra {
    public int[] dijkstra(ArrayList<ArrayList<ArrayList<Integer>>> adj, int src) {
        int n = adj.size();
        int[] dist = new int[n];
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distance));

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.offer(new Pair(src, 0));

        // while (!pq.isEmpty()) {
        //     Pair curr = pq.poll();
        //     int u = curr.node;

        //     if (visited[u]) continue;
        //     visited[u] = true;

        //     for (int v = 0; v < n; v++) {
        //         if (graph.get(u).get(v).get(0) != 0 && !visited[v]) {
        //             int newDist = dist[u] + graph.get(u).get(v).get(0);
        //             if (newDist < dist[v]) {
        //                 dist[v] = newDist;
        //                 pq.offer(new Pair(v, newDist));
        //             }
        //         }
        //     }
        // }
        while(!pq.isEmpty()) {
            Pair curr = pq.poll();
            int u = curr.node;

            for(int i=0;i<adj.get(u).size();i++) {
                int v = adj.get(u).get(i).get(0);
                int weight = adj.get(u).get(i).get(1);

                if(dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new Pair(v, dist[v]));
                }
            }
        }
        return dist;
    }
}
