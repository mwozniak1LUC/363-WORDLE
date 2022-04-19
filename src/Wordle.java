import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Wordle {

    List<String> wordList;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
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
        int int_random = rand.nextInt(wordList.size());
        return wordList.get(int_random);
    }

    public void runGame() {
        String word = getRandomWord(wordList).toUpperCase();
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
                        System.out.print(ANSI_GREEN + guess.charAt(i) + ANSI_RESET);
                    } else if (word.indexOf(guess.charAt(i)) != -1) {
                        System.out.print(ANSI_YELLOW + guess.charAt(i) + ANSI_RESET);
                    } else {
                        System.out.print(guess.charAt(i));
                    }
                }
            }
            guess_count++;
        }
        if (!guess.equals(word)) {
            System.out.println("\n\nGOOD TRY, THE CORRECT WORD WAS " + ANSI_GREEN + word + ANSI_RESET);
        }
    }
}

