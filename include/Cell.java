import java.util.Vector;

// Cell class for discrete positions on a board.
public class Cell {
    private boolean blocked, queen;    // Whether a queen is placable on the system.
    public Vector<Queen> placed;

    // Constructor
    public Cell() {
        blocked = false;
        placed = new Vector<>();
    }

    // Functions for handelling blocked
    public void block() {
        blocked = true;
    }
    public void unblock() {
        blocked = false;
    }
    public boolean blocked() {
        return blocked;
    }

    // Functions for determining placed queen
    void queen(boolean b) {
        queen = b;
    }
    boolean hasQueen() {
        return queen;
    }

    // Placing the queen that blocks this cell
    public void place(Queen q) {
        if(placed.indexOf(q) != -1)
            return;
        placed.addElement(q);
        block();
    }
    // Unplacing the queen, if blocking the current block
    public void unplace(Queen q) {
        try {
            placed.removeElementAt(placed.indexOf(q));
            if(placed.size() == 0)
                unblock();
        }
        catch (Exception e) {
            // Do nothing, let pass...
            // System.out.println("Queen not affecting the current cell...");
        }
    }
}
