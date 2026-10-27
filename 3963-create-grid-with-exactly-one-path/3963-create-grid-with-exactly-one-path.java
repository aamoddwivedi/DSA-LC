
import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        // Block every cell initially
        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }

        // Keep the first row free
        Arrays.fill(grid[0], '.');

        // Keep the last column free
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }

        // Convert each row into a String
        String[] ans = new String[m];

        for (int i = 0; i < m; i++) {
            ans[i] = new String(grid[i]);
        }

        return ans;
    }
}
