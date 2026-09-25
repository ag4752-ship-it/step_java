package session_seven_topics.assignment_problems;

public class DigitalClassroomSetup {

    public static abstract class ClassroomDevice {
        protected final String assetTag;

        public ClassroomDevice(String assetTag) {
            if (assetTag == null || assetTag.trim().isEmpty()) {
                throw new IllegalArgumentException("Asset tag cannot be blank");
            }
            this.assetTag = assetTag.trim();
        }

        public String getAssetTag() {
            return this.assetTag;
        }

        public abstract String operate();
    }

    public interface Chargeable {
        String charge();
        String charge(int minutes);
    }

    public static class Tablet extends ClassroomDevice implements Chargeable {
        public Tablet(String assetTag) {
            super(assetTag);
        }

        @Override
        public String operate() {
            return "Tablet " + this.assetTag + " displaying lesson";
        }

        @Override
        public String charge() {
            return this.assetTag + " charging";
        }

        @Override
        public String charge(int minutes) {
            return this.assetTag + " charging for " + minutes + " minutes";
        }
    }

    public static void main(String[] args) {
        Tablet t = new Tablet("TAB-5");
        System.out.println(t.operate());
        System.out.println(t.charge());
        System.out.println(t.charge(30));
    }
}
