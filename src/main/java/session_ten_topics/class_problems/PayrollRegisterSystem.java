package session_ten_topics.class_problems;

import java.util.*;

/**
 * Session 10 - Class Problem 5: Payroll Register System
 * 
 * Demonstrates:
 * - Polymorphic staff payroll calculation (FullTime, PartTime, Intern).
 * - Payslip generation, total disbursement accumulation, and top earner tracking.
 */
public class PayrollRegisterSystem {

    public static abstract class Staff {
        protected String name;
        protected String type;

        public Staff(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public abstract int calculatePay();
    }

    public static class FullTimeStaff extends Staff {
        private int salary;

        public FullTimeStaff(String name, int salary) {
            super(name, "FullTime");
            this.salary = salary;
        }

        @Override
        public int calculatePay() {
            return salary;
        }
    }

    public static class PartTimeStaff extends Staff {
        private int hours;
        private int rate;

        public PartTimeStaff(String name, int hours, int rate) {
            super(name, "PartTime");
            this.hours = hours;
            this.rate = rate;
        }

        @Override
        public int calculatePay() {
            return hours * rate;
        }
    }

    public static class InternStaff extends Staff {
        private int stipend;

        public InternStaff(String name, int stipend) {
            super(name, "Intern");
            this.stipend = stipend;
        }

        @Override
        public int calculatePay() {
            return stipend;
        }
    }

    public static class Payslip {
        private String name;
        private String type;
        private int pay;

        public Payslip(String name, String type, int pay) {
            this.name = name;
            this.type = type;
            this.pay = pay;
        }

        public String getName() {
            return name;
        }

        public int getPay() {
            return pay;
        }

        @Override
        public String toString() {
            return String.format("Payslip[name=%s, type=%s, pay=%d]", name, type, pay);
        }
    }

    public static void processPayroll(List<String> inputLines) {
        List<Staff> staffList = new ArrayList<>();

        for (String line : inputLines) {
            String[] tokens = line.trim().split("\\s+");
            if (tokens.length < 3) continue;

            String type = tokens[0];
            String name = tokens[1];

            if (type.equalsIgnoreCase("FullTime")) {
                int salary = Integer.parseInt(tokens[2]);
                staffList.add(new FullTimeStaff(name, salary));
            } else if (type.equalsIgnoreCase("PartTime")) {
                int hours = Integer.parseInt(tokens[2]);
                int rate = Integer.parseInt(tokens[3]);
                staffList.add(new PartTimeStaff(name, hours, rate));
            } else if (type.equalsIgnoreCase("Intern")) {
                int stipend = Integer.parseInt(tokens[2]);
                staffList.add(new InternStaff(name, stipend));
            }
        }

        Payslip[] payslips = new Payslip[staffList.size()];
        int totalPay = 0;
        String topEarner = "";
        int maxPay = -1;

        for (int i = 0; i < staffList.size(); i++) {
            Staff s = staffList.get(i);
            int pay = s.calculatePay();
            payslips[i] = new Payslip(s.getName(), s.getType(), pay);
            totalPay += pay;

            if (pay > maxPay) {
                maxPay = pay;
                topEarner = s.getName();
            }
        }

        for (Payslip p : payslips) {
            System.out.println(p);
        }

        System.out.println("Total " + totalPay);
        System.out.println("top earner " + topEarner);
    }

    public static void main(String[] args) {
        List<String> input = Arrays.asList(
            "FullTime Asha 50000",
            "PartTime Ravi 80 300",
            "Intern Neha 15000"
        );

        processPayroll(input);
    }
}
