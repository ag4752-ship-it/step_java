package session_three_topics.class_problems;

public class Student {
    String name;
    int attendance;
    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    public Student(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }

    public void displayDetails() {
        System.out.println("Student Name: " + name + ", Attendance: " + attendance + "%");
    }

    public static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }

    public static void main(String[] args) {
        Student s1 = new Student("Ananya", 85);
        Student s2 = new Student("Rohan", 92);

        s1.displayDetails();
        s2.displayDetails();

        System.out.println("2 Student objects created");
        Student.printCollegeInfo();
    }
}