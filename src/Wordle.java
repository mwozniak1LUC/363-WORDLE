import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Wordle {

    /** TODO: document */
    /** TODO: implement TRIE data structure? */

    public List<String> wordList;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Wordle() {
        this.wordList = readInWords("five_letter_words.txt");
    }

    public List<String> readInWords(String fileName) {
        try {
            Scanner s = new Scanner(new File(fileName));
            ArrayList<String> list = new ArrayList();
            while (s.hasNext()){
                list.add(s.next());
            }
            s.close();
            return list;
        } catch (Exception e){
            System.out.println("Error: " + e);
        }
       return null;
    }

    public String getRandomWord(List<String> wordList) {
        Random rand = new Random();
        int selection = rand.nextInt(wordList.size());
        return wordList.get(selection);
    }

    public String wordChecker(String guess, String word) {
        String result = "";
        if (guess.equals(word)) {
            return "WINNER! THE CORRECT WORD WAS " + ANSI_GREEN + word + ANSI_RESET;
        } else {
            for (int i = 0; i < guess.length(); i++) {
                if (guess.charAt(i) == word.charAt(i)) {
                    result += ANSI_GREEN + guess.charAt(i) + ANSI_RESET;
                } else if (word.indexOf(guess.charAt(i)) != -1) {
                    result += ANSI_YELLOW + guess.charAt(i) + ANSI_RESET;
                } else {
                    result += guess.charAt(i);
                }
            }
        }
        return result;
    }

    public boolean isValidWord(String guess) {
        /** TODO: implement check for guess being valid english word **/
        return true;
    }

    public void runGame() {
            String word = getRandomWord(wordList).toUpperCase();
            Scanner input = new Scanner(System.in);
            AutoGuesser ai = new AutoGuesser(wordList);
            int guess_count = 1;
            String guess = "";
            int numGuessesToBeat = ai.guesses(word);
            System.out.println(ANSI_BLUE + "WELCOME TO MAN VS MACHINE WORDLE, TRY TO GUESS THE FIVE-LETTER WORD!");
            System.out.println(ANSI_BLUE + "OUR ALGORITHM FOUND THE WORD IN " + ANSI_GREEN + numGuessesToBeat +
                              " GUESSES" + ANSI_BLUE + ", CAN YOU BEAT THAT?" + ANSI_RESET);
            while (guess_count <= numGuessesToBeat && !guess.equals(word)) {
                System.out.println("\nGUESS " + guess_count + ": ");
                guess = input.nextLine().toUpperCase();
                if (guess.length() == 5 && isValidWord(guess)) {
                    System.out.println(wordChecker(guess, word));
                    guess_count++;
                } else {
                    System.out.println("INVALID INPUT. TRY AGAIN");
                }
            }
            if (!guess.equals(word)) {
                System.out.println("\n\nGOOD TRY, THE CORRECT WORD WAS " + ANSI_GREEN + word + ANSI_RESET + "\n");
            }
        }
    }

