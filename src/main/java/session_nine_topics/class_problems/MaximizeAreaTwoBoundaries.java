package session_nine_topics.class_problems;

/**
 * Session 9 - Class Problem 5: Maximize Area Between Two Boundaries (Container With Most Water)
 * 
 * Algorithm: Two-Pointer greedy approach moving the shorter boundary inward.
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * 
 * Brute-force comparison:
 * - Checks all pairs: Time O(n^2), Space O(1).
 */
public class MaximizeAreaTwoBoundaries {

    /**
     * Calculates the maximum area between two vertical boundary lines.
     * 
     * @param heights array of non-negative integers representing line heights
     * @return maximum container area
     */
    public static int maxContainerArea(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }

        int maxArea = 0;
        int left = 0;
        int right = heights.length - 1;

        while (left < right) {
            int h = Math.min(heights[left], heights[right]);
            int width = right - left;
            int currentArea = h * width;
            maxArea = Math.max(maxArea, currentArea);

            // Move the pointer with the smaller height inward to potentially find a taller boundary
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = maxContainerArea(heights);

        System.out.println("Expected Output: 49");
        System.out.println("Actual Output:   " + result);
    }
}
