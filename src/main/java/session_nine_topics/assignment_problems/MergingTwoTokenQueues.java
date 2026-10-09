package session_nine_topics.assignment_problems;

import java.util.*;

/**
 * Session 9 - Assignment Problem 2: Merging Two Token Queues
 * 
 * Algorithm: Two-pointer linear merge of two pre-sorted lists/arrays.
 * Time Complexity: O(m + n)
 * Space Complexity: O(m + n)
 * 
 * Comparison:
 * - Combining and sorting: O((m + n) log(m + n)) time.
 * - Two-pointer linear merge: O(m + n) time, leveraging pre-sorted property.
 */
public class MergingTwoTokenQueues {

    /**
     * Merges two sorted integer arrays into a single sorted array preserving duplicates.
     * 
     * @param counterA sorted array from counter A
     * @param counterB sorted array from counter B
     * @return combined sorted array
     */
    public static int[] mergeTokens(int[] counterA, int[] counterB) {
        if (counterA == null && counterB == null) return new int[0];
        if (counterA == null || counterA.length == 0) return counterB != null ? counterB.clone() : new int[0];
        if (counterB == null || counterB.length == 0) return counterA.clone();

        int m = counterA.length;
        int n = counterB.length;
        int[] merged = new int[m + n];

        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (counterA[i] <= counterB[j]) {
                merged[k++] = counterA[i++];
            } else {
                merged[k++] = counterB[j++];
            }
        }

        while (i < m) {
            merged[k++] = counterA[i++];
        }

        while (j < n) {
            merged[k++] = counterB[j++];
        }

        return merged;
    }

    public static void main(String[] args) {
        // Sample 1
        int[] counterA1 = {3, 8, 15, 20};
        int[] counterB1 = {5, 8, 12};
        System.out.println("Sample 1 Output: " + Arrays.toString(mergeTokens(counterA1, counterB1)));

        // Sample 2
        int[] counterA2 = {};
        int[] counterB2 = {4, 9};
        System.out.println("Sample 2 Output: " + Arrays.toString(mergeTokens(counterA2, counterB2)));
    }
}
