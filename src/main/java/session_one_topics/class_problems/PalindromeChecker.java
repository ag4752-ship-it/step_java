package session_one_topics.class_problems;

public class PalindromeChecker {

    public static boolean isPalindromeIterative(String text) {
        if (text == null) return false;
        int left = 0, right = text.length() - 1;
        while (left < right) {
            if (Character.toLowerCase(text.charAt(left)) != Character.toLowerCase(text.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text == null) return false;
        return checkRecursive(text.toLowerCase(), 0, text.length() - 1);
    }

    private static boolean checkRecursive(String text, int left, int right) {
        if (left >= right) return true;
        if (text.charAt(left) != text.charAt(right)) return false;
        return checkRecursive(text, left + 1, right - 1);
    }

    public static boolean isPalindromeArrayReversal(String text) {
        if (text == null) return false;
        char[] original = text.toLowerCase().toCharArray();
        char[] reversed = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        for (int i = 0; i < original.length; i++) {
            if (original[i] != reversed[i]) return false;
        }
        return true;
    }

    public static void testString(String input) {
        boolean iter = isPalindromeIterative(input);
        boolean rec = isPalindromeRecursive(input);
        boolean arr = isPalindromeArrayReversal(input);

        String iterRes = iter ? "Palindrome" : "Not Palindrome";
        String recRes = rec ? "Palindrome" : "Not Palindrome";
        String arrRes = arr ? "Palindrome" : "Not Palindrome";

        System.out.printf("Input: \"%s\"%n", input);
        System.out.printf("Iterative: %s | Recursive: %s | Array Reversal: %s%n%n", iterRes, recRes, arrRes);
    }

    public static void main(String[] args) {
        testString("madam");
        testString("hello");
        testString("racecar");
    }
}