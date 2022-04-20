import java.io.*;
import java.util.Scanner;

public class Wordle {

    /** class instance variables */
    private int guessLimit;
    private Trie wordTrie;
    private String word;

    /** colors for use in terminal console */
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    /** Constructor */
    public Wordle() {
        this.guessLimit = 6;
        this.wordTrie = buildWordTrie("five_letter_words.txt");
        this.word = wordTrie.getRandomWord();
    }

    /** Builds the Trie data structure using the five_letter_words.txt */
    public Trie buildWordTrie(String fileName) {
        try {
            Scanner s = new Scanner(new File(fileName));
            Trie wordTrie = new Trie();
            while (s.hasNext()){
                wordTrie.insert(s.next().toUpperCase());
            }
            s.close();
            return wordTrie;
        } catch (Exception e){
            System.out.println("Error: " + e);
        }
       return null;
    }

    /** Checks for any correct/incorrect letter positions in a given guess */
    public String wordChecker(String guess, String word) {
        String result = "";
        if (guess.equals(word)) {
            return "WINNER! THE CORRECT WORD WAS " + ANSI_GREEN + word + ANSI_RESET;
        } else {
            for (int i = 0; i < guess.length(); i++) {
                /** correct letter position (green) */
                if (guess.charAt(i) == word.charAt(i)) {
                    result += ANSI_GREEN + guess.charAt(i) + ANSI_RESET;
                }
                else {
                    /** word contains letter, but in different position (yellow)
                     *  ~ handles duplicate letters being made yellow when the correct position was already found */
                    int dupCheck = word.indexOf(guess.charAt(i));
                    if (dupCheck != -1 && guess.charAt(dupCheck) != guess.charAt(i)) {
                        result += ANSI_YELLOW + guess.charAt(i) + ANSI_RESET;
                    } else {
                        /** letter not present in word */
                        result += guess.charAt(i);
                    }
                }
            }
        }
        return result;
    }

    /** Uses essentially BFS to search the trie for if a given guess is a valid english word */
    public boolean isValidWord(String guess) {
        return wordTrie.search(guess);
    }

    /** returns the trie (for testing purposes) */
    public Trie getWordTrie() {
        return this.wordTrie;
    }

    /** Workhorse method that runs the game */
    public void runGame() {
            Scanner input = new Scanner(System.in);
            int guess_count = 1;
            String guess = "";
            System.out.println(ANSI_BLUE + "WELCOME TO WORDLE, TRY TO GUESS THE FIVE-LETTER WORD IN 6 GUESSES!" + ANSI_RESET);
            while (guess_count <= guessLimit && !guess.equals(word)) {
                System.out.println("\nGUESS " + guess_count + ": ");
                guess = input.nextLine().toUpperCase();
                if (isValidWord(guess)) {
                    System.out.println(wordChecker(guess, word));
                    guess_count++;
                } else {
                    System.out.println(ANSI_RED + "INVALID INPUT. TRY AGAIN" + ANSI_RESET);
                }
            }
            if (!guess.equals(word)) {
                System.out.println(ANSI_RED + "\n\nGOOD TRY, THE CORRECT WORD WAS " + word + ANSI_RESET + "\n");
            }
        }
    }

