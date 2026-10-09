package session_nine_topics.assignment_problems;

import java.util.*;

/**
 * Session 9 - Assignment Problem 3: Most Popular Canteen Order
 * 
 * Algorithm: Single-pass frequency mapping with secondary scan over original list to break ties by first appearance.
 * Time Complexity: O(n)
 * Space Complexity: O(k) where k is the number of distinct items
 */
public class MostPopularCanteenOrder {

    public static class PopularItemResult {
        private String item;
        private int count;

        public PopularItemResult(String item, int count) {
            this.item = item;
            this.count = count;
        }

        public String getItem() {
            return item;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return String.format("(\"%s\", %d)", item, count);
        }
    }

    /**
     * Finds the most frequently ordered item. If there is a tie, returns the item that appeared first.
     * 
     * @param orders array of ordered item names
     * @return PopularItemResult containing (item, count)
     */
    public static PopularItemResult mostPopular(String[] orders) {
        if (orders == null || orders.length == 0) {
            return new PopularItemResult("", 0);
        }

        Map<String, Integer> freqMap = new HashMap<>();
        for (String order : orders) {
            freqMap.put(order, freqMap.getOrDefault(order, 0) + 1);
        }

        int maxCount = 0;
        String mostFrequentItem = "";

        // Iterate through orders in original order to preserve first-appearance tie breaking
        for (String order : orders) {
            int count = freqMap.get(order);
            if (count > maxCount) {
                maxCount = count;
                mostFrequentItem = order;
            }
        }

        return new PopularItemResult(mostFrequentItem, maxCount);
    }

    public static void main(String[] args) {
        // Sample 1
        String[] orders1 = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        System.out.println("Sample 1 Output: " + mostPopular(orders1));

        // Sample 2
        String[] orders2 = {"tea", "coffee", "coffee", "tea"};
        System.out.println("Sample 2 Output: " + mostPopular(orders2));
    }
}
