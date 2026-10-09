package session_nine_topics.class_problems;

import java.util.*;

/**
 * Session 9 - Class Problem 1: Library Catalog Lookup
 * 
 * Algorithm: Binary Search on sorted (ISBN, Title) catalog.
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class LibraryCatalogLookup {

    public static class BookRecord {
        private String isbn;
        private String title;

        public BookRecord(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }
    }

    /**
     * Efficiently searches a sorted list of book records by ISBN using binary search.
     * 
     * @param catalog sorted list of BookRecord objects
     * @param targetIsbn target ISBN to search for
     * @return book title if found, otherwise "Not Found"
     */
    public static String findBook(List<BookRecord> catalog, String targetIsbn) {
        if (catalog == null || catalog.isEmpty() || targetIsbn == null) {
            return "Not Found";
        }

        int low = 0;
        int high = catalog.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            BookRecord midRecord = catalog.get(mid);
            int cmp = midRecord.getIsbn().compareTo(targetIsbn);

            if (cmp == 0) {
                return midRecord.getTitle();
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        List<BookRecord> catalog = Arrays.asList(
            new BookRecord("0001112223", "Introduction to Algebra"),
            new BookRecord("0002223334", "Beginning Python"),
            new BookRecord("0003334445", "Classic Mythology"),
            new BookRecord("0004445556", "Data and Society"),
            new BookRecord("0005556667", "European History")
        );

        // Example 1
        String result1 = findBook(catalog, "0003334445");
        System.out.println("ISBN 0003334445: " + result1);

        // Example 2
        String result2 = findBook(catalog, "0009998887");
        System.out.println("ISBN 0009998887: " + result2);
    }
}
