package session_eight_topics.class_problems;

/**
 * Session 8 - Class Problem 1: Vehicle Rental System
 * 
 * Demonstrates:
 * - Abstract Vehicle base class with polymorphic rental charge calculation.
 * - Vehicle categories: Sedan, SUV, Truck.
 * - Customer, Rental workflows, and vehicle availability tracking.
 */
public class VehicleRentalSystem {

    public static abstract class Vehicle {
        protected String id;
        protected String name;
        protected boolean isAvailable;

        public Vehicle(String id, String name) {
            this.id = id;
            this.name = name;
            this.isAvailable = true;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean available) {
            isAvailable = available;
        }

        public abstract double calculateRentalCharge(int days);
    }

    public static class Sedan extends Vehicle {
        private static final double DAILY_RATE = 50.0;

        public Sedan(String id, String name) {
            super(id, name);
        }

        @Override
        public double calculateRentalCharge(int days) {
            return DAILY_RATE * days;
        }
    }

    public static class SUV extends Vehicle {
        private static final double DAILY_RATE = 80.0;

        public SUV(String id, String name) {
            super(id, name);
        }

        @Override
        public double calculateRentalCharge(int days) {
            return DAILY_RATE * days;
        }
    }

    public static class Truck extends Vehicle {
        private static final double DAILY_RATE = 100.0;

        public Truck(String id, String name) {
            super(id, name);
        }

        @Override
        public double calculateRentalCharge(int days) {
            return DAILY_RATE * days;
        }
    }

    public static class Customer {
        private String customerId;
        private String name;

        public Customer(String customerId, String name) {
            this.customerId = customerId;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getCustomerId() {
            return customerId;
        }
    }

    public static class Rental {
        private Customer customer;
        private Vehicle vehicle;
        private int days;
        private double totalCharge;
        private boolean active;

        public Rental(Customer customer, Vehicle vehicle, int days) {
            this.customer = customer;
            this.vehicle = vehicle;
            this.days = days;
            this.totalCharge = vehicle.calculateRentalCharge(days);
            this.active = true;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public double getTotalCharge() {
            return totalCharge;
        }

        public boolean isActive() {
            return active;
        }

        public void completeRental() {
            this.active = false;
            this.vehicle.setAvailable(true);
        }
    }

    public static class RentalService {
        public Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {
            if (!vehicle.isAvailable()) {
                System.out.println(vehicle.getName() + " is currently unavailable.");
                return null;
            }

            vehicle.setAvailable(false);
            Rental rental = new Rental(customer, vehicle, days);
            System.out.printf("%s rented successfully by %s. Rental charge: $%.2f.%n",
                    vehicle.getName(), customer.getName(), rental.getTotalCharge());
            return rental;
        }

        public void returnVehicle(Rental rental) {
            if (rental != null && rental.isActive()) {
                rental.completeRental();
                System.out.printf("%s returned by %s.%n",
                        rental.getVehicle().getName(), rental.getCustomer().getName());
            }
        }
    }

    public static void main(String[] args) {
        RentalService service = new RentalService();

        Customer customer1 = new Customer("C1", "Customer 1");
        Customer customer2 = new Customer("C2", "Customer 2");
        Customer customer3 = new Customer("C3", "Customer 3");

        Vehicle sedanA = new Sedan("V1", "Sedan A");
        Vehicle suvB = new SUV("V2", "SUV B");

        // Customer 1 rents Sedan A for 3 days
        Rental rental1 = service.rentVehicle(customer1, sedanA, 3);

        // Customer 2 attempts to rent Sedan A for 2 days (while it's rented by Customer 1)
        service.rentVehicle(customer2, sedanA, 2);

        // Customer 1 returns Sedan A
        service.returnVehicle(rental1);

        // Customer 3 rents SUV B for 5 days
        service.rentVehicle(customer3, suvB, 5);
    }
}
