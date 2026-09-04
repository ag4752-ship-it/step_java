public class BankTransactionValidator {

    public static String normalizeReference(String raw) {
        if (raw == null) return null;
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public static String validateAndFormat(String reference) {
        if (reference == null) {
            return "Invalid: reference is null";
        }

        String normalized = normalizeReference(reference);

        if (normalized.length() != 14) {
            return "Invalid: wrong length (must be 14 characters)";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(normalized.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(normalized.charAt(i))) {
                return "Invalid: body must contain 11 digits";
            }
        }

        String bankCode = normalized.substring(0, 3);
        String day = normalized.substring(3, 5);
        String month = normalized.substring(5, 7);
        String year = normalized.substring(7, 9);
        String seq = normalized.substring(9, 14);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] ");
        sb.append("DATE: ").append(day).append("/").append(month).append("/").append(year).append(" | ");
        sb.append("SEQ: ").append(seq);

        return sb.toString();
    }

    public static void test(String raw) {
        System.out.printf("Raw Input: \"%s\"%n", raw);
        System.out.println("Result   : " + validateAndFormat(raw) + "\n");
    }

    public static void main(String[] args) {
        test(" hdf03022600042 ");
        test("12F03022600042");
        test("sbi15082512345");
        test("icici12345");
    }
}
