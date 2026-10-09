package session_eight_topics.assignment_problems;

/**
 * Session 8 - Assignment Problem 4: The FitZone Membership Desk
 * 
 * Demonstrates:
 * - MembershipPlan interface with polymorphic discount calculations:
 *   Monthly (1 month, base ₹1,000), Quarterly (3 months, 10% off = ₹2,700), Annual (12 months, 25% off = ₹9,000).
 * - Membership lifecycle states: Active, Frozen, Expired.
 * - Strict transition controls preventing check-ins or freezing on Expired memberships.
 */
public class FitZoneMembershipDesk {

    public interface MembershipPlan {
        String getPlanName();
        int getDurationMonths();
        double calculateFee();
    }

    public static class MonthlyPlan implements MembershipPlan {
        private static final double BASE_RATE_PER_MONTH = 1000.0;

        @Override
        public String getPlanName() {
            return "Monthly";
        }

        @Override
        public int getDurationMonths() {
            return 1;
        }

        @Override
        public double calculateFee() {
            return BASE_RATE_PER_MONTH * 1;
        }
    }

    public static class QuarterlyPlan implements MembershipPlan {
        private static final double BASE_RATE_PER_MONTH = 1000.0;

        @Override
        public String getPlanName() {
            return "Quarterly";
        }

        @Override
        public int getDurationMonths() {
            return 3;
        }

        @Override
        public double calculateFee() {
            return (BASE_RATE_PER_MONTH * 3) * 0.90; // 10% off
        }
    }

    public static class AnnualPlan implements MembershipPlan {
        private static final double BASE_RATE_PER_MONTH = 1000.0;

        @Override
        public String getPlanName() {
            return "Annual";
        }

        @Override
        public int getDurationMonths() {
            return 12;
        }

        @Override
        public double calculateFee() {
            return (BASE_RATE_PER_MONTH * 12) * 0.75; // 25% off
        }
    }

    public static class Member {
        private String name;

        public Member(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum MembershipStatus {
        ACTIVE,
        FROZEN,
        EXPIRED
    }

    public static class Membership {
        private Member member;
        private MembershipPlan plan;
        private MembershipStatus status;

        public Membership(Member member, MembershipPlan plan) {
            this.member = member;
            this.plan = plan;
            this.status = MembershipStatus.ACTIVE;
            System.out.printf("%s membership created for %s. Fee: \u20B9%,.2f. Status: %s.%n",
                    plan.getPlanName(), member.getName(), plan.calculateFee(), getStatusString());
        }

        public Member getMember() {
            return member;
        }

        public MembershipStatus getStatus() {
            return status;
        }

        public String getStatusString() {
            switch (status) {
                case ACTIVE: return "Active";
                case FROZEN: return "Frozen";
                case EXPIRED: return "Expired";
                default: return "";
            }
        }

        public void checkIn() {
            if (status == MembershipStatus.ACTIVE) {
                System.out.printf("%s checked in successfully.%n", member.getName());
            } else {
                System.out.printf("Check-in denied: %s's membership is %s.%n",
                        member.getName(), getStatusString());
            }
        }

        public void freeze() {
            if (status == MembershipStatus.EXPIRED) {
                System.out.println("Cannot freeze an Expired membership.");
                return;
            }
            this.status = MembershipStatus.FROZEN;
            System.out.printf("%s's membership frozen. Status: %s.%n",
                    member.getName(), getStatusString());
        }

        public void unfreeze() {
            if (status == MembershipStatus.EXPIRED) {
                System.out.println("Cannot unfreeze an Expired membership.");
                return;
            }
            this.status = MembershipStatus.ACTIVE;
            System.out.printf("%s's membership unfrozen. Status: %s.%n",
                    member.getName(), getStatusString());
        }

        public void expire() {
            this.status = MembershipStatus.EXPIRED;
            System.out.printf("%s's membership expired. Status: %s.%n",
                    member.getName(), getStatusString());
        }
    }

    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        // Asha buys a Quarterly membership
        Membership ashaMembership = new Membership(asha, new QuarterlyPlan());

        // Ravi buys a Monthly membership
        Membership raviMembership = new Membership(ravi, new MonthlyPlan());

        // Asha checks in
        ashaMembership.checkIn();

        // Asha freezes her membership
        ashaMembership.freeze();

        // Asha attempts to check in
        ashaMembership.checkIn();

        // Ravi's membership expires
        raviMembership.expire();

        // Ravi attempts to freeze his membership
        raviMembership.freeze();
    }
}
