package session_nine_topics.assignment_problems;

/**
 * Session 9 - Assignment Problem 1: Class Topper Finder
 * 
 * Algorithm: Row-by-row matrix sum accumulation and maximum tracking with tie-breaking (smallest row index).
 * Time Complexity: O(m * n) where m is students, n is subjects
 * Space Complexity: O(1)
 */
public class ClassTopperFinder {

    public static class TopperResult {
        private int rowIndex;
        private int totalMarks;

        public TopperResult(int rowIndex, int totalMarks) {
            this.rowIndex = rowIndex;
            this.totalMarks = totalMarks;
        }

        public int getRowIndex() {
            return rowIndex;
        }

        public int getTotalMarks() {
            return totalMarks;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", rowIndex, totalMarks);
        }
    }

    /**
     * Finds the student (row index) with the highest total marks across all subjects.
     * 
     * @param marks 2D array of student marks
     * @return TopperResult containing (rowIndex, total)
     */
    public static TopperResult findTopper(int[][] marks) {
        if (marks == null || marks.length == 0) {
            return new TopperResult(-1, 0);
        }

        int maxTotal = -1;
        int topperIndex = 0;

        for (int i = 0; i < marks.length; i++) {
            int currentTotal = 0;
            for (int j = 0; j < marks[i].length; j++) {
                currentTotal += marks[i][j];
            }

            // Strictly greater ensures the smallest row index is retained in case of ties
            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                topperIndex = i;
            }
        }

        return new TopperResult(topperIndex, maxTotal);
    }

    public static void main(String[] args) {
        int[][] marks = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };

        TopperResult result = findTopper(marks);
        System.out.println("Expected Output: (1, 259)");
        System.out.println("Actual Output:   " + result);
    }
}
