package session_ten_topics.class_problems;

import java.util.*;

/**
 * Session 10 - Class Problem 3: Student Club Registration Management
 * 
 * Demonstrates:
 * - Proper override of equals() and hashCode() on business key (roll number).
 * - Set-based deduplication for registration.
 * - Membership querying and size reporting.
 */
public class StudentClubRegistrationManagement {

    public static class Student {
        private String rollNumber;
        private String name;

        public Student(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        public String getRollNumber() {
            return rollNumber;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return Objects.equals(rollNumber, student.rollNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(rollNumber);
        }
    }

    public static class ClubRegistrationManager {
        private Set<Student> members = new HashSet<>();
        private boolean reportedCount = false;

        public void processOperation(String line) {
            String[] tokens = line.trim().split("\\s+");
            if (tokens.length < 2) return;

            String command = tokens[0];
            String roll = tokens[1];
            String name = tokens.length > 2 ? tokens[2] : "";

            if (command.equalsIgnoreCase("ADD")) {
                Student s = new Student(roll, name);
                if (members.add(s)) {
                    System.out.println("Added");
                } else {
                    System.out.println("duplicate rejected");
                }
            } else if (command.equalsIgnoreCase("CONTAINS")) {
                if (!reportedCount) {
                    System.out.println("member count " + members.size());
                    reportedCount = true;
                }
                Student probe = new Student(roll, name);
                boolean contains = members.contains(probe);
                System.out.println("contains: " + contains);
            }
        }
    }

    public static void main(String[] args) {
        String[] operations = {
            "ADD 21CS01 Asha",
            "ADD 21CS01 Asha",
            "ADD 21CS02 Ravi",
            "CONTAINS 21CS02 Ravi",
            "CONTAINS 21CS03 Priya"
        };

        ClubRegistrationManager manager = new ClubRegistrationManager();
        for (String op : operations) {
            manager.processOperation(op);
        }
    }
}
