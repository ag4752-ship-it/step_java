package session_nine_topics.class_problems;

import java.util.*;

/**
 * Session 9 - Class Problems 3 & 4: Pair With Target Sum (Unsorted Array)
 * 
 * Approaches:
 * 1. HashSet Complement Lookup (Optimal):
 *    - Time Complexity: O(n)
 *    - Space Complexity: O(n)
 * 2. Sorting + Two Pointers:
 *    - Time Complexity: O(n log n)
 *    - Space Complexity: O(1) or O(n) depending on in-place sort
 * 3. Brute Force Pair Check:
 *    - Time Complexity: O(n^2)
 *    - Space Complexity: O(1)
 */
public class PairWithTargetSum {

    /**
     * Determines if any two distinct elements sum to target using HashSet (Optimal).
     * 
     * @param nums array of integers (may contain negatives and duplicates)
     * @param target target sum
     * @return true if pair exists, false otherwise
     */
    public static boolean hasPairWithSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return true;
            }
            seen.add(num);
        }

        return false;
    }

    /**
     * Brute-force approach checking every possible pair.
     * 
     * @param nums array of integers
     * @param target target sum
     * @return true if pair exists, false otherwise
     */
    public static boolean hasPairWithSumBruteForce(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("nums1 = [2, 7, 11, 15], target = 9 -> " + hasPairWithSum(nums1, target1));

        // Example 2
        int[] nums2 = {3, 4, 6};
        int target2 = 20;
        System.out.println("nums2 = [3, 4, 6], target = 20 -> " + hasPairWithSum(nums2, target2));
    }
}
