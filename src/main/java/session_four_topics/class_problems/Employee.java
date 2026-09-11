package session_four_topics.class_problems;

public class Employee {
    String empId;
    double salary;

    public Employee(String empId, double salary) {
        this.empId = empId;
        this.salary = salary;
    }

    public void raiseSalary(double salary) {
        this.salary += salary;
    }

    public void printEmployee() {
        System.out.printf("%s | Final Salary: Rs %.1f%n", this.empId, this.salary);
    }

    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("E-101", 40000),
            new Employee("E-102", 55000),
            new Employee("E-103", 62000),
            new Employee("E-104", 48000)
        };

        double bonus = 5000;
        for (Employee emp : employees) {
            emp.raiseSalary(bonus);
            emp.printEmployee();
        }
    }
}