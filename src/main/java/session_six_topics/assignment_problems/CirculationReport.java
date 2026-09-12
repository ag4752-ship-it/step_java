package session_six_topics.assignment_problems;

public class CirculationReport {

    public static class LibraryMember {
        protected String memberId;
        protected int borrowLimit;
        protected int booksBorrowed;

        public LibraryMember(String memberId, int borrowLimit) {
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
            this.booksBorrowed = 0;
        }

        public String displayInfo() {
            return "General | Books: " + booksBorrowed;
        }
    }

    public static class StudentMember extends LibraryMember {
        private String course;

        public StudentMember(String memberId, int borrowLimit, String course) {
            super(memberId, borrowLimit);
            this.course = course;
        }

        public String getCourse() {
            return course;
        }

        @Override
        public String displayInfo() {
            return "Student | Course: " + course + " | Books: " + booksBorrowed;
        }
    }

    public static String batchPrint(LibraryMember[] members) {
        if (members == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (LibraryMember member : members) {
            if (member != null) {
                sb.append(member.displayInfo());
                if (member instanceof StudentMember) {
                    StudentMember sm = (StudentMember) member;
                    sb.append(" [Course via downcast: ").append(sm.getCourse()).append("]");
                }
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Test 1: Batch print combining polymorphic displayInfo and guarded downcast
        LibraryMember[] batch = {
            new LibraryMember("LB5", 3),
            new StudentMember("STU6", 3, "ECE")
        };
        System.out.println(batchPrint(batch));
        // "General | Books: 0 | Student | Course: ECE | Books: 0 [Course via downcast: ECE] | "

        // Test 2: Unguarded downcast fails with ClassCastException
        LibraryMember plain = new LibraryMember("LB6", 3);
        try {
            StudentMember bad = (StudentMember) plain;
            System.out.println("Downcast succeeded: " + bad);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime: " + e.getMessage());
        }
    }
}
