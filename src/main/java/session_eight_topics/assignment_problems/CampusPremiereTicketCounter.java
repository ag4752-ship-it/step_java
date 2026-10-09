package session_eight_topics.assignment_problems;

import java.util.*;

/**
 * Session 8 - Assignment Problem 3: The Campus Premiere Ticket Counter
 * 
 * Demonstrates:
 * - Polymorphic Seat categories (Regular: ₹150, Premium: ₹250, Recliner: ₹400).
 * - Multi-seat booking management (up to 6 seats) and duplicate seat reservation prevention.
 * - Booking cancellation and dynamic seat release mechanism before show start.
 */
public class CampusPremiereTicketCounter {

    public static abstract class Seat {
        protected String seatNumber;
        protected String category;
        protected double price;

        public Seat(String seatNumber, String category, double price) {
            this.seatNumber = seatNumber;
            this.category = category;
            this.price = price;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }
    }

    public static class RegularSeat extends Seat {
        public RegularSeat(String seatNumber) {
            super(seatNumber, "Regular", 150.00);
        }
    }

    public static class PremiumSeat extends Seat {
        public PremiumSeat(String seatNumber) {
            super(seatNumber, "Premium", 250.00);
        }
    }

    public static class ReclinerSeat extends Seat {
        public ReclinerSeat(String seatNumber) {
            super(seatNumber, "Recliner", 400.00);
        }
    }

    public static class Customer {
        private String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Booking {
        private Customer customer;
        private Show show;
        private List<Seat> bookedSeats;
        private double total;
        private boolean cancelled;

        public Booking(Customer customer, Show show, List<Seat> seats) {
            this.customer = customer;
            this.show = show;
            this.bookedSeats = new ArrayList<>(seats);
            this.total = 0;
            for (Seat s : seats) {
                total += s.getPrice();
            }
            this.cancelled = false;
        }

        public Customer getCustomer() {
            return customer;
        }

        public List<Seat> getBookedSeats() {
            return bookedSeats;
        }

        public double getTotal() {
            return total;
        }

        public boolean isCancelled() {
            return cancelled;
        }

        public void cancel() {
            this.cancelled = true;
        }
    }

    public static class Show {
        private String title;
        private String time;
        private Set<String> occupiedSeatNumbers;
        private List<Booking> activeBookings;

        public Show(String title, String time) {
            this.title = title;
            this.time = time;
            this.occupiedSeatNumbers = new HashSet<>();
            this.activeBookings = new ArrayList<>();
        }

        public Booking bookSeats(Customer customer, List<Seat> requestedSeats) {
            if (requestedSeats == null || requestedSeats.isEmpty() || requestedSeats.size() > 6) {
                System.out.println("Invalid booking: Must select between 1 and 6 seats.");
                return null;
            }

            for (Seat seat : requestedSeats) {
                if (occupiedSeatNumbers.contains(seat.getSeatNumber())) {
                    System.out.printf("Seat %s is already booked for this show.%n", seat.getSeatNumber());
                    return null;
                }
            }

            for (Seat seat : requestedSeats) {
                occupiedSeatNumbers.add(seat.getSeatNumber());
            }

            Booking booking = new Booking(customer, this, requestedSeats);
            activeBookings.add(booking);

            List<String> seatCodes = new ArrayList<>();
            for (Seat s : requestedSeats) {
                seatCodes.add(s.getSeatNumber());
            }

            System.out.printf("Booking confirmed for %s: %s. Total: \u20B9%.2f.%n",
                    customer.getName(), String.join(", ", seatCodes), booking.getTotal());
            return booking;
        }

        public void cancelBooking(Booking booking, boolean beforeShowStarts) {
            if (booking == null || booking.isCancelled()) {
                return;
            }

            if (!beforeShowStarts) {
                System.out.println("Cannot cancel booking after show has started.");
                return;
            }

            booking.cancel();
            List<String> releasedSeats = new ArrayList<>();
            for (Seat seat : booking.getBookedSeats()) {
                occupiedSeatNumbers.remove(seat.getSeatNumber());
                releasedSeats.add(seat.getSeatNumber());
            }

            System.out.printf("%s's booking cancelled. Seats %s released.%n",
                    booking.getCustomer().getName(), String.join(", ", releasedSeats));
        }
    }

    public static void main(String[] args) {
        Show eveningShow = new Show("Movie Premiere", "7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        // Asha books Regular seats A1 and A2 and Premium seat F5 for the 7 PM show
        List<Seat> ashaSeats = Arrays.asList(new RegularSeat("A1"), new RegularSeat("A2"), new PremiumSeat("F5"));
        Booking ashaBooking = eveningShow.bookSeats(asha, ashaSeats);

        // Ravi attempts to book seat A2 for the same show
        eveningShow.bookSeats(ravi, Collections.singletonList(new RegularSeat("A2")));

        // Ravi books Recliner seat R1 for the same show
        eveningShow.bookSeats(ravi, Collections.singletonList(new ReclinerSeat("R1")));

        // Asha cancels her booking before the show starts
        eveningShow.cancelBooking(ashaBooking, true);

        // Neha books seat A2 for the same show
        eveningShow.bookSeats(neha, Collections.singletonList(new RegularSeat("A2")));
    }
}
