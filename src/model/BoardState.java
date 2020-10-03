package model;

public class BoardState {
    private int width,height;
    private  int[][] boardArr;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[][] getBoardArray() {
        return boardArr;
    }

    public void setBoardArray(int[][] boardArray) {
        this.boardArr= boardArray;
    }
    public void resetBoard(){
        boardArr = new int[width][height];
    }
    public int checkEnd(int row, int col) {
        int r = 0, c = 0;
        int i;
        boolean human, pc;
        // Check hang ngang
        while (c < width - 4) {
            human = true;
            pc = true;
            for (i = 0; i < 5; i++) {
                if (boardArr[row][c + i] != 1)
                    human = false;
                if (boardArr[row][c + i] != 2)
                    pc = false;
            }
            if (human)
                return 1;
            if (pc)
                return 2;
            c++;
        }

        // Check  hang doc
        while (r < height - 4) {
            human = true;
            pc = true;
            for (i = 0; i < 5; i++) {
                if (boardArr[r + i][col] != 1)
                    human = false;
                if (boardArr[r + i][col] != 2)
                    pc = false;
            }
            if (human)
                return 1;
            if (pc)
                return 2;
            r++;
        }

        // Check duong cheo xuong
        r = row;
        c = col;
        while (r > 0 && c > 0) {
            r--;
            c--;
        }
        while (r < height - 4 && c < width - 4) {
            human = true;
            pc = true;
            for (i = 0; i < 5; i++) {
                if (boardArr[r + i][c + i] != 1)
                    human = false;
                if (boardArr[r + i][c + i] != 2)
                    pc = false;
            }
            if (human)
                return 1;
            if (pc)
                return 2;
            r++;
            c++;
        }

        // Check duong cheo len
        r = row;
        c = col;
        while (r < height - 1 && c > 0) {
            r++;
            c--;
        }

        while (r >= 4 && c < height - 4) {
            human = true;
            pc = true;
            for (i = 0; i < 5; i++) {
                if (boardArr[r - i][c + i] != 1)
                    human = false;
                if (boardArr[r - i][c + i] != 2)
                    pc = false;
            }
            if (human)
                return 1;
            if (pc)
                return 2;
            r--;
            c++;
        }
        return 0;
    }
    public int getPosition(int x, int y) {
        return boardArr[x][y];
    }


    public boolean setPosition(int x, int y, int player) {
        if(boardArr[x][y] == 0)
        {
            boardArr[x][y] = player;
            return true ;
        }
        return false;
    }
}
