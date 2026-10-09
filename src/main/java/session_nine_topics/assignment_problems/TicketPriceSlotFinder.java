package session_nine_topics.assignment_problems;

/**
 * Session 9 - Assignment Problem 5: Ticket Price Slot Finder
 * 
 * Algorithm: Binary search to find exact index or insertion slot (lower bound / search insert position).
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 * 
 * Comparison:
 * - Linear scan: O(n) time.
 * - Binary search: O(log n) time.
 */
public class TicketPriceSlotFinder {

    /**
     * Finds the index of the price if present, or the index where it should be inserted
     * to maintain sorted order.
     * 
     * @param prices sorted array of distinct integer prices
     * @param newPrice price to find or insert
     * @return 0-based index between 0 and prices.length
     */
    public static int findSlot(int[] prices, int newPrice) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int low = 0;
        int high = prices.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (prices[mid] == newPrice) {
                return mid;
            } else if (prices[mid] < newPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        int[] prices = {120, 150, 200, 260};

        // Sample 1: already present
        System.out.println("findSlot(prices, 150) -> " + findSlot(prices, 150) + " (Expected: 1)");

        // Sample 2: insert in middle
        System.out.println("findSlot(prices, 210) -> " + findSlot(prices, 210) + " (Expected: 3)");

        // Sample 3: insert at end
        System.out.println("findSlot(prices, 300) -> " + findSlot(prices, 300) + " (Expected: 4)");
    }
}
