import java.util.Random;

public class testPass {
    private static int sizeOfBoard = 8;
    private static boolean[][] playboard = new boolean[sizeOfBoard][sizeOfBoard];
    private static boolean[][] newPlayboard = new boolean[sizeOfBoard][sizeOfBoard];

    public static void main(String[] args){

        Random random = new Random();

        for(int i = 0; i < sizeOfBoard; i++) {
            for (int j = 0; j < sizeOfBoard; j++) {
                playboard[i][j] = (random.nextInt(100) > 80);
                if(playboard[i][j]) System.out.print("+");
                else System.out.print("-");
            }
            System.out.println(" ");
        }

        long m = System.currentTimeMillis();
        theLife();
        System.out.println("Program was working: " + (double) (System.currentTimeMillis() - m));

        for(int i = 0; i < sizeOfBoard; i++) {
            for (int j = 0; j < sizeOfBoard; j++) {
                if(newPlayboard[i][j]) System.out.print("+");
                else System.out.print("-");
            }
            System.out.println(" ");
        }


        //long m = System.currentTimeMillis();
        //System.out.println(theLife());
        //System.out.println("Program was working: " + (double) (System.currentTimeMillis() - m));
    }

    public static void theLife() {

        for(int i = 0; i < sizeOfBoard; i++) {
            for (int j = 0; j < sizeOfBoard; j++) {
                int alive = aliveNeighbours(i,j);
                if(playboard[i][j]){
                    if(alive < 2 || alive > 3) newPlayboard[i][j] = false;
                    else newPlayboard[i][j] = true;
                } else {
                    if(alive == 3) newPlayboard[i][j] = true;
                    else newPlayboard[i][j] = false;
                }

            }
        }

    }

    public static int aliveNeighbours(int i, int j){
        int alive = 0;
        for (int x = Math.max(i, 1) - 1; x < Math.min(i + 2, sizeOfBoard); ++x)
            for (int y = Math.max(j, 1) - 1; y < Math.min(j + 2, sizeOfBoard); ++y) {
                if (x == i && y == j) continue;
                if (playboard[x][y]) alive++;
            }
        return alive;
    }
}
