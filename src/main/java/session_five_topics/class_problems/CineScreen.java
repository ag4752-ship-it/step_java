package session_five_topics.class_problems;

public class CineScreen {
    private int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            throw new IllegalArgumentException("Invalid total seats: must be greater than 0.");
        }
        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }

    public void bookSeat() {
        if (seatsAvailable > 0) {
            seatsAvailable--;
        }
        // Silently reject if no seats available
    }

    public void cancelBooking() {
        if (seatsAvailable < seatsTotal) {
            seatsAvailable++;
        }
        // Silently reject if already back to full capacity
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public int getSeatsTotal() {
        return seatsTotal;
    }

    public static void main(String[] args) {
        // Test 1: Invalid construction
        try {
            new CineScreen(0);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        // Test 2: Booking beyond capacity
        CineScreen c1 = new CineScreen(2);
        c1.bookSeat();
        c1.bookSeat();
        c1.bookSeat(); // 3rd booking rejected silently
        System.out.println("Seats available after 3 bookings on screen of size 2: " + c1.getSeatsAvailable()); // 0

        // Test 3: Cancelling beyond capacity
        c1.cancelBooking();
        c1.cancelBooking();
        c1.cancelBooking(); // 3rd cancellation rejected silently
        System.out.println("Seats available after 3 cancellations: " + c1.getSeatsAvailable()); // 2
    }
}