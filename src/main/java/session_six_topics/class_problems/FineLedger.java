package session_six_topics.class_problems;

import java.util.Arrays;

public class FineLedger {

    public static class LibraryMember {
        protected String memberId;
        protected int borrowLimit;
        private int[] fineHistory = new int[10];
        private int fineCount = 0;
        private int totalFine = 0;

        public LibraryMember(String memberId, int borrowLimit) {
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        protected void chargeFine(int amount) {
            if (fineCount < fineHistory.length) {
                fineHistory[fineCount++] = amount;
                totalFine += amount;
            }
        }

        public int[] getFineHistory() {
            return Arrays.copyOf(fineHistory, fineCount);
        }

        public int getTotalFine() {
            return totalFine;
        }
    }

    public static class StudentMember extends LibraryMember {

        public StudentMember(String memberId, int borrowLimit) {
            super(memberId, borrowLimit);
        }

        @Override
        protected void chargeFine(int amount) {
            super.chargeFine(amount / 2);
        }
    }

    public static void main(String[] args) {
        // Test 1: Student fine halved discount via super.chargeFine
        StudentMember s = new StudentMember("STU5", 3);
        s.chargeFine(100);
        System.out.println(s.getTotalFine()); // 50

        // Test 2: Defensive copy protection on getFineHistory()
        int[] history = s.getFineHistory();
        history[0] = 999;
        System.out.println(Arrays.toString(s.getFineHistory())); // [50]
    }
}
