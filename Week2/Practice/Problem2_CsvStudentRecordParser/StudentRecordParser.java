public class StudentRecordParser {

    public static void parseStudentRecord(String csvLine) {
        if (csvLine == null) {
            System.out.println("Invalid Record");
            return;
        }

        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String name = fields[0].trim();
        String rollNo = fields[1].trim();
        String dept = fields[2].trim();

        if (name.isEmpty() || rollNo.isEmpty() || dept.isEmpty()) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.printf("Name: %s | Roll No: %s | Dept: %s%n", name, rollNo, dept);
    }

    public static void main(String[] args) {
        System.out.println("--- Test 1 ---");
        parseStudentRecord("Ananya Verma,RA2211003010123,CSE");

        System.out.println("\n--- Test 2 ---");
        parseStudentRecord("Ananya Verma,CSE");

        System.out.println("\n--- Test 3 ---");
        parseStudentRecord("Rohan Sharma,RA2211003010456,ECE");
    }
}
