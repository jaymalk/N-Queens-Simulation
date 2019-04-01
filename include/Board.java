import java.util.Vector;
import java.util.Random;

// Class for placing of queens on a grid of cells
public class Board {
    int size;   // Board dimensions (size * size)
    Vector<Vector<Cell>> cells;     // Cells on the board
    Vector<Queen> queens;      // Vector of queens placed so far
    Random r = new Random();

    // Constructor
    public Board(int size) {
        this.size = size;
        // Constructing the cell grid
        cells = new Vector<>();
        for(int i=0; i<size; i++) {
            Vector<Cell> temp = new Vector<>();
            for(int j=0; j<size; j++)
                temp.addElement(new Cell());
            cells.addElement(temp);
        }
        // Initialising the queens vector
        queens = new Vector<>();
    }

    // Placing a queen on a board
    public void placeQueen(Queen q) throws Exception {
        if(queens.size() == 0) {
            q.update(0, 0, cells.get(0).get(0));
            blockPositions(0, 0, q);
            queens.addElement(q);
            return;
        }
        int yStart = 0;
        try {
            Queen prevPlaced = queens.get(queens.size()-1);
            yStart = prevPlaced.y();
        }
        catch(Exception e) {
            // Do nothing...
        }
        placeQueen(q, yStart+1);
    }
    public void placeQueen(Queen q, int y) throws Exception {
        boolean placed = false;
        for(int j=y; (j<size) && (!placed); j++)
            for(int i=0; i<size; i++)
                if(!cells.get(i).get(j).blocked())
                // Cell is free for the queen to be placed
                {
                    q.update(i, j, cells.get(i).get(j));
                    blockPositions(i, j, q);
                    placed = true;
                    break;
                }
        if(!placed)
        {
            // No suitable position for the queen
            // Reposition the previous queen
            if(queens.size() == 0)
                throw new Exception("NO PLACEMENT POSSIBLE");
            replaceQueen(queens.get(queens.size()-1));
            placeQueen(q);
        }
        if(queens.indexOf(q) == -1) {
            queens.add(q);
            Thread.sleep(500);
            draw();
        }
    }

    // Repositioning a queen
    public void replaceQueen(Queen q) throws Exception{
        unblockPositions(q);
        int indx = queens.indexOf(q);
        if(indx == -1)
            System.out.println(q);
        queens.removeElementAt(indx);;
        int x = 0, y = 0;
        try {
            x = q.x();
            y = q.y();
        }
        catch(Exception e) {
            // Do nothing, never caught
        }
        boolean placed = false;
        q.remove();
        for(int i=x+1; i < size; i++)
            if(!cells.get(i).get(y).blocked())
            // Cell is free for the queen to be placed
            {
                q.update(i, y, cells.get(i).get(y));
                blockPositions(i, y, q);
                placed = true;
                break;
            }
        if(!placed) {
            for(int j=y+1; (j<size) && (!placed); j++)
                for(int i=0; i<size; i++)
                    if(!cells.get(i).get(j).blocked())
                    // Cell is free for the queen to be placed
                    {
                        q.update(i, j, cells.get(i).get(j));
                        blockPositions(i, j, q);
                        placed = true;
                        break;
                    }
        }
        if(!placed)
        {
            // No suitable position for the queen
            // Reposition the previous queen
            if(queens.size() == 0)
                throw new Exception("NO PLACEMENT POSSIBLE");
            replaceQueen(queens.get(queens.size()-1));
            placeQueen(q);
        }
        if(queens.indexOf(q) == -1)
            queens.add(q);
    }

    // Blocking all postions blocked by the set queen
    public void blockPositions(int x, int y, Queen q) {
        for(int i=0; i<size; i++) {
            cells.get(x).get(i).place(q);
            cells.get(i).get(y).place(q);
        }
        if(x < y) {
            int i=0;
            while(i+y-x < size) {
                cells.get(i).get(i+y-x).place(q);
                i++;
            }
        }
        else {
            int i=0;
            while(i-y+x < size) {
                cells.get(i+x-y).get(i).place(q);
                i++;
            }
        }
        for(int i=-size; i<size; i++) {
            if(! ( x-i < 0 || x-i >= size || y+i < 0 || y+i >= size) )
            cells.get(x-i).get(y+i).place(q);
        }
    }

    // Unblock all positions blocked by the removed queen
    public void unblockPositions(Queen q) {
        int x = 0, y = 0;
        try {
            x = q.x();
            y = q.y();
        }
        catch(Exception e) {
            // Do nothing, never caught
        }
        for(int i=0; i<size; i++) {
            cells.get(x).get(i).unplace(q);
            cells.get(i).get(y).unplace(q);
        }
        if(x < y) {
            int i=0;
            while(i+y-x < size) {
                cells.get(i).get(i+y-x).unplace(q);
                i++;
            }
        }
        else {
            int i=0;
            while(i-y+x < size) {
                cells.get(i+x-y).get(i).unplace(q);
                i++;
            }
        }
        for(int i=-size; i<size; i++) {
            if(! ( x-i < 0 || x-i >= size || y+i < 0 || y+i >= size) )
            cells.get(x-i).get(y+i).unplace(q);
        }
    }

    // Drawing the board
    public void draw() {
        System.out.println("\033[2J\033[H");
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++)
                if(cells.get(i).get(j).hasQueen())
                    System.out.print("O ");
                else
                    System.out.print(". ");
            System.out.println();
        }

    }
}
