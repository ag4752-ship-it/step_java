package session_six_topics.class_problems;

public class MembershipTree {

    public static class LibraryMember {
        protected String memberId;
        protected int borrowLimit;
        protected int booksBorrowed;

        public LibraryMember(String memberId, int borrowLimit) {
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
            this.booksBorrowed = 0;
        }

        public void borrowBook() {
            this.booksBorrowed++;
        }

        public int getBooksBorrowed() {
            return this.booksBorrowed;
        }

        public String displayInfo() {
            return "General Member | Books Borrowed: " + booksBorrowed;
        }
    }

    public static class StudentMember extends LibraryMember {
        protected String course;

        public StudentMember(String memberId, int borrowLimit, String course) {
            super(memberId, borrowLimit);
            this.course = course;
        }

        public String getCourse() {
            return course;
        }

        @Override
        public String displayInfo() {
            return "Student Member | Course: " + course + " | Books Borrowed: " + getBooksBorrowed();
        }
    }

    public static class HonorsStudentMember extends StudentMember {
        private int bonusLimit;

        public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
            super(memberId, borrowLimit, course);
            this.bonusLimit = bonusLimit;
        }

        public int getBonusLimit() {
            return bonusLimit;
        }

        @Override
        public String displayInfo() {
            return "Honors Student Member | Course: " + course + " | Bonus Limit: " + bonusLimit + " | Books Borrowed: " + getBooksBorrowed();
        }
    }

    public static class FacultyMember extends LibraryMember {
        private String department;

        public FacultyMember(String memberId, int borrowLimit, String department) {
            super(memberId, borrowLimit);
            this.department = department;
        }

        public String getDepartment() {
            return department;
        }

        @Override
        public String displayInfo() {
            return "Faculty Member | Department: " + department + " | Books Borrowed: " + getBooksBorrowed();
        }
    }

    public static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof StudentMember) {
            return "Single inheritance descendant";
        } else if (member != null) {
            return "General Member base class";
        }
        return "Unknown";
    }

    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        if (members != null) {
            for (LibraryMember member : members) {
                if (member != null) {
                    total += member.getBooksBorrowed();
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        // Test 1: Subclass displayInfo overrides
        LibraryMember generalMember = new LibraryMember("STU1", 3);
        StudentMember studentMember = new StudentMember("STU2", 3, "CSE");
        HonorsStudentMember honorsMember = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember facultyMember = new FacultyMember("STU4", 5, "Physics");

        System.out.println(generalMember.displayInfo());
        // "General Member | Books Borrowed: 0"
        System.out.println(studentMember.displayInfo());
        // "Student Member | Course: CSE | Books Borrowed: 0"
        System.out.println(honorsMember.displayInfo());
        // "Honors Student Member | Course: ECE | Bonus Limit: 2 | Books Borrowed: 0"
        System.out.println(facultyMember.displayInfo());
        // "Faculty Member | Department: Physics | Books Borrowed: 0"

        // Test 2: Generation classification using instanceof
        System.out.println(classifyGeneration(honorsMember));
        // "Multilevel descendant (3 generations deep)"
        System.out.println(classifyGeneration(facultyMember));
        // "Hierarchical sibling (independent branch)"

        // Test 3: Polymorphic book counting across mixed hierarchy
        studentMember.borrowBook();
        studentMember.borrowBook();
        honorsMember.borrowBook();
        facultyMember.borrowBook();
        facultyMember.borrowBook();
        facultyMember.borrowBook();

        LibraryMember[] batch = {studentMember, honorsMember, facultyMember};
        System.out.println(getTotalBooksBorrowed(batch)); // 6 (2 + 1 + 3)
    }
}
