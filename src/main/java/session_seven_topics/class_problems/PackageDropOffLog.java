package session_seven_topics.class_problems;

public class PackageDropOffLog {

    public static abstract class DeliveryNote {
        protected final String trackingId;

        public DeliveryNote(String trackingId) {
            if (trackingId == null || trackingId.trim().isEmpty()) {
                throw new IllegalArgumentException("Tracking ID cannot be blank");
            }
            this.trackingId = trackingId.trim();
        }

        public String getTrackingId() {
            return this.trackingId;
        }

        public abstract String confirmDelivery();

        public String confirmDelivery(String signature) {
            return confirmDelivery() + ", signed by " + signature;
        }
    }

    public static class ParcelNote extends DeliveryNote {
        public ParcelNote(String trackingId) {
            super(trackingId);
        }

        @Override
        public String confirmDelivery() {
            return "Parcel " + this.trackingId + " delivered";
        }
    }

    public static class LetterNote extends DeliveryNote {
        public LetterNote(String trackingId) {
            super(trackingId);
        }

        @Override
        public String confirmDelivery() {
            return "Letter " + this.trackingId + " delivered";
        }
    }

    public static void logAll(DeliveryNote[] notes) {
        if (notes != null) {
            for (DeliveryNote note : notes) {
                if (note != null) {
                    System.out.println(note.confirmDelivery());
                }
            }
        }
    }

    public static void main(String[] args) {
        ParcelNote p = new ParcelNote("TRK-1");
        System.out.println(p.confirmDelivery());
        System.out.println(p.confirmDelivery("J. Smith"));

        System.out.println("\n--- logAll Output ---");
        DeliveryNote ref = p; // upcasting: ParcelNote stored as its parent type
        logAll(new DeliveryNote[]{ref, new LetterNote("TRK-2")});
    }
}
