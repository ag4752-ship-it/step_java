package session_eight_topics.class_problems;

/**
 * Session 8 - Class Problem 2: Employee Leave Request Workflow
 * 
 * Demonstrates:
 * - Leave request state management (Pending, Approved, Rejected).
 * - Guarded state transitions preventing reverting from terminal states.
 * - Polymorphic Employee types and Leave Policy workflows.
 */
public class EmployeeLeaveRequestWorkflow {

    public enum LeaveStatus {
        PENDING,
        APPROVED,
        REJECTED
    }

    public static abstract class Employee {
        protected String id;
        protected String name;

        public Employee(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public abstract String getRole();
    }

    public static class FullTimeEmployee extends Employee {
        public FullTimeEmployee(String id, String name) {
            super(id, name);
        }

        @Override
        public String getRole() {
            return "Full-Time";
        }
    }

    public static class PartTimeEmployee extends Employee {
        public PartTimeEmployee(String id, String name) {
            super(id, name);
        }

        @Override
        public String getRole() {
            return "Part-Time";
        }
    }

    public static class Contractor extends Employee {
        public Contractor(String id, String name) {
            super(id, name);
        }

        @Override
        public String getRole() {
            return "Contractor";
        }
    }

    public static class Manager {
        private String name;

        public Manager(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void reviewLeaveRequest(LeaveRequest request, boolean approve) {
            if (approve) {
                request.approve();
            } else {
                request.reject();
            }
        }
    }

    public static class LeaveRequest {
        private Employee employee;
        private String dateRange;
        private int days;
        private LeaveStatus status;

        public LeaveRequest(Employee employee, int days, String dateRange) {
            this.employee = employee;
            this.days = days;
            this.dateRange = dateRange;
            this.status = LeaveStatus.PENDING;
            System.out.printf("Leave request submitted for %s (%s). Status: %s.%n",
                    employee.getName(), dateRange, getStatusString());
        }

        public Employee getEmployee() {
            return employee;
        }

        public String getDateRange() {
            return dateRange;
        }

        public LeaveStatus getStatus() {
            return status;
        }

        public String getStatusString() {
            switch (status) {
                case APPROVED: return "Approved";
                case REJECTED: return "Rejected";
                default: return "Pending";
            }
        }

        public void approve() {
            this.status = LeaveStatus.APPROVED;
            System.out.printf("%s's leave request (%s) approved. Status: %s.%n",
                    employee.getName(), dateRange, getStatusString());
        }

        public void reject() {
            this.status = LeaveStatus.REJECTED;
            System.out.printf("%s's leave request (%s) rejected. Status: %s.%n",
                    employee.getName(), dateRange, getStatusString());
        }

        public void changeStatus(LeaveStatus newStatus) {
            if (this.status != LeaveStatus.PENDING && newStatus == LeaveStatus.PENDING) {
                System.out.printf("Cannot change leave request status from %s to Pending.%n",
                        getStatusString());
                return;
            }
            this.status = newStatus;
        }
    }

    public static void main(String[] args) {
        // FullTimeEmployee John submits leave request for 5 days (Jan 1-5)
        Employee john = new FullTimeEmployee("E1", "John");
        LeaveRequest johnRequest = new LeaveRequest(john, 5, "Jan 1-5");

        // Manager Alice reviews John's request and approves it
        Manager alice = new Manager("Alice");
        alice.reviewLeaveRequest(johnRequest, true);

        // PartTimeEmployee Jane submits leave request for 2 days (Feb 10-11)
        Employee jane = new PartTimeEmployee("E2", "Jane");
        LeaveRequest janeRequest = new LeaveRequest(jane, 2, "Feb 10-11");

        // Manager Bob reviews Jane's request and rejects it
        Manager bob = new Manager("Bob");
        bob.reviewLeaveRequest(janeRequest, false);

        // John attempts to change his approved leave request to Pending
        johnRequest.changeStatus(LeaveStatus.PENDING);
    }
}
