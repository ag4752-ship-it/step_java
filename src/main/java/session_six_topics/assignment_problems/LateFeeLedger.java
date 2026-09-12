package session_six_topics.assignment_problems;

import java.util.Arrays;

public class LateFeeLedger {

    public static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        private int[] lateFeeHistory;
        private int feeCount;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
                throw new IllegalArgumentException("Invalid member ID: " + memberId);
            }
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
            this.lateFeeHistory = new int[10];
            this.feeCount = 0;
        }

        protected void chargeLateFee(int amount) {
            if (amount > 0 && feeCount < lateFeeHistory.length) {
                lateFeeHistory[feeCount++] = amount;
            }
        }

        public int[] getLateFeeHistory() {
            return Arrays.copyOf(this.lateFeeHistory, this.feeCount);
        }

        public int getTotalLateFees() {
            int total = 0;
            for (int i = 0; i < feeCount; i++) {
                total += lateFeeHistory[i];
            }
            return total;
        }

        public String getMemberId() {
            return this.memberId;
        }

        public int getMonthlyFee() {
            return this.monthlyFee;
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
        protected void chargeLateFee(int amount) {
            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args) {
        // Example 1: PremiumMember halves late fee
        PremiumMember p = new PremiumMember("MEM5", 2000, "Coach Riya");
        p.chargeLateFee(200);
        System.out.println("Total Late Fees: " + p.getTotalLateFees()); // 100

        // Example 2: Defensive copy verification
        int[] history = p.getLateFeeHistory();
        System.out.println("History before mutation: " + Arrays.toString(p.getLateFeeHistory())); // [100]
        history[0] = 999;
        System.out.println("History after mutating copy: " + Arrays.toString(p.getLateFeeHistory())); // [100]
    }
}
