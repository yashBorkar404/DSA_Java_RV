public class FloodFill {
    private void dfs(int[][] image, int sr, int sc, int[] delrow,int[] delcol, int originalColor, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != originalColor) {
            return; // Out of bounds or not the original color
        }

        image[sr][sc] = newColor; // Fill with the new color

        // Recursively fill in all four directions
        for (int i = 0; i < 4; i++) {
            int newRow = sr + delrow[i];
            int newCol = sc + delcol[i];
            if(newRow >= 0 && newRow < image.length && newCol >= 0 && newCol < image[0].length && image[newRow][newCol] == originalColor && image[newRow][newCol] != newColor) {
                // Only call dfs if the new position is within bounds and has the original color
                dfs(image, newRow, newCol, delrow, delcol, originalColor, newColor);
            }
        }
    }
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
       if(image == null || image.length == 0 || image[0].length == 0) {
            return image; // Return the original image if it's empty
        }
        
        int originalColor = image[sr][sc]; // Get the color at the starting pixel
        if(originalColor == newColor) {
            return image; // If the original color is the same as the new color, no need to fill
        }
        
        int[] delrow = {-1, 1, 0, 0}; // Directions for row movement
        int[] delcol = {0, 0, -1, 1}; // Directions for column movement
        
        dfs(image, sr, sc, delrow, delcol, originalColor, newColor); // Start DFS from the given pixel
        
        return image; // Return the modified image
    }
}