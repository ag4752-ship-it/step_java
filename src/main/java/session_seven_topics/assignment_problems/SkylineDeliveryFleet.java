package session_seven_topics.assignment_problems;

public class SkylineDeliveryFleet {

    public static abstract class Drone {
        protected final String id;

        public Drone(String id) {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("ID cannot be blank");
            }
            this.id = id.trim();
        }

        public String getId() {
            return this.id;
        }

        public abstract String fly();
    }

    public interface Trackable {
        String getLocation();
    }

    public static class DeliveryDrone extends Drone implements Trackable {
        private String location;

        public DeliveryDrone(String id) {
            this(id, "Sector 4");
        }

        public DeliveryDrone(String id, String location) {
            super(id);
            this.location = (location != null && !location.trim().isEmpty()) ? location.trim() : "Sector 4";
        }

        @Override
        public String fly() {
            return "DeliveryDrone " + this.id + " flying to deliver package";
        }

        @Override
        public String getLocation() {
            return this.id + " at " + this.location;
        }
    }

    public static class ScoutDrone extends Drone {
        public ScoutDrone(String id) {
            super(id);
        }

        @Override
        public String fly() {
            return "ScoutDrone " + this.id + " flying for scouting";
        }
    }

    public static class GroundRobot implements Trackable {
        private final String id;
        private String location;

        public GroundRobot(String id) {
            this(id, "Sector 4");
        }

        public GroundRobot(String id, String location) {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("ID cannot be blank");
            }
            this.id = id.trim();
            this.location = (location != null && !location.trim().isEmpty()) ? location.trim() : "Sector 4";
        }

        public String getId() {
            return this.id;
        }

        @Override
        public String getLocation() {
            return this.id + " at " + this.location;
        }
    }

    public static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable trackable = (Trackable) o;
            return trackable.getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        DeliveryDrone d = new DeliveryDrone("DR-1");
        System.out.println(getLocationIfTrackable(d));

        ScoutDrone s = new ScoutDrone("SC-1");
        System.out.println(getLocationIfTrackable(s));

        GroundRobot g = new GroundRobot("GR-1");
        System.out.println(getLocationIfTrackable(g));
    }
}
