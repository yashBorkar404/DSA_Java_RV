import java.util.PriorityQueue;
import java.util.ArrayList;


class Pair{
    int node;
    int distance;

    Pair(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

public class Prims{
    public int prims(int n, ArrayList<ArrayList<ArrayList<Integer>>> adj){
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        boolean[] visited = new boolean[n];
        int totalCost = 0;
        pq.offer(new Pair(0, 0)); // Start from node 0 with distance 0
        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int node = current.node;
            int distance = current.distance;

            if (visited[node]) continue; // Skip if already visited
            visited[node] = true;
            totalCost += distance;
            for (ArrayList<Integer> neighbor : adj.get(node)) {
                int nextNode = neighbor.get(0);
                int edgeWeight = neighbor.get(1);
                if (!visited[nextNode]) {
                    pq.offer(new Pair(nextNode, edgeWeight));
                }
            } 

            
    }
        return totalCost;
    }   
}