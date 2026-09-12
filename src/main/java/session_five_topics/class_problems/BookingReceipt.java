package session_five_topics.class_problems;

import java.util.Arrays;

public class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = (seatNumbers != null) ? seatNumbers.clone() : new String[0];
    }

    public String getBookingId() {
        return bookingId;
    }

    public String[] getSeatNumbers() {
        return (seatNumbers != null) ? seatNumbers.clone() : new String[0];
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] updatedSeats = (this.seatNumbers != null) ? this.seatNumbers.clone() : new String[0];
        if (index >= 0 && index < updatedSeats.length) {
            updatedSeats[index] = newSeat;
        }
        return new BookingReceipt(this.bookingId, updatedSeats);
    }

    @Override
    public String toString() {
        return "BookingReceipt{" +
                "bookingId='" + bookingId + '\'' +
                ", seatNumbers=" + Arrays.toString(seatNumbers) +
                '}';
    }
}