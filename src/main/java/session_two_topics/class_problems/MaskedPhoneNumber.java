package session_two_topics.class_problems;

public class MaskedPhoneNumber {

    public static String maskPhoneNumber(String phone) {
        if (phone == null) {
            return "Invalid phone number";
        }

        String cleaned = phone.trim();
        if (cleaned.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < cleaned.length(); i++) {
            if (!Character.isDigit(cleaned.charAt(i))) {
                return "Invalid phone number";
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("XXXXXX");
        sb.append("-");
        sb.append(cleaned.substring(6));

        return sb.toString();
    }

    public static void test(String phone) {
        System.out.printf("Input: %-15s -> Output: %s%n", phone, maskPhoneNumber(phone));
    }

    public static void main(String[] args) {
        test("9876543210");
        test("98765");
        test("987654321a");
        test("1234567890");
    }
}