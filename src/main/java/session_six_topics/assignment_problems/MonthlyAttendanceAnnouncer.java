package session_six_topics.assignment_problems;

public class MonthlyAttendanceAnnouncer {

    public static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid member ID: " + memberId);
            }
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            this.sessionsAttended = 0;
        }

        public void attendSession() {
            this.sessionsAttended++;
        }

        public int getSessionsAttended() {
            return this.sessionsAttended;
        }

        public String getMemberId() {
            return this.memberId;
        }

        public int getMonthlyFee() {
            return this.monthlyFee;
        }

        public String displayInfo() {
            return "Standard | Sessions: " + this.sessionsAttended;
        }
    }

    public static class PremiumMember extends GymMember {
        protected String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        public String getTrainerName() {
            return this.trainerName;
        }

        @Override
        public String displayInfo() {
            return "Premium | Trainer: " + this.trainerName + " | Sessions: " + this.sessionsAttended;
        }
    }

    public static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (GymMember member : members) {
                // Call displayInfo() polymorphically
                sb.append(member.displayInfo());

                // Guarded downcast using instanceof
                if (member instanceof PremiumMember) {
                    PremiumMember pm = (PremiumMember) member;
                    sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
                }
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Example 1: batchPrint polymorphic output
        GymMember[] list = {
            new GymMember("MEM6", 1000),
            new PremiumMember("MEM7", 2000, "Coach Riya")
        };
        String announcement = batchPrint(list);
        System.out.println("Announcement: \"" + announcement + "\"");

        // Example 2: Unguarded downcast fails with ClassCastException
        GymMember plain = new GymMember("MEM8", 1000);
        try {
            @SuppressWarnings("unused")
            PremiumMember bad = (PremiumMember) plain;
        } catch (ClassCastException e) {
            System.out.println("ClassCastException caught as expected for invalid downcast");
        }
    }
}
