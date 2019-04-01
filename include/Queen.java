

// Class for the queen piece
public class Queen {
    private int x_pos, y_pos;
    private Cell cll;
    boolean placed = false;

    // Default Constructor
    public Queen() {
        x_pos = 0;
        y_pos = 0;
        cll = null;
    }
    // Placing Constructor
        // Followed by blocking of all the cells that this queen blocks (function in Board.java)
    public Queen(int x, int y, Cell cell) {
        x_pos = x;
        y_pos = y;
        placed = true;
        cll = cell;
        cell.queen(true);
    }

    // Handelling the position of queen
    // X coordinate
    public int x() throws Exception {
        if(!placed)
            throw new IllegalArgumentException("The queen is not currently placed.");
        return x_pos;
    }
    // Y coordinate
    public int y() throws Exception {
        if(!placed)
            throw new IllegalArgumentException("The queen is not currently placed.");
        return y_pos;
    }
    // Cell
    public Cell cell() throws Exception {
        if(!placed)
            throw new IllegalArgumentException("The queen is not currently placed.");
        return cll;
    }

    // Updating the position
        // Followed by unblocking (conditional) of all the cells that this queen blocked and blocking of new ones (function in Board.java)
    public void update(int x, int y, Cell cell) {
        x_pos = x;
        y_pos = y;
        placed = true;
        cll = cell;
        try {
            cell.queen(true);
        }
        catch(Exception e) {

        }
    }

    // Removing from the board
        // Followed by unblocking (conditional) of all the cells that this queen blocked (function in Board.java)
    public void remove() {
        x_pos = 0;
        y_pos = 0;
        placed = false;
        if(cll != null)
            cll.queen(false);
        cll = null;
    }

    @Override
    public boolean equals(Object o) {
        try {
            Queen other = (Queen)o;
            return (other.x() == x()) && (other.y() == y());
        }
        catch(Exception e) {
            return false;
        }
    }
}
