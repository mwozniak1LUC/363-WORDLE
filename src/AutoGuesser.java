import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoGuesser {

    public List<String> wordList;
    public int guessesTaken;
    public Character[] board;
    public List<Character> found;
    public List<Character> notPresent;
    public List<Integer> checked;

    public AutoGuesser(List<String> wordList) {
        this.wordList = wordList;
        this.guessesTaken = 0;
        this.board = new Character[]{'*','*','*','*','*'};
        this.found = new ArrayList<>();
        this.notPresent = new ArrayList<>();
    }

    // correctAnswer is only there for checking
    public boolean checkAnswer(String aiGuess, String correctWord) {
        if (aiGuess.equals(correctWord)) {
            return true;
        } else {
            for (int i = 0; i < aiGuess.length(); i++) {
                if (aiGuess.charAt(i) == correctWord.charAt(i)) {
                    board[i] = aiGuess.charAt(i);
                } else if (correctWord.indexOf(aiGuess.charAt(i)) != -1) {
                    found.add(aiGuess.charAt(i));
                } else {
                    notPresent.add(aiGuess.charAt(i));
                }
            }
        }
        return false;
    }

    public void reduceList(int pos, char c) {
        for (String word : wordList) {
            if (word.charAt(pos) != c) {
                wordList.remove(word);
            }
        }
    }

    public String newGuess(List<String> wordList) {
        Random rand = new Random();
        int selection = rand.nextInt(wordList.size());
        return wordList.get(selection);
    }

    public int guesses(String correctWord) {
        return 6;
        /** TODO: need to make this have a systemic approach using the wordList and strategic guessing strategy to guess the correct word in as few number of guesses possible.*/
//        String guess = "ARISE"; // popular first guess
//        while (checkAnswer(guess, correctWord) == false) {
//                guessesTaken++;
//                for (int i = 0; i < board.length; i++) {
//                    if (board[i] != '*' && !checked.contains(i)) {
//                        reduceList(i, board[i]);
//                        checked.add(i);
//                    }
//                }
//                System.out.println(wordList.size());
//                guess = newGuess(wordList);
//        }
//        return guessesTaken;
        }
    }


