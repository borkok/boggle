import java.util.function.Consumer;

class BetterBoggleBoard {
    private final BoggleBoard board;

    BetterBoggleBoard(BoggleBoard board) {
        this.board = board;
    }

    boolean hasAtLeast3Dices() {
        return board.cols() + board.rows() > 3;
    }

    void forEachDice(Consumer<RowCol> consumer) {
        for (int row = 0; row < board.rows(); row++) {
            for (int col = 0; col < board.cols(); col++) {
                consumer.accept(new RowCol(row, col));
            }
        }
    }

    boolean isOutOfBounds(RowCol rowCol) {
        return rowCol.col() < 0 || rowCol.col() >= board.cols() || rowCol.row() < 0 || rowCol.row() >= board.rows();
    }

    public String getLetter(RowCol rowCol) {
        char letter = board.getLetter(rowCol.row(), rowCol.col());
        return letter == 'Q' ? "QU" : ""+letter;
    }
}
