package session_four_topics.assignment_problems;

public class MembershipCard {
    public static String libraryName;
    public static String validUntil;
    private String studentName;

    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";
        System.out.println("Library info loaded");
    }

    public MembershipCard(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public static void main(String[] args) {
        String[] names = {"Ananya", "Rohan", "Priya", "Arjun", "Sneha"};

        for (String name : names) {
            MembershipCard card = new MembershipCard(name);
            System.out.println("Membership card issued: " + card.getStudentName());
        }
    }
}


