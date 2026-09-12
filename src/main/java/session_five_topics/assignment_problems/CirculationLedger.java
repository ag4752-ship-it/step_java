package session_five_topics.assignment_problems;

import java.util.Arrays;

class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = (bookIds != null) ? bookIds.clone() : new String[0];
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return (bookIds != null) ? bookIds.clone() : new String[0];
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] updatedBookIds = (this.bookIds != null) ? this.bookIds.clone() : new String[0];
        if (index >= 0 && index < updatedBookIds.length) {
            updatedBookIds[index] = newId;
        }
        return new LoanReceipt(this.memberId, updatedBookIds);
    }

    @Override
    public String toString() {
        return "LoanReceipt{" +
                "memberId='" + memberId + '\'' +
                ", bookIds=" + Arrays.toString(bookIds) +
                '}';
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}

public class CirculationLedger {
    public static String branchCode;

    static {
        branchCode = "PAGE-TURNER-MAIN";
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int refOnlyCount = 0;
        int regularCount = 0;

        if (receipts != null) {
            for (LoanReceipt receipt : receipts) {
                if (receipt == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (receipt instanceof ReferenceOnlyLoanReceipt) {
                        refOnlyCount++;
                    } else {
                        regularCount++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + refOnlyCount + " reference-only | " + regularCount + " regular";
    }

    public static void main(String[] args) {
        // Test 1: Defensive copying verification
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println("After mutating returned array: " + r.getBookIds()[0]); // "BK-100"

        // Test 2: Wither method immutability
        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println("Original receipts: " + Arrays.toString(r.getBookIds()));             // ["BK-100", "BK-101"]
        System.out.println("Corrected receipts: " + Arrays.toString(corrected.getBookIds()));     // ["BK-100", "BK-102"]

        // Test 3: Nightly circulation ledger batch processing
        LoanReceipt[] batch = new LoanReceipt[]{
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println(processNightlyCirculation(batch));
        // "2 processed | 1 null skipped | 1 reference-only | 1 regular"
    }
}