package session_two_topics.assignment_problems;

import java.util.*;

public class StopWordFilteredFrequencyReport {

    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "the", "was", "and", "a", "is", "of", "in"
    ));

    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            return;
        }

        String cleaned = feedback.toLowerCase()
                .replace(".", "")
                .replace(",", "")
                .replace("!", "")
                .replace("?", "")
                .replace(";", "")
                .replace(":", "")
                .replace("\"", "");

        String[] words = cleaned.trim().split("\\s+");

        Map<String, Integer> freqMap = new LinkedHashMap<>();
        for (String word : words) {
            if (!word.isEmpty() && !STOP_WORDS.contains(word)) {
                freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freqMap.entrySet());
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1 ---");
        String input = "The mentor was great, the session was great and clear.";
        printFilteredWordFrequency(input);
    }
}


