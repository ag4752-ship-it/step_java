package session_five_topics.assignment_problems;

public class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {
        if (copiesTotal <= 0) {
            throw new IllegalArgumentException("Total copies must be positive.");
        }
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
        // Silently rejected if no copies are available
    }

    public void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
        // Silently rejected if already at full capacity
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public int getCopiesTotal() {
        return copiesTotal;
    }

    public static void main(String[] args) {
        // Test 1: Checkout up to and past zero
        BookInventory b = new BookInventory(3);
        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut(); // 4th attempt silently rejected
        System.out.println("Copies available after 4 checkouts: " + b.getCopiesAvailable()); // 0

        // Test 2: Check in up to and past full capacity
        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn(); // 4th attempt silently rejected
        System.out.println("Copies available after 4 checkins: " + b.getCopiesAvailable()); // 3
    }
}