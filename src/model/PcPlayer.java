package model;

import java.util.ArrayList;

public class PcPlayer implements Player {
    private int playerFlag = 2;
    private BoardState boardState;
    private BoardMarkState boardMarkState;
    private Point goAi, go;

    public PcPlayer(BoardState boardState) {
        this.boardState = boardState;
        this.boardMarkState = new BoardMarkState(boardState.getHeight(), boardState.getWidth());
    }

    public static int maxDepth = 6; // do sau toi da
    public static int maxMove = 5;  // so o tiep theo dem xet toi da

    private int[] attackScore = {0, 4, 27, 256, 1458};// Mang diem tan cong 0,4,28,256,2308
    private int[] defendScore = {0, 2, 9, 99, 769};  // Mang diem phong ngu 0,1,9,85,769

    public int[] getAttackScore() {
        return attackScore;
    }

    public void setAttackScore(int[] attackScore) {
        this.attackScore = attackScore;
    }

    public int[] getDefendScore() {
        return defendScore;
    }

    public void setDefendScore(int[] defendScore) {
        this.defendScore = defendScore;
    }

    public void evalBoard(BoardMarkState boardMarkState) {
        int row, col;
        int ePC, eHuman;
        boardMarkState.resetBoard(); // reset toan bo diem trang thai cua toan bo o co
        // Duyet theo hang
        for (row = 0; row < boardMarkState.getWidth(); row++)
            for (col = 0; col < boardMarkState.getHeight() - 4; col++) {
                ePC = 0;
                eHuman = 0;
                for (int i = 0; i < 5; i++) {
                    if (boardState.getPosition(row, col + i) == 1) // neu quan do la cua human
                        eHuman++;
                    if (boardState.getPosition(row, col + i) == 2) // neu quan do la cua pc
                        ePC++;
                }
                // trong vong 5 o khong co quan dich
                if (eHuman * ePC == 0 && eHuman != ePC)
                    for (int i = 0; i < 5; i++) {
                        if (boardState.getPosition(row, col + i) == 0) { // neu o chua danh
                            if (eHuman == 0) // ePC khac 0
//								if (player == 1)
//								{
//								 	eBoard.getEBoard()[row][col + i] += DScore[ePC]; // cho diem phong ngu
//								}
//								else
                            {
                                boardMarkState.getEBoard()[row][col + i] += attackScore[ePC];// cho diem tan cong
                            }
                            if (ePC == 0) // eHuman khac 0


                                boardMarkState.getEBoard()[row][col + i] += defendScore[eHuman];// cho diem phong ngui

                        }
//								else
//									eBoard.getEBoard()[row][col + i] += AScore[eHuman];// cho diem tan cong
                        if (eHuman == 4 || ePC == 4)
                            boardMarkState.getEBoard()[row][col + i] *= 2;
                    }
            }


        // Duyet theo cot
        for (col = 0; col < boardMarkState.getHeight(); col++)
            for (row = 0; row < boardMarkState.getWidth() - 4; row++) {
                ePC = 0;
                eHuman = 0;
                for (int i = 0; i < 5; i++) {
                    if (boardState.getPosition(row + i, col) == 1)
                        eHuman++;
                    if (boardState.getPosition(row + i, col) == 2)
                        ePC++;
                }
                if (eHuman * ePC == 0 && eHuman != ePC)
                    for (int i = 0; i < 5; i++) {
                        if (boardState.getPosition(row + i, col) == 0) // Neu o chua duoc danh
                        {
                            if (eHuman == 0) boardMarkState.getEBoard()[row + i][col] += attackScore[ePC];
                            if (ePC == 0) boardMarkState.getEBoard()[row + i][col] += defendScore[eHuman];
                            if (eHuman == 4 || ePC == 4)
                                boardMarkState.getEBoard()[row + i][col] *= 2;
                        }

                    }
            }

        // Duyet theo duong cheo xuong
        for (col = 0; col < boardMarkState.getHeight() - 4; col++)
            for (row = 0; row < boardMarkState.getWidth() - 4; row++) {
                ePC = 0;
                eHuman = 0;
                for (int i = 0; i < 5; i++) {
                    if (boardState.getPosition(row + i, col + i) == 1)
                        eHuman++;
                    if (boardState.getPosition(row + i, col + i) == 2)
                        ePC++;
                }
                if (eHuman * ePC == 0 && eHuman != ePC)
                    for (int i = 0; i < 5; i++) {
                        if (boardState.getPosition(row + i, col + i) == 0) // Neu o chua duoc danh
                        {
                            if (eHuman == 0)
                                boardMarkState.getEBoard()[row + i][col + i] += attackScore[ePC];
                            if (ePC == 0)

                                boardMarkState.getEBoard()[row + i][col + i] += defendScore[eHuman];
                            if (eHuman == 4 || ePC == 4)
                                boardMarkState.getEBoard()[row + i][col + i] *= 2;
                        }

                    }
            }

        // Duyet theo duong cheo len
        for (row = 4; row < boardMarkState.getWidth(); row++)
            for (col = 0; col < boardMarkState.getHeight() - 4; col++) {
                ePC = 0; // so quan PC
                eHuman = 0; // so quan Human
                for (int i = 0; i < 5; i++) {
                    if (boardState.getPosition(row - i, col + i) == 1) // neu la human
                        eHuman++; // tang so quan human
                    if (boardState.getPosition(row - i, col + i) == 2) // neu la PC
                        ePC++; // tang so quan PC
                }
                if (eHuman * ePC == 0 && eHuman != ePC)
                    for (int i = 0; i < 5; i++) {
                        if (boardState.getPosition(row - i, col + i) == 0) { // neu o chua duoc danh
                            if (eHuman == 0)
                                boardMarkState.getEBoard()[row - i][col + i] += attackScore[ePC];
                            if (ePC == 0)

                                boardMarkState.getEBoard()[row - i][col + i] += defendScore[eHuman];
                            if (eHuman == 4 || ePC == 4)
                                boardMarkState.getEBoard()[row - i][col + i] *= 2;
                        }

                    }
            }
    }

    public void alphaBeta(int alpha, int beta, int depth) {
        maxValue(boardState, alpha, beta, depth);
    }

    private int maxValue(BoardState state, int alpha, int beta, int depth) {
        // tinh toa do co diem cao nhat
        int value = boardMarkState.getEvaluationBoard(); // gia tri max hien tai
        if (depth >= maxDepth) {
            return value;
        }
        int v = 0;
        evalBoard(boardMarkState);
        System.out.println("---------");// danh gia diem voi nguoi choi hien tai la PC
        print(boardMarkState);
        ArrayList<Point> list = new ArrayList<>(); // list cac nut con
        for (int i = 0; i < maxMove; i++) {
            Point node = boardMarkState.MaxPos();
            if (node == null)
                break;
            list.add(node); System.out.println(node.getX()+" "+ node.getY());
            System.out.println(boardMarkState.getEBoard()[node.getX()][node.getY()]);
            boardMarkState.getEBoard()[node.getX()][node.getY()] = 0;
        }


        System.out.println("-------------");
            v = Integer.MIN_VALUE;
            for (Point com : list) {
                state.setPosition(com.getX(), com.getY(), 2);
                v = Math.max(v, minValue(state, alpha, beta, depth + 1));
                state.setPosition(com.getX(), com.getY(), 0);
                if (v >= beta || state.checkEnd(com.getX(), com.getY()) == 2) {
                    goAi = com;
                    return v;
                }
                alpha = Math.max(alpha, v);
            }

        return v;
    }
    public int minValue(BoardState state, int alpha, int beta, int depth){
        boardMarkState.MaxPos();
        int value = boardMarkState.getEvaluationBoard(); // gia tri max hien tai
        if (depth >= maxDepth) {
            return value;
        }
        ArrayList<Point> list = new ArrayList<>(); // list cac nut con
        for (int i = 0; i < maxMove; i++) {
            Point node = boardMarkState.MaxPos();
            if (node == null)
                break;
            list.add(node);
            boardMarkState.getEBoard()[node.getX()][node.getY()] = 0;
        }
        int v = Integer.MAX_VALUE;
        for (Point com : list) {
            state.setPosition(com.getX(), com.getY(), 1);
            v = Math.min(v, minValue(state, alpha, beta, depth + 1));
            state.setPosition(com.getX(), com.getY(), 0);
            if (v >= beta || state.checkEnd(com.getX(), com.getY()) == 1) {
                go= com;
                return v;
            }
            alpha = Math.min(alpha, v);
        }
        return v;
    }
    int  k = 0;
    public void print(BoardMarkState b){
        k++;
        System.out.println(k+" -------------------------");
        for(int i = 0 ; i < b.getEBoard().length; i++){
            for(int j = 0 ; j < b.getEBoard().length; j++){
                System.out.print(b.getEBoard()[i][j]+" ");
                if(j == b.getEBoard().length-1) System.out.println("\n");

            }
        }

    }
    @Override
    public int getPlayerFlag() {
        return playerFlag;
    }

    @Override
    public void setPlayerFlag(int playerFlag) {
        this.playerFlag = playerFlag;
    }

    @Override
    public BoardState getBoardState() {
        return boardState;
    }

    @Override
    public Point movePoint(int player) {
        alphaBeta(0, 1, 2);
        System.out.println(goAi.getX() + " " + goAi.getY());
        if (goAi != null) return goAi;
        return new Point(0, 0);
    }
}
