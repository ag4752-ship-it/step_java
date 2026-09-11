package session_three_topics.assignment_problems;

public class EmployeeStatic {

    private String empName;
    private double salary;

    private static String companyName = "Bright Horizon Technologies";
    private static int employeeCount = 0;

    public EmployeeStatic(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {

        EmployeeStatic emp1 = new EmployeeStatic("Alice", 50000);
        EmployeeStatic emp2 = new EmployeeStatic("Bob", 60000);
        EmployeeStatic emp3 = new EmployeeStatic("Charlie", 70000);

        EmployeeStatic.printCompanyInfo();
    }
}