import java.util.*;

/** Custom Trie Implementation */
class Trie
{
    private int size;
    private boolean isLastLetter;
    private Map<Character, Trie> children;
    private List<String> wordList;

    /** Constructor */
    Trie() {
        this.size = 0;
        this.wordList = new ArrayList<>();
        this.isLastLetter = false;
        this.children = new HashMap<>();
    }

    /** Inserts a string into the Trie */
    public void insert(String key) {
        wordList.add(key);
        Trie current = this;

        /** goes char by char to make subtrees */
        for (char c: key.toCharArray())
        {
            current.children.putIfAbsent(c, new Trie());
            current = current.children.get(c);
        }

        /** at end of inserted word, mark current as a leaf */
        current.isLastLetter = true;
        size++;
    }

    /** searches for key in trie, returns true if found */
    public boolean search(String key) {
        Trie current = this;

        /** go character by character (subtree by subtree -- map by map) */
        /** Example: KEY = BREAD **/
        for (char c: key.toCharArray())
        {
            /** next subtree */
            /** 1st loop: change current to B's subtree if it exists */
            /** 2nd loop: change current to R's subtree of the B subtree if it exists */
            /** 3rd loop: change current to E's subtree of the R subtree if it exists */
            /** 4th loop: change current to A's subtree of the E subtree if it exists */
            /** 5th loop: change current to D's subtree of the A subtree if it exists */
            current = current.children.get(c);

            /** if next subtree doesn't exist, word not found */
            if (current == null) {
                return false;
            }
        }

        /** if current node is a leaf, then the word was found */
        return current.isLastLetter;
    }

    /** returns a random word from the trie */
    public String getRandomWord() {
        try {
            Random rand = new Random();
            int selection = rand.nextInt(wordList.size());
            return wordList.get(selection);
        } catch (Exception e) {
            return null;
        }
    }

    /** returns size of trie */
    public int getSize() {
        return this.size;
    }

}