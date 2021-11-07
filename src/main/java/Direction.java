public enum Direction {
    N   (-1,0),
    NE  (-1,1),
    E   (0,+1),
    SE  (+1,+1),
    S   (+1,0),
    SW  (+1,-1),
    W   (0,-1),
    NW  (-1,-1);

    public final int rowOffset;
    public final int colOffset;

    Direction(int rowOffset, int colOffset) {
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
    }
}
