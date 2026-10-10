package session_ten_topics.assignment_problems;

/**
 * Session 10 - Assignment Problem 1: Priority Token Insertion
 * 
 * Demonstrates:
 * - Singly linked list custom implementation without java.util.LinkedList.
 * - addFirst (O(1)), addLast (O(n) or O(1) with tail), and insertAt(index, token) (O(n)).
 */
public class PriorityTokenInsertion {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class SinglyLinkedList {
        private Node head;

        public SinglyLinkedList() {
            this.head = null;
        }

        public Node getHead() {
            return head;
        }

        public void addFirst(int token) {
            Node newNode = new Node(token);
            newNode.next = head;
            head = newNode;
        }

        public void addLast(int token) {
            Node newNode = new Node(token);
            if (head == null) {
                head = newNode;
                return;
            }
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }

        public void insertAt(int index, int token) {
            if (index < 0) {
                throw new IndexOutOfBoundsException("Negative index: " + index);
            }
            if (index == 0) {
                addFirst(token);
                return;
            }

            Node curr = head;
            for (int i = 0; i < index - 1; i++) {
                if (curr == null) {
                    throw new IndexOutOfBoundsException("Index exceeds list size: " + index);
                }
                curr = curr.next;
            }

            if (curr == null) {
                throw new IndexOutOfBoundsException("Index exceeds list size: " + index);
            }

            Node newNode = new Node(token);
            newNode.next = curr.next;
            curr.next = newNode;
        }

        public void printList() {
            Node curr = head;
            StringBuilder sb = new StringBuilder();
            while (curr != null) {
                sb.append(curr.data).append(" -> ");
                curr = curr.next;
            }
            sb.append("null");
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        // Initial: 101 -> 102 -> 103
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        // Operations: addFirst(100), addLast(104), insertAt(2, 150)
        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        // Expected Output: 100 -> 101 -> 150 -> 102 -> 103 -> 104 -> null
        list.printList();
    }
}
