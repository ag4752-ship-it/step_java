package session_nine_topics.assignment_problems;

/**
 * Session 9 - Assignment Problem 4: Hot Weather Alert Windows
 * 
 * Algorithm: Fixed-size sliding window of size k with running sum comparison: sum >= k * threshold.
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * 
 * Comparison:
 * - Recalculating sum from scratch for each window: O(n * k)
 * - Sliding window updating in O(1) per step: O(n)
 */
public class HotWeatherAlertWindows {

    /**
     * Counts the number of contiguous k-hour blocks whose average temperature is at least threshold.
     * 
     * @param readings array of hourly temperature readings
     * @param k window block size
     * @param threshold temperature threshold
     * @return number of qualifying alert windows
     */
    public static int countAlerts(int[] readings, int k, int threshold) {
        if (readings == null || readings.length < k || k <= 0) {
            return 0;
        }

        long requiredSum = (long) k * threshold;
        long currentSum = 0;
        int alertCount = 0;

        // Compute sum of the first window
        for (int i = 0; i < k; i++) {
            currentSum += readings[i];
        }

        if (currentSum >= requiredSum) {
            alertCount++;
        }

        // Slide window across the array
        for (int i = k; i < readings.length; i++) {
            currentSum += readings[i] - readings[i - k];
            if (currentSum >= requiredSum) {
                alertCount++;
            }
        }

        return alertCount;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        int threshold = 4;

        int result = countAlerts(readings, k, threshold);
        System.out.println("Expected Output: 3");
        System.out.println("Actual Output:   " + result);
    }
}
