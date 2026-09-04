public class SeatDuplicationChecker {

    public static void checkDuplicateSeats(int[] seatNumbers) {
        if (seatNumbers == null || seatNumbers.length == 0) {
            System.out.println("No seats provided.");
            return;
        }

        boolean duplicateFound = false;

        int[] reported = new int[seatNumbers.length];
        int reportedCount = 0;

        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {

                    boolean alreadyReported = false;
                    for (int k = 0; k < reportedCount; k++) {
                        if (reported[k] == seatNumbers[i]) {
                            alreadyReported = true;
                            break;
                        }
                    }

                    if (!alreadyReported) {
                        System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                        reported[reportedCount++] = seatNumbers[i];
                    }
                    duplicateFound = true;
                }
            }
        }

        if (!duplicateFound) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1 ---");
        int[] input1 = { 101, 102, 103, 102, 105 };
        checkDuplicateSeats(input1);

        System.out.println("\n--- Test Case 2 ---");
        int[] input2 = { 101, 102, 103, 104, 105 };
        checkDuplicateSeats(input2);
    }
}
