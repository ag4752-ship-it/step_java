public class AtmPinLengthValidator {

    public static void checkPinLength(String pin) {
        if (pin != null && pin.length() == 4) {
            System.out.println("PIN length OK.");
        } else {
            System.out.println("Invalid PIN \u2014 must be exactly 4 digits.");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1 ---");
        checkPinLength("482");

        System.out.println("\n--- Test Case 2 ---");
        checkPinLength("4820");
    }
}



