public class Coordinate {
    private final int row;
    private final int column;

    public Coordinate(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){return this.row;}

    public int getColumn(){return this.column;}

    public boolean checkCoordinate(int i, int j){
        return this.row == i && this.column == j;
    }
}
