package client;

import compute.*;

public class ConwaysGameOfLife implements Task {
    private int from;
    private int to;
    private int sizeOfBoard;
    private boolean[][] playboard;
    private boolean[][] newPlayboard;

    public ConwaysGameOfLife(int sizeOfBoard, boolean[][] playboard, boolean[][] newPlayboard, int from, int to) {
        this.from = from;
        this.sizeOfBoard = sizeOfBoard;
        this.to = to;
        this.playboard = playboard;
        this.newPlayboard = newPlayboard;
    }

    public Object execute(){ return theLife(playboard,newPlayboard); }

    public int theLife(boolean[][] playboard, boolean[][] newPlayboard) {
        for(int i = from; i < to; i++) {
            for (int j = 0; j < sizeOfBoard; j++) {
                int alive = aliveNeighbours(playboard,i,j);
                if(playboard[i][j]){
                    if(alive < 2 || alive > 3) newPlayboard[i][j] = false;
                    else newPlayboard[i][j] = true;
                } else {
                    if(alive == 3) newPlayboard[i][j] = true;
                    else newPlayboard[i][j] = false;
                }
            }
        }
        return 0;
    }

    public int aliveNeighbours(boolean[][] playboard, int i, int j){
        int alive = 0;
        for (int x = Math.max(i, 1) - 1; x < Math.min(i + 2, sizeOfBoard); ++x)
            for (int y = Math.max(j, 1) - 1; y < Math.min(j + 2, sizeOfBoard); ++y) {
                if (x == i && y == j) continue;
                if (playboard[x][y]) alive++;
            }
        return alive;
    }

}
