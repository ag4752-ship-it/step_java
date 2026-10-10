package session_ten_topics.assignment_problems;

/**
 * Session 10 - Assignment Problem 2: Cancelled Orders Cleanup
 * 
 * Algorithm:
 * 1. Remove matching nodes from the front by updating head pointer.
 * 2. Traverse the remainder using a previous pointer, skipping nodes where curr.data == code.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class CancelledOrdersCleanup {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Removes every node with the given code and returns the new head.
     * 
     * @param head head of the singly linked list
     * @param code order code to remove
     * @return updated head of the list
     */
    public static Node removeAll(Node head, int code) {
        // Step 1: Remove matching nodes from the front
        while (head != null && head.data == code) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        // Step 2: Traverse and skip matching nodes in the rest of the list
        Node curr = head;
        while (curr.next != null) {
            if (curr.next.data == code) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        return head;
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while (curr != null) {
            sb.append(curr.data).append(" -> ");
            curr = curr.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    public static Node buildList(int[] values) {
        if (values == null || values.length == 0) return null;
        Node head = new Node(values[0]);
        Node curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new Node(values[i]);
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        // Sample 1: 5 -> 3 -> 5 -> 8 -> 5, code = 5
        Node list1 = buildList(new int[]{5, 3, 5, 8, 5});
        Node result1 = removeAll(list1, 5);
        System.out.print("Sample 1 Output: ");
        printList(result1); // Expected: 3 -> 8 -> null

        // Sample 2: 5 -> 5 -> 5, code = 5
        Node list2 = buildList(new int[]{5, 5, 5});
        Node result2 = removeAll(list2, 5);
        System.out.print("Sample 2 Output: ");
        printList(result2); // Expected: null
    }
}
