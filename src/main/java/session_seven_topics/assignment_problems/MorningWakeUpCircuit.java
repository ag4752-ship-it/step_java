package session_seven_topics.assignment_problems;

public class MorningWakeUpCircuit {

    public interface Ringable {
        String ring();
    }

    public static class AlarmClock implements Ringable {
        private final String time;

        public AlarmClock(String time) {
            if (time == null || time.trim().isEmpty()) {
                throw new IllegalArgumentException("Time cannot be blank");
            }
            this.time = time.trim();
        }

        public String getTime() {
            return time;
        }

        @Override
        public String ring() {
            return "Alarm ringing for " + this.time;
        }
    }

    public static class Doorbell implements Ringable {
        private final String location;

        public Doorbell(String location) {
            if (location == null || location.trim().isEmpty()) {
                throw new IllegalArgumentException("Location cannot be blank");
            }
            this.location = location.trim();
        }

        public String getLocation() {
            return location;
        }

        @Override
        public String ring() {
            return "Doorbell ringing at " + this.location;
        }
    }

    public static void ringAll(Ringable[] devices) {
        if (devices != null) {
            for (Ringable device : devices) {
                if (device != null) {
                    System.out.println(device.ring());
                }
            }
        }
    }

    public static void main(String[] args) {
        AlarmClock a = new AlarmClock("7:00 AM");
        System.out.println(a.ring());

        Doorbell d = new Doorbell("Front Door");
        System.out.println(d.ring());

        System.out.println("\n--- ringAll Output ---");
        ringAll(new Ringable[]{a, d});
    }
}
