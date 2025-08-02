import java.util.LinkedList;
import java.util.Queue;
class Pair{
    int node;
    int distance;

    Pair(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}
public class MinMultiToReachEnd {
    public int minMultiplication(int[] arr, int start, int end) {
        int n = arr.length;
        if (start == end) return 0;
        
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(start, 0));
        int dist[] = new int[100000];
        for(int i = 0; i < 100000; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;
        int mod = 100000;

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            int node = current.node;
            int distance = current.distance;
            for(int i = 0; i < n; i++) {
                int nextNode = (node * arr[i]) % mod;
                if (nextNode == end) {
                    return distance + 1; // Found the end node
                }
                if (dist[nextNode] > distance + 1) {
                    dist[nextNode] = distance + 1;
                    queue.offer(new Pair(nextNode, distance + 1));
                }
            }
    
        }
        return -1; // If no path found
    }
}
