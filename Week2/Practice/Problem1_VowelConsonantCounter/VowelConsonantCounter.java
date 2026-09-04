public class VowelConsonantCounter {

    public static void countVowelsAndConsonants(String text) {
        if (text == null) {
            System.out.println("No text provided.");
            return;
        }

        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = Character.toLowerCase(text.charAt(i));
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        System.out.printf("Input: \"%s\"%n", text);
        System.out.printf("Vowels: %d | Consonants: %d%n%n", vowels, consonants);
    }

    public static void main(String[] args) {
        countVowelsAndConsonants("Java Programming");
        countVowelsAndConsonants("BridgeLabz CodInClub");
    }
}
