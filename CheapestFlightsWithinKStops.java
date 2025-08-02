import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
class Triple{
    int dist;
    int row;
    int col;
    Triple(int dist, int row, int col) {
        this.dist = dist;
        this.row = row;
        this.col = col;
    }
}
class Pair {
    int node;
    int distance;

    Pair(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}
public class CheapestFlightsWithinKStops {
     public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i =0;i< flights.length; i++) {
            int u = flights[i][0];
            int v = flights[i][1];
            int cost = flights[i][2];
            graph.get(u).add(new Pair(v, cost));
        }
        Queue<Triple> pq = new LinkedList<>();  
        pq.offer(new Triple(0, src, 0));
        int dist[] = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        while (!pq.isEmpty()) {
            Triple curr = pq.poll();
            int cost = curr.dist;
            int node = curr.row;
            int stops = curr.col;
            if (stops > k) {
                continue;
            }
            for(Pair neighbor : graph.get(node)) {
                int nextNode = neighbor.node;
                int nextCost = neighbor.distance;
                if (cost + nextCost < dist[nextNode] && stops <= k) {
                    dist[nextNode] = cost + nextCost;
                    pq.offer(new Triple(dist[nextNode], nextNode, stops + 1));
                }
            }
        
        }
        if(dist[dst] != Integer.MAX_VALUE) {
            return dist[dst];
        }
        return -1;
    }
}