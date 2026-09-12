package session_five_topics.class_problems;

import java.util.Arrays;

public class SettlementProcessor {

    public static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (receipts != null) {
            for (BookingReceipt receipt : receipts) {
                if (receipt == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (receipt instanceof GroupBookingReceipt) {
                        groupCount++;
                    } else {
                        individualCount++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + groupCount + " group | " + individualCount + " individual";
    }

    public static void main(String[] args) {
        // Test 1: Defensive copying on getSeatNumbers
        BookingReceipt b = new BookingReceipt("CH-1001", new String[]{"A1", "A2"});
        String[] seats = b.getSeatNumbers();
        seats[0] = "X";
        System.out.println("After attempting array mutation: " + b.getSeatNumbers()[0]); // "A1"

        // Test 2: Wither method immutability
        BookingReceipt updated = b.withUpdatedSeat(1, "A3");
        System.out.println("Original seats: " + Arrays.toString(b.getSeatNumbers()));       // ["A1", "A2"]
        System.out.println("Updated seats: " + Arrays.toString(updated.getSeatNumbers())); // ["A1", "A3"]

        // Test 3: Settlement processing with instanceof and null safety
        BookingReceipt[] batch = new BookingReceipt[]{
            new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
            null,
            new BookingReceipt("CH-3003", new String[]{"C1"})
        };
        System.out.println(processNightlySettlement(batch));
        // "2 processed | 1 null skipped | 1 group | 1 individual"
    }
}