

// The main simulator class
public class Simulator {

    public static void main(String[] args) throws Exception{
        Board b = new Board(8);
        int vals = 8;
        Queen q;
        try {
            b = new Board(Integer.valueOf(args[0]));
            vals = Integer.valueOf(args[1]);
            assert(vals <= b.size());
        }
        catch(AssertionError e) {
            System.out.println("No. of queens can't be greater than board size...");
            System.exit(1);
        }
        catch(Exception e) {
            // Do Nothing
        }
        try {
            boolean check = (args[2].equals("Fast") || args[2].equals("No_Thread") || args[2].equals("F"));
            if(check)
                b.useThread = false;
        }
        catch(Exception e) {
            // Do Nothing
        }
        try {
            for(int i=0; i<vals; i++)
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
