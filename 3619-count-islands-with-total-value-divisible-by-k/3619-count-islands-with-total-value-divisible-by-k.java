
class Solution {
    public int countIslands(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] != 0) {
                    long sum = dfs(grid, i, j);

                    if (sum % k == 0) {
                        ans++;
                    }
                }
            }
        }

        return ans;
    }

    public long dfs(int[][] grid, int row, int col) {

        int n = grid.length;
        int m = grid[0].length;

        if (row < 0 || col < 0 ||
            row >= n || col >= m ||
            grid[row][col] == 0) {
            return 0;
        }

        long sum = grid[row][col];

        // Mark the current cell as visited
        grid[row][col] = 0;

        // Explore all four directions
        sum += dfs(grid, row + 1, col);
        sum += dfs(grid, row - 1, col);
        sum += dfs(grid, row, col + 1);
        sum += dfs(grid, row, col - 1);

        return sum;
    }
}
