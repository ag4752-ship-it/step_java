package session_eight_topics.assignment_problems;

/**
 * Session 8 - Assignment Problem 1: The Hostel Laundry Queue
 * 
 * Demonstrates:
 * - Polymorphic WashType hierarchy (QuickWash, NormalWash, HeavyWash) with duration and charges.
 * - WashingMachine state encapsulation preventing outside modification of availability.
 * - WashCycle workflow tracking and machine status transitions.
 */
public class HostelLaundryQueue {

    public interface WashType {
        String getTypeName();
        int getDurationMinutes();
        double getCharge();
    }

    public static class QuickWash implements WashType {
        @Override
        public String getTypeName() {
            return "Quick";
        }

        @Override
        public int getDurationMinutes() {
            return 30;
        }

        @Override
        public double getCharge() {
            return 20.00;
        }
    }

    public static class NormalWash implements WashType {
        @Override
        public String getTypeName() {
            return "Normal";
        }

        @Override
        public int getDurationMinutes() {
            return 45;
        }

        @Override
        public double getCharge() {
            return 30.00;
        }
    }

    public static class HeavyWash implements WashType {
        @Override
        public String getTypeName() {
            return "Heavy";
        }

        @Override
        public int getDurationMinutes() {
            return 60;
        }

        @Override
        public double getCharge() {
            return 45.00;
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

    public static class WashingMachine {
        private String machineId;
        private boolean busy;

        public WashingMachine(String machineId) {
            this.machineId = machineId;
            this.busy = false;
        }

        public String getMachineId() {
            return machineId;
        }

        public boolean isBusy() {
            return busy;
        }

        public void setBusy(boolean busy) {
            this.busy = busy;
        }
    }

    public static class WashCycle {
        private Student student;
        private WashingMachine machine;
        private WashType washType;
        private boolean completed;

        public WashCycle(Student student, WashingMachine machine, WashType washType) {
            this.student = student;
            this.machine = machine;
            this.washType = washType;
            this.completed = false;
        }

        public Student getStudent() {
            return student;
        }

        public WashingMachine getMachine() {
            return machine;
        }

        public WashType getWashType() {
            return washType;
        }

        public void complete() {
            this.completed = true;
            this.machine.setBusy(false);
            System.out.printf("%s cycle completed. %s is now free.%n",
                    machine.getMachineId(), machine.getMachineId());
        }
    }

    public static class LaundryService {
        public WashCycle startWash(Student student, WashingMachine machine, WashType washType) {
            if (machine.isBusy()) {
                System.out.printf("Machine %s is currently busy.%n", machine.getMachineId());
                return null;
            }

            machine.setBusy(true);
            WashCycle cycle = new WashCycle(student, machine, washType);
            System.out.printf("%s wash started on %s for %s (%d min). Charge: \u20B9%.2f.%n",
                    washType.getTypeName(), machine.getMachineId(), student.getName(),
                    washType.getDurationMinutes(), washType.getCharge());
            return cycle;
        }
    }

    public static void main(String[] args) {
        LaundryService service = new LaundryService();

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashType quick = new QuickWash();
        WashType normal = new NormalWash();
        WashType heavy = new HeavyWash();

        // Asha starts a Quick wash on Machine M1
        WashCycle cycle1 = service.startWash(asha, m1, quick);

        // Ravi attempts to start a Heavy wash on Machine M1
        service.startWash(ravi, m1, heavy);

        // Ravi starts a Heavy wash on Machine M2
        service.startWash(ravi, m2, heavy);

        // Machine M1 completes its cycle
        if (cycle1 != null) {
            cycle1.complete();
        }

        // Neha starts a Normal wash on Machine M1
        service.startWash(neha, m1, normal);
    }
}
