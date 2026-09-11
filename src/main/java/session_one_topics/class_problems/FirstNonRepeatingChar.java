package session_one_topics.class_problems;

public class FirstNonRepeatingChar {

    public static char findFirstNonRepeatingChar(String text) {
        if (text == null || text.isEmpty()) {
            return '\0';
        }

        int[] freq = new int[256];
        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i)]++;
        }

        for (int i = 0; i < text.length(); i++) {
            if (freq[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }
        return '\0';
    }

    public static void test(String text) {
        char ch = findFirstNonRepeatingChar(text);
        System.out.printf("Input: \"%s\"%n", text);
        if (ch != '\0') {
            System.out.println("First Non-Repeating Character: '" + ch + "'\n");
        } else {
            System.out.println("No Non-Repeating Character Found\n");
        }
    }

    public static void main(String[] args) {
        test("swiss");
        test("aabbcc");
        test("datastructures");
    }
}