import java.util.Objects;

public class RowCol {
    private final int row;
    private final int col;

    public RowCol(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    //compass rose
    public RowCol neighbour(Direction direction) {
        return new RowCol(row + direction.rowOffset, col + direction.colOffset);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RowCol rowCol = (RowCol) o;
        return row == rowCol.row && col == rowCol.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
