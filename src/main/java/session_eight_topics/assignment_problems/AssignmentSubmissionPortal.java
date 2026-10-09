package session_eight_topics.assignment_problems;

/**
 * Session 8 - Assignment Problem 2: The Assignment Submission Portal
 * 
 * Demonstrates:
 * - Abstract Assignment class with specialized penalty rules for Coding (-10%/day) and Written (-20%/day).
 * - Submission lifecycle (Submitted -> Graded) with immutable state guarding against resubmission.
 */
public class AssignmentSubmissionPortal {

    public static abstract class Assignment {
        protected String title;
        protected int maxMarks;
        protected int dueDay; // Day number in March

        public Assignment(String title, int maxMarks, int dueDay) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDay = dueDay;
        }

        public String getTitle() {
            return title;
        }

        public int getMaxMarks() {
            return maxMarks;
        }

        public int getDueDay() {
            return dueDay;
        }

        public abstract double calculateFinalMarks(double rawMarks, int lateDays);
        public abstract double getPenaltyPercentagePerDay();
    }

    public static class CodingAssignment extends Assignment {
        public CodingAssignment(String title, int maxMarks, int dueDay) {
            super(title, maxMarks, dueDay);
        }

        @Override
        public double calculateFinalMarks(double rawMarks, int lateDays) {
            if (lateDays <= 0) return rawMarks;
            double penaltyPercent = lateDays * 0.10;
            return rawMarks * (1.0 - penaltyPercent);
        }

        @Override
        public double getPenaltyPercentagePerDay() {
            return 10.0;
        }
    }

    public static class WrittenAssignment extends Assignment {
        public WrittenAssignment(String title, int maxMarks, int dueDay) {
            super(title, maxMarks, dueDay);
        }

        @Override
        public double calculateFinalMarks(double rawMarks, int lateDays) {
            if (lateDays <= 0) return rawMarks;
            double penaltyPercent = lateDays * 0.20;
            return rawMarks * (1.0 - penaltyPercent);
        }

        @Override
        public double getPenaltyPercentagePerDay() {
            return 20.0;
        }
    }

    public static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum SubmissionStatus {
        SUBMITTED,
        GRADED
    }

    public static class Submission {
        private Student student;
        private Assignment assignment;
        private int submissionDay;
        private int lateDays;
        private SubmissionStatus status;
        private double finalMarks;

        public Submission(Student student, Assignment assignment, int submissionDay) {
            this.student = student;
            this.assignment = assignment;
            this.submissionDay = submissionDay;
            this.lateDays = Math.max(0, submissionDay - assignment.getDueDay());
            this.status = SubmissionStatus.SUBMITTED;

            String lateInfo = (lateDays == 0) ? "(on time)" : String.format("(%d days late)", lateDays);
            System.out.printf("%s's submission for '%s' received %s. Status: Submitted.%n",
                    student.getName(), assignment.getTitle(), lateInfo);
        }

        public Student getStudent() {
            return student;
        }

        public Assignment getAssignment() {
            return assignment;
        }

        public SubmissionStatus getStatus() {
            return status;
        }

        public void grade(double awardedMarks) {
            this.status = SubmissionStatus.GRADED;
            this.finalMarks = assignment.calculateFinalMarks(awardedMarks, lateDays);

            if (lateDays == 0) {
                System.out.printf("%s graded: %d/%d. Status: Graded.%n",
                        student.getName(), (int) finalMarks, assignment.getMaxMarks());
            } else {
                int totalPenaltyPercent = (int) (lateDays * assignment.getPenaltyPercentagePerDay());
                System.out.printf("%s graded: %d/%d after %d%% late penalty. Status: Graded.%n",
                        student.getName(), (int) finalMarks, assignment.getMaxMarks(), totalPenaltyPercent);
            }
        }

        public boolean canResubmit() {
            if (status == SubmissionStatus.GRADED) {
                System.out.printf("Cannot resubmit: '%s' has already been graded.%n", assignment.getTitle());
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Assignment codingLab = new CodingAssignment("Linked List Lab", 50, 10);
        Assignment writtenEssay = new WrittenAssignment("Design Essay", 50, 12);

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        // Asha submits 'Linked List Lab' on Mar 10
        Submission ashaSub = new Submission(asha, codingLab, 10);

        // Ravi submits 'Design Essay' on Mar 14 (2 days late)
        Submission raviSub = new Submission(ravi, writtenEssay, 14);

        // Faculty awards Asha 45 marks
        ashaSub.grade(45);

        // Faculty awards Ravi 40 marks
        raviSub.grade(40);

        // Asha attempts to resubmit 'Linked List Lab'
        ashaSub.canResubmit();
    }
}
