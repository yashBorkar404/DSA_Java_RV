public class LeastCitiesWithinThreshold {
    public int FloydWarshall(int[][] edges, int threshold) {
        int n = edges.length;
        int[][] dist = new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i == j){
                    dist[i][j] = 0;
                }
                else{
                    dist[i][j] = Integer.MAX_VALUE / 2; // Use a large value to avoid overflow
                }
            }
        }
        for(int[] edge: edges){
            dist[edge[0]][edge[1]] = edge[2];
            dist[edge[1]][edge[0]] = edge[2];
        }
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(dist[i][k] != Integer.MAX_VALUE / 2 && dist[k][j] != Integer.MAX_VALUE / 2){
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        int city = -1;
        int countMax = 0;
        for(int i=0;i<n;i++){
            int count = 0;
            for(int j=0;j<n;j++){
                if(i!=j && dist[i][j] <= threshold){
                    count++;
                }

            }
            if(count >= countMax){
                countMax = count;
                city = i;
            }
        }
        return city;
    }
}
