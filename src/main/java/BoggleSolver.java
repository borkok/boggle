//Immutable
public class BoggleSolver {
    private final Dictionary dictionary;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        this.dictionary = new Dictionary(dictionary);
    }

    /**
     * Returns the set of all valid words in the given Boggle board, as an Iterable.
     * A valid word must be composed by following a sequence of adjacent dice—two dice are adjacent if they are horizontal, vertical, or diagonal neighbors.
     * A valid word can use each die at most once.
     * A valid word must contain at least 3 letters.
     * A valid word must be in the dictionary (which typically does not contain proper nouns).
     */
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        return new BoggleBoardSolver(dictionary, board).getAllValidWords();
    }

    /**
     * Returns the score of the given word if it is in the dictionary, zero otherwise.
     * (You can assume the word contains only the uppercase letters A through Z.)
     * Valid words are scored according to their length, using this table:
     * word length	  	points
     * 3–4		1
     * 5		2
     * 6		3
     * 7		5
     * 8+		11
     *
     * @param word
     * @return score of the given word
     * @throws IllegalArgumentException if word is null, is empty or contains other letters that A..Z
     */
    private static final byte[] POINTS = new byte[]{0, 0, 0, 1, 1, 2, 3, 5, 11};

    public int scoreOf(String word) {
        if (word == null || !word.matches("[A-Z]+")) throw new IllegalArgumentException();
        if (!dictionary.isValid(word)) return 0;

        if (word.length() > 8) return POINTS[8];
        return POINTS[word.length()];
    }
}