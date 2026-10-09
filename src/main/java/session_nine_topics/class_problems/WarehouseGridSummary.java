package session_nine_topics.class_problems;

/**
 * Session 9 - Class Problem 2: Warehouse Grid Summary
 * 
 * Algorithm: Row-major 2D Grid traversal computing sum and tracking maximum with tie-breaking.
 * Time Complexity: O(m * n) where m is rows, n is columns
 * Space Complexity: O(1)
 */
public class WarehouseGridSummary {

    public static class SummaryResult {
        private int totalItems;
        private int maxRow;
        private int maxCol;

        public SummaryResult(int totalItems, int maxRow, int maxCol) {
            this.totalItems = totalItems;
            this.maxRow = maxRow;
            this.maxCol = maxCol;
        }

        public int getTotalItems() {
            return totalItems;
        }

        public int getMaxRow() {
            return maxRow;
        }

        public int getMaxCol() {
            return maxCol;
        }

        @Override
        public String toString() {
            return String.format("(%d, (%d, %d))", totalItems, maxRow, maxCol);
        }
    }

    /**
     * Calculates total items across all bins and finds coordinates of the bin with max items.
     * Ties are broken by the first encountered bin in row-major order.
     * 
     * @param grid 2D array of non-negative integers
     * @return SummaryResult containing total items and max coordinate (row, col)
     */
    public static SummaryResult warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new SummaryResult(0, -1, -1);
        }

        int totalItems = 0;
        int maxVal = Integer.MIN_VALUE;
        int maxRow = 0;
        int maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int val = grid[r][c];
                totalItems += val;
                if (val > maxVal) {
                    maxVal = val;
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        return new SummaryResult(totalItems, maxRow, maxCol);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        SummaryResult result = warehouseSummary(grid);
        System.out.println("Expected Output: (49, (2, 1))");
        System.out.println("Actual Output:   " + result);
    }
}
