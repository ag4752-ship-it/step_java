package session_five_topics.assignment_problems;

import java.util.Arrays;

public class LoanReceipt {
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