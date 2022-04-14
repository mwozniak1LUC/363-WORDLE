import java.util.Scanner;

public class Wordle {
    public static void main(String[] args) {
        String word = "TESTS"; // will eventually pick a random word from a list of all 5-letter words
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Wordle, Guess the 5-letter word in 6 guesses");
        int guess_count = 1;
        String guess = "";
        while (guess_count <= 6 && !guess.equals(word)) {
            System.out.println("\nGUESS " + guess_count + ": ");
            guess = input.nextLine().toUpperCase();
            if (guess.equals(word)) {
                System.out.println("WINNER! THE WORD WAS " + guess);
            } else {
                for (int i = 0; i < guess.length(); i++) {
                    if (guess.charAt(i) == word.charAt(i)) {
                        System.out.print("✓");
                    } else if (word.indexOf(guess.charAt(i)) != -1) {
                        System.out.print("C");
                    } else {
                        System.out.print("X");
                    }
                }
            }
            guess_count++;
        }
        if (guess != word) {
            System.out.println("GOOD TRY, THE CORRECT WORD WAS " + word);
        }
    }
}
