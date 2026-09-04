import java.util.Random;

public class RockPaperScissors {

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        Random random = new Random(42);

        int rounds = 5;
        String[] roundHistory = new String[rounds];
        int wins = 0, losses = 0, draws = 0;

        System.out.println("=== Rock-Paper-Scissors Match ===");
        for (int i = 0; i < rounds; i++) {
            String player = playerMoves[i];
            String computer = moves[random.nextInt(3)];
            String result = playRound(player, computer);

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }

            roundHistory[i] = String.format("Round %d | Player: %-8s | Computer: %-8s | Result: %s", 
                                            (i + 1), player, computer, result);
            System.out.println(roundHistory[i]);
        }

        double winPercentage = ((double) wins / rounds) * 100.0;
        System.out.println();
        System.out.println("Final Summary (after " + rounds + " rounds)");
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n", wins, losses, draws, winPercentage);
    }
}
