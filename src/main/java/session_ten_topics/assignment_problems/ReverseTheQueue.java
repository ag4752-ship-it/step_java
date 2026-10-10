package session_ten_topics.assignment_problems;

/**
 * Session 10 - Assignment Problem 3: Reverse the Queue
 * 
 * Algorithm: In-place iterative singly linked list reversal using previous, current, and next pointers.
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class ReverseTheQueue {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Reverses the singly linked list in place without allocating new nodes.
     * 
     * @param head head of the queue linked list
     * @return new head of the reversed list
     */
    public static Node reverse(Node head) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        return prev;
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
        // Sample 1: queue = 1 -> 2 -> 3 -> 4
        Node queue1 = buildList(new int[]{1, 2, 3, 4});
        Node rev1 = reverse(queue1);
        System.out.print("Sample 1 Output: ");
        printList(rev1); // Expected: 4 -> 3 -> 2 -> 1 -> null

        // Sample 2: queue = 9
        Node queue2 = buildList(new int[]{9});
        Node rev2 = reverse(queue2);
        System.out.print("Sample 2 Output: ");
        printList(rev2); // Expected: 9 -> null
    }
}
