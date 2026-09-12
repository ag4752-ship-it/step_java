package session_six_topics.assignment_problems;

public class GymTrialSignUpValidator {

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
    }

    public static class PremiumMember extends GymMember {
        private String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        public String getTrainerName() {
            return this.trainerName;
        }
    }

    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;

        if (memberIds != null) {
            for (String id : memberIds) {
                try {
                    new GymMember(id, monthlyFee);
                    signedUp++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        // Example 1: Construction rejection (GM1 is only 3 chars)
        try {
            new GymMember("GM1", 1000);
            System.out.println("Construction succeeded");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        // Example 2: PremiumMember creation and attending sessions
        PremiumMember p = new PremiumMember("MEM01", 2000, "Coach Riya");
        p.attendSession();
        p.attendSession();
        System.out.println("Sessions attended: " + p.getSessionsAttended()); // 2

        // Example 3: signUpBatch
        String[] batch = {"MEM1", "GM1", "MEM2", " ", "MEM3"};
        String result = signUpBatch(batch, 1000);
        System.out.println("Batch result: " + result); // "Signed Up: 3 | Rejected: 2"
    }
}
