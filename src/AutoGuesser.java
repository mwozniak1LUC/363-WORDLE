import java.util.List;

public class AutoGuesser {

    List<String> wordList;
    int guessesTaken;

    public AutoGuesser(List<String> wordList) {
        this.wordList = wordList;
        this.guessesTaken = 0;
    }

    public int guesses() {
        return 6;
    }

}
