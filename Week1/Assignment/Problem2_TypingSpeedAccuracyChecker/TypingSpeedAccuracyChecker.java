public class TypingSpeedAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null) {
            System.out.println("Invalid input strings.");
            return;
        }

        int totalChars = original.length();
        int matchedChars = 0;
        int firstMismatchPos = -1;
        char origCharMismatch = ' ';
        char typedCharMismatch = ' ';

        for (int i = 0; i < totalChars; i++) {
            char o = original.charAt(i);
            char t = typed.charAt(i);

            if (o == t) {
                matchedChars++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1;
                origCharMismatch = o;
                typedCharMismatch = t;
            }
        }

        double accuracy = totalChars > 0 ? ((double) matchedChars / totalChars) * 100.0 : 0.0;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Matched: %d/%d | Accuracy: %.2f%% | ", matchedChars, totalChars, accuracy));

        if (firstMismatchPos != -1) {
            sb.append(String.format("First Mismatch at position %d ('%c' vs '%c')", firstMismatchPos, origCharMismatch, typedCharMismatch));
        } else {
            sb.append("No Mismatches");
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1 ---");
        checkTypingAccuracy("hello world", "hello worlt");

        System.out.println("\n--- Test Case 2 ---");
        checkTypingAccuracy("coding", "coding");
    }
}



