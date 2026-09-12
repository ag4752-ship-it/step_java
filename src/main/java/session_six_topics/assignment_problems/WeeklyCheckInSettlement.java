package session_six_topics.assignment_problems;

public class WeeklyCheckInSettlement {

    public static class GymMember {
        private static int counter = 2000;
        private static int membersEnrolled = 0;

        public final String membershipNumber;
        protected int monthlyFee;
        protected int feesPaid;
        protected String lastPaymentMode;

        public GymMember(int monthlyFee) {
            this.membershipNumber = "GYM-" + (++counter);
            membersEnrolled++;
            this.monthlyFee = monthlyFee;
            this.feesPaid = 0;
            this.lastPaymentMode = null;
        }

        public void payFee(int amount) {
            this.feesPaid += amount;
        }

        public void payFee(int amount, String mode) {
            this.lastPaymentMode = mode;
            payFee(amount);
        }

        public int getFeesPaid() {
            return this.feesPaid;
        }

        public String getLastPaymentMode() {
            return this.lastPaymentMode;
        }

        public static int getMembersEnrolled() {
            return membersEnrolled;
        }
    }

    public static class GroupClassMember extends GymMember {
        private String className;

        public GroupClassMember(int monthlyFee, String className) {
            super(monthlyFee);
            this.className = className;
        }

        public String getClassName() {
            return this.className;
        }
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'G') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }
        return true;
    }

    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (members != null) {
            for (GymMember member : members) {
                if (member == null) {
                    nullSkipped++;
                    continue;
                }
                processed++;
                if (member instanceof GroupClassMember) {
                    groupCount++;
                } else {
                    individualCount++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + groupCount + " group | " + individualCount + " individual";
    }

    public static void main(String[] args) {
        // Example 1: Static auto-increment membershipNumber and enrollment count
        GymMember m1 = new GymMember(1000);
        System.out.println("Membership Number: " + m1.membershipNumber); // "GYM-2001"
        System.out.println("Members Enrolled: " + GymMember.getMembersEnrolled()); // 1

        // Example 2: Referral code validation without regex
        System.out.println("isValidReferralCode(\"G45B\"): " + isValidReferralCode("G45B")); // true
        System.out.println("isValidReferralCode(\"G4B\"): " + isValidReferralCode("G4B"));   // false
        System.out.println("isValidReferralCode(\"X45B\"): " + isValidReferralCode("X45B")); // false

        // Example 3: Overloaded payFee delegation
        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println("Total Fees Paid: " + m1.getFeesPaid()); // 1000

        // Example 4: Weekly check-in batch processing with null safety
        GymMember[] weeklyBatch = {
            new GroupClassMember(1500, "Zumba"),
            null,
            new GymMember(1000)
        };
        String checkInSummary = processWeeklyCheckIn(weeklyBatch);
        System.out.println("Weekly Check-in Summary: " + checkInSummary);
        // "2 processed | 1 null skipped | 1 group | 1 individual"
    }
}
