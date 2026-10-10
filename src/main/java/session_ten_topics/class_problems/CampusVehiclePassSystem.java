package session_ten_topics.class_problems;

import java.util.*;

/**
 * Session 10 - Class Problem 2: Campus Vehicle Pass System
 * 
 * Demonstrates:
 * - Vehicle hierarchy (Bike, Car, EBike, ECar).
 * - Chargeable interface for electric vehicles eligible for charging bays.
 * - Dynamic pass fee calculation and charging bay reservation validation.
 */
public class CampusVehiclePassSystem {

    public interface Chargeable {
        boolean canCharge();
    }

    public static abstract class Vehicle {
        protected String passNumber;
        protected String ownerName;
        protected String vehicleType;

        public Vehicle(String passNumber, String ownerName, String vehicleType) {
            this.passNumber = passNumber;
            this.ownerName = ownerName;
            this.vehicleType = vehicleType;
        }

        public String getPassNumber() {
            return passNumber;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public String getVehicleType() {
            return vehicleType;
        }

        public abstract int getPassFee();
    }

    public static class Bike extends Vehicle {
        public Bike(String passNumber, String ownerName) {
            super(passNumber, ownerName, "Bike");
        }

        @Override
        public int getPassFee() {
            return 300;
        }
    }

    public static class Car extends Vehicle {
        public Car(String passNumber, String ownerName) {
            super(passNumber, ownerName, "Car");
        }

        @Override
        public int getPassFee() {
            return 1000;
        }
    }

    public static class EBike extends Vehicle implements Chargeable {
        public EBike(String passNumber, String ownerName) {
            super(passNumber, ownerName, "EBike");
        }

        @Override
        public int getPassFee() {
            return 300;
        }

        @Override
        public boolean canCharge() {
            return true;
        }
    }

    public static class ECar extends Vehicle implements Chargeable {
        public ECar(String passNumber, String ownerName) {
            super(passNumber, ownerName, "ECar");
        }

        @Override
        public int getPassFee() {
            return 1000;
        }

        @Override
        public boolean canCharge() {
            return true;
        }
    }

    public static class VehiclePassManager {
        private Map<String, Vehicle> vehicles = new HashMap<>();

        public void processOperation(String line) {
            String[] tokens = line.trim().split("\\s+");
            if (tokens.length == 0 || tokens[0].isEmpty()) return;

            String command = tokens[0];
            if (command.equalsIgnoreCase("PASS")) {
                String type = tokens[1];
                String passNumber = tokens[2];
                String owner = tokens[3];

                Vehicle vehicle;
                if (type.equalsIgnoreCase("Bike")) {
                    vehicle = new Bike(passNumber, owner);
                } else if (type.equalsIgnoreCase("Car")) {
                    vehicle = new Car(passNumber, owner);
                } else if (type.equalsIgnoreCase("EBike") || type.equalsIgnoreCase("E-Bike")) {
                    vehicle = new EBike(passNumber, owner);
                } else if (type.equalsIgnoreCase("ECar") || type.equalsIgnoreCase("E-Car")) {
                    vehicle = new ECar(passNumber, owner);
                } else {
                    vehicle = new Bike(passNumber, owner);
                }

                vehicles.put(passNumber, vehicle);
                System.out.printf("%s (%s) pass fee %d%n", vehicle.getPassNumber(), vehicle.getVehicleType(), vehicle.getPassFee());
            } else if (command.equalsIgnoreCase("CHARGE")) {
                String passNumber = tokens[1];
                Vehicle vehicle = vehicles.get(passNumber);
                if (vehicle == null) {
                    System.out.printf("%s rejected: vehicle not registered%n", passNumber);
                } else if (vehicle instanceof Chargeable) {
                    System.out.printf("%s charging bay allotted%n", passNumber);
                } else {
                    System.out.printf("%s rejected: charging unsupported%n", passNumber);
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] operations = {
            "PASS Bike KA01 Asha",
            "PASS ECar KA02 Ravi",
            "CHARGE KA02",
            "CHARGE KA01"
        };

        VehiclePassManager manager = new VehiclePassManager();
        for (String op : operations) {
            manager.processOperation(op);
        }
    }
}
