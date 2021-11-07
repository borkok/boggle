import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

class BoggleBoardSolver {
    private final BetterBoggleBoard betterBoard;

    private final Set<String> words;
    private final Dictionary dictionary;

    public BoggleBoardSolver(Dictionary dictionary, BoggleBoard boggleBoard) {
        this.dictionary = dictionary;
        this.betterBoard = new BetterBoggleBoard(boggleBoard);
        this.words = new HashSet<>();

        solve();
    }

    private void solve() {
        if (!betterBoard.hasAtLeast3Dices()) return;

        betterBoard.forEachDice(this::getValidWordsStartingFrom);
    }

    private void getValidWordsStartingFrom(RowCol rowCol) {
        Set<RowCol> visited = new HashSet<>();
        getValidWords(rowCol, "", visited);
    }

    private void getValidWords(RowCol rowCol, String wordSoFar, Set<RowCol> visitedSoFar) {
        if (betterBoard.isOutOfBounds(rowCol)) return;
        if (visitedSoFar.contains(rowCol)) return;

        String word = wordSoFar + betterBoard.getLetter(rowCol);
        if (!dictionary.existsPrefix(word)) return;

        Set<RowCol> visited = new HashSet<>(visitedSoFar);
        visited.add(rowCol);

        if (dictionary.isValid(word)) words.add(word);

        for (Direction direction : EnumSet.allOf(Direction.class)) {
            getValidWords(rowCol.neighbour(direction), word, visited);
        }
    }

    Iterable<String> getAllValidWords() {
        return words;
    }
}
