package model;

public class BoardState {
    private int width,height;
    private  int[][] boardArr;
    private Point begin;
    private Point end;

    public BoardState(int widthBoard, int heightBoard) {
        boardArr = new int[widthBoard][heightBoard];
        this.width = widthBoard;
        this.height = heightBoard;
    }

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
        // Check hang ngang(---)
        while (c < width - 4) {
            human = true;
            pc = true;
            for (i = 0; i < 5; i++) {
                if (boardArr[row][c + i] != 1)
                    human = false;
                if (boardArr[row][c + i] != 2)
                    pc = false;
            }
            if(human || pc){
                begin = new Point(row,c);
                end = new Point(row,c+5);
            }
            if (human)
                return 1;
            if (pc)
                return 2;
            c++;
        }

        // Check  hang doc (||)
        while (r < height - 4) {
            human = true;
            pc = true;
            for (i = 0; i < 5; i++) {
                if (boardArr[r + i][col] != 1)
                    human = false;
                if (boardArr[r + i][col] != 2)
                    pc = false;
            }
            if(human || pc){
                begin = new Point(r,col);
                end = new Point(r+5,col);
            }
            if (human)
                return 1;
            if (pc)
                return 2;
            r++;
        }

        // Check duong cheo xuong(-_)
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
            if (human){
                begin = new Point(r,c);
                end = new Point(r+5,c+5);
                return 1;}
            if (pc)
            {
                begin = new Point(r,c);
                end = new Point(r+5,c+5);
                return 2;
            }
            r++;
            c++;
        }

        // Check duong cheo len(_-)
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
            }if(human || pc){
                begin = new Point(r,c);
                end = new Point(r-5,c+5);
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


    public void setPosition(int x, int y, int player) {
            boardArr[x][y] = player;
    }
}
