

// The main simulator class
public class Simulator {

    public static void main(String[] args) throws Exception{
        Board b = new Board(10);
        Queen q;
        try {
            for(int i=0; i<9; i++)
            {
                q = new Queen();
                b.placeQueen(q);
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
