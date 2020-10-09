package model;

public class BoardMarkState {
    private int height, width;
    private  int[][] EBoard;
    private  int evaluationBoard = 0;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int[][] getEBoard() {
        return EBoard;
    }

    public void setEBoard(int[][] EBoard) {
        this.EBoard = EBoard;
    }

    public int getEvaluationBoard() {
        return evaluationBoard;
    }

    public void setEvaluationBoard(int evaluationBoard) {
        this.evaluationBoard = evaluationBoard;
    }

    public BoardMarkState(int height, int width) {
        this.height = height;
        this.width = width;
        EBoard = new int[height][width];
        // ResetBoard();
    }

    public void resetBoard() {
        for (int r = 0; r < height; r++)
            for (int c = 0; c < width; c++)
                EBoard[r][c] = 0;
    }

    public Point MaxPos() {
        int Max = 0; // mark max
        Point p = new Point();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (EBoard[i][j] > Max) {
                    p.setX(i);
                    p.setY(j);
                    Max = EBoard[i][j];
                }
            }
        }
        if (Max == 0) {
            return null;
        }
        setEvaluationBoard(Max);
        return p;
    }
}
