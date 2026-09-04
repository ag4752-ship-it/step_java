public class WordReversalEncoder {

    public static String reverseEachWord(String sentence) {
        if (sentence == null) {
            return "";
        }

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder reversedWord = new StringBuilder();

            for (int j = word.length() - 1; j >= 0; j--) {
                reversedWord.append(word.charAt(j));
            }

            result.append(reversedWord);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1 ---");
        String input = "hello club";
        String output = reverseEachWord(input);
        System.out.println(output);
    }
}



