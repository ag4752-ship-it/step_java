package session_eight_topics.class_problems;

import java.util.*;

/**
 * Session 8 - Class Problem 4: Hotel Booking System
 * 
 * Demonstrates:
 * - Abstract Room class with polymorphic pricing rules (StandardRoom, DeluxeRoom, Suite).
 * - Multi-day reservation management and date-range overlap conflict detection.
 * - Reservation cancellation and availability release workflow.
 */
public class HotelBookingSystem {

    public static abstract class Room {
        protected String roomNumber;
        protected String categoryName;
        protected double pricePerNight;

        public Room(String roomNumber, String categoryName, double pricePerNight) {
            this.roomNumber = roomNumber;
            this.categoryName = categoryName;
            this.pricePerNight = pricePerNight;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public String getDisplayName() {
            return categoryName + " " + roomNumber;
        }

        public abstract double calculatePrice(int nights);
    }

    public static class StandardRoom extends Room {
        public StandardRoom(String roomNumber, double pricePerNight) {
            super(roomNumber, "Standard Room", pricePerNight);
        }

        @Override
        public double calculatePrice(int nights) {
            return pricePerNight * nights;
        }
    }

    public static class DeluxeRoom extends Room {
        public DeluxeRoom(String roomNumber, double pricePerNight) {
            super(roomNumber, "Deluxe Room", pricePerNight);
        }

        @Override
        public double calculatePrice(int nights) {
            return pricePerNight * nights;
        }
    }

    public static class Suite extends Room {
        public Suite(String roomNumber, double pricePerNight) {
            super(roomNumber, "Suite", pricePerNight);
        }

        @Override
        public double calculatePrice(int nights) {
            return pricePerNight * nights;
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

    public static class Reservation {
        private Customer customer;
        private Room room;
        private int startDay;
        private int endDay;
        private String dateRange;
        private double price;
        private boolean active;

        public Reservation(Customer customer, Room room, int startDay, int endDay, String dateRange, int nights) {
            this.customer = customer;
            this.room = room;
            this.startDay = startDay;
            this.endDay = endDay;
            this.dateRange = dateRange;
            this.price = room.calculatePrice(nights);
            this.active = true;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Room getRoom() {
            return room;
        }

        public int getStartDay() {
            return startDay;
        }

        public int getEndDay() {
            return endDay;
        }

        public String getDateRange() {
            return dateRange;
        }

        public double getPrice() {
            return price;
        }

        public boolean isActive() {
            return active;
        }

        public void cancel() {
            this.active = false;
        }

        public boolean overlapsWith(int start, int end) {
            if (!active) return false;
            return Math.max(this.startDay, start) < Math.min(this.endDay, end);
        }
    }

    public static class Hotel {
        private List<Reservation> reservations = new ArrayList<>();

        public boolean isAvailable(Room room, int startDay, int endDay, String dateRange) {
            for (Reservation r : reservations) {
                if (r.isActive() && r.getRoom().getRoomNumber().equals(room.getRoomNumber())) {
                    if (r.overlapsWith(startDay, endDay)) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void checkAvailability(Room room, int startDay, int endDay, String dateRange) {
            if (isAvailable(room, startDay, endDay, dateRange)) {
                System.out.printf("%s is available from %s.%n", room.getDisplayName(), dateRange);
            } else {
                System.out.printf("%s is not available from %s.%n", room.getDisplayName(), dateRange);
            }
        }

        public Reservation reserveRoom(Customer customer, Room room, int startDay, int endDay, String dateRange, int nights) {
            if (!isAvailable(room, startDay, endDay, dateRange)) {
                System.out.printf("%s is not available from %s.%n", room.getDisplayName(), dateRange);
                return null;
            }

            Reservation reservation = new Reservation(customer, room, startDay, endDay, dateRange, nights);
            reservations.add(reservation);
            System.out.printf("Reservation confirmed for %s, %s (%s). Price: $%.2f.%n",
                    customer.getName(), room.getDisplayName(), dateRange, reservation.getPrice());
            return reservation;
        }

        public void cancelReservation(Reservation reservation) {
            if (reservation != null && reservation.isActive()) {
                reservation.cancel();
                System.out.printf("Reservation for %s, %s (%s) cancelled successfully.%n",
                        reservation.getCustomer().getName(),
                        reservation.getRoom().getDisplayName(),
                        reservation.getDateRange());
            }
        }
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");
        Customer customerC = new Customer("Customer C");

        Room room101 = new StandardRoom("101", 100.0);
        Room room201 = new DeluxeRoom("201", 150.0);

        // Customer A checks availability for Standard Room 101 from Jan 1 to Jan 5
        hotel.checkAvailability(room101, 1, 5, "Jan 1 to Jan 5");

        // Customer A reserves Standard Room 101 from Jan 1 to Jan 5 (4 nights)
        Reservation resA = hotel.reserveRoom(customerA, room101, 1, 5, "Jan 1-5", 4);

        // Customer B attempts to reserve Standard Room 101 from Jan 3 to Jan 7
        hotel.reserveRoom(customerB, room101, 3, 7, "Jan 3 to Jan 7", 4);

        // Customer A cancels reservation for Standard Room 101 for Jan 1-5
        hotel.cancelReservation(resA);

        // Customer C reserves Deluxe Room 201 from Feb 10 to Feb 12 (2 nights)
        hotel.reserveRoom(customerC, room201, 41, 43, "Feb 10-12", 2);
    }
}
