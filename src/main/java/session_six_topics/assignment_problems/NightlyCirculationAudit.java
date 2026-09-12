package session_six_topics.assignment_problems;

public class NightlyCirculationAudit {

    public static class LibraryMember {
        private static int membersEnrolled = 0;

        public final String memberNumber;
        protected int borrowLimit;
        protected int booksBorrowed;
        protected String lastGenre;

        public LibraryMember(int borrowLimit) {
            membersEnrolled++;
            this.memberNumber = "LIB-" + (100 + membersEnrolled);
            this.borrowLimit = borrowLimit;
            this.booksBorrowed = 0;
        }

        public void borrowBook() {
            this.booksBorrowed++;
        }

        public void borrowBook(String genre) {
            this.lastGenre = genre;
            borrowBook();
        }

        public int getBooksBorrowed() {
            return booksBorrowed;
        }

        public static int getMembersEnrolled() {
            return membersEnrolled;
        }

        public static void resetCounter() {
            membersEnrolled = 0;
        }
    }

    public static class FacultyMember extends LibraryMember {
        private String department;

        public FacultyMember(int borrowLimit, String department) {
            super(borrowLimit);
            this.department = department;
        }

        public String getDepartment() {
            return department;
        }
    }

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'R') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }
        return true;
    }

    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int facultyCount = 0;
        int regularCount = 0;

        if (members != null) {
            for (LibraryMember member : members) {
                if (member == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (member instanceof FacultyMember) {
                        facultyCount++;
                    } else {
                        regularCount++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + facultyCount + " faculty | " + regularCount + " regular";
    }

    public static void main(String[] args) {
        // Test 1: Auto-assigned final memberNumber & static counter
        LibraryMember m1 = new LibraryMember(3);
        System.out.println("m1.memberNumber: " + m1.memberNumber); // "LIB-101"
        System.out.println("LibraryMember.getMembersEnrolled(): " + LibraryMember.getMembersEnrolled()); // 1

        // Test 2: Renewal code validation without regex
        System.out.println("isValidRenewalCode(\"R12A\"): " + isValidRenewalCode("R12A")); // true
        System.out.println("isValidRenewalCode(\"R1A\"): " + isValidRenewalCode("R1A"));   // false
        System.out.println("isValidRenewalCode(\"X12A\"): " + isValidRenewalCode("X12A")); // false

        // Test 3: Overloaded borrowBook method
        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println("m1.getBooksBorrowed(): " + m1.getBooksBorrowed()); // 2

        // Test 4: Nightly audit batch processing with null skipping and instanceof check
        LibraryMember[] batch = {
            new FacultyMember(5, "Physics"),
            null,
            new LibraryMember(3)
        };
        System.out.println(processNightlyAudit(batch));
        // "2 processed | 1 null skipped | 1 faculty | 1 regular"
    }
}
