import java.util.*;

/** Custom Trie Implementation */
class Trie
{
    private int size;
    private boolean isLeaf;
    private Map<Character, Trie> children;
    private List<String> wordList;

    /** Constructor */
    Trie() {
        this.size = 0;
        this.wordList = new ArrayList<>();
        this.isLeaf = false;
        this.children = new HashMap<>();
    }

    /** Inserts a string into the Trie */
    public void insert(String key) {
        wordList.add(key);
        Trie currentSubTrie = this;

        /** goes char by char to make subtrees */
        for (char c: key.toCharArray())
        {
            currentSubTrie.children.putIfAbsent(c, new Trie());
            currentSubTrie = currentSubTrie.children.get(c);
        }

        /** at end of inserted word, mark current as a leaf */
        currentSubTrie.isLeaf = true;
        size++;
    }

    /** searches for key in trie, returns true if found */
    public boolean search(String key) {
        Trie currentSubTrie = this;

        /** go character by character (subtree by subtree to find the word) */
        for (char c: key.toCharArray())
        {
            /** next subtree */
            currentSubTrie = currentSubTrie.children.get(c);

            /** if next subtree doesn't exist, word not found */
            if (currentSubTrie == null) {
                return false;
            }
        }

        /** is ending char a leaf, if yes then word is found */
        return currentSubTrie.isLeaf;
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