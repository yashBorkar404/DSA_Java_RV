//Assumptions or expectations: Since we are using topological sorting we have assumed that there are no cycles in the graph , and also we assume that the node with least or say 0 indegree is the source node and has path to reach  all the nodes and hence there are no cycles.

import java.util.*;

class Pair{
    int node;
    int weight;
    Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}
public class ShortestPathDIrectedAcyclic {
      public int[] shortestDistance(int[][] graph, int source) {
          ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
          for (int i = 0; i < graph.length; i++) {
              adj.add(new ArrayList<>());
          }
          for(int i=0;i<graph.length;i++){
            for(int j=0;j<graph[i].length;j++){
                if(graph[i][j] < Integer.MAX_VALUE && graph[i][j] > 0) {
                    adj.get(i).add(new Pair(j, graph[i][j]));
                } 
            }
          }
          int V = graph.length;
          int[] dist = new int[V];
          Arrays.fill(dist, Integer.MAX_VALUE);
          dist[source] = 0;

          // Topological sort
          Stack<Integer> stack = new Stack<>();
          boolean[] visited = new boolean[V];
          for (int i = 0; i < V; i++) {
              if (!visited[i]) {
                  topologicalSortUtil(adj, i, visited, stack);
              }
          }

          // Relax edges in topological order
          while (!stack.isEmpty()) {
              int u = stack.pop();
                  for (Pair neighbor : adj.get(u)) {
                      if (dist[u] + neighbor.weight < dist[neighbor.node]) {
                          dist[neighbor.node] = dist[u] + neighbor.weight;
                      }
                   }
          }
          return dist;
      }
        private void topologicalSortUtil(ArrayList<ArrayList<Pair>> adj, int v, boolean[] visited, Stack<Integer> stack) {
            visited[v] = true;
            for (Pair neighbor : adj.get(v)) {
                if (!visited[neighbor.node]) {
                    topologicalSortUtil(adj, neighbor.node, visited, stack);
                }
            }
            stack.push(v);
        }
    }
