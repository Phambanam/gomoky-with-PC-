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
    public static int maxMove = 6;  // so o tiep theo dem xet toi da

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

    public void evalBoard(BoardMarkState boardMarkState, int player) {
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
                            if (eHuman == 0) {
                                if (player == 2)
                                    boardMarkState.getEBoard()[row][col + i] += attackScore[ePC];// cho diem tan cong
                                else boardMarkState.getEBoard()[row][col + i] += defendScore[ePC];
                            }
                            if (ePC == 0) // eHuman khac 0

                                if (player == 2)
                                    boardMarkState.getEBoard()[row][col + i] += defendScore[eHuman];// cho diem phong ngui
                                else boardMarkState.getEBoard()[row][col + i] += attackScore[eHuman];
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
                            if (eHuman == 0)
                                if (player == 2)
                                    boardMarkState.getEBoard()[row + i][col] += attackScore[ePC];
                                else boardMarkState.getEBoard()[row + i][col] += defendScore[ePC];
                            if (ePC == 0)
                                if (player == 2)
                                    boardMarkState.getEBoard()[row + i][col] += defendScore[eHuman];
                                else boardMarkState.getEBoard()[row + i][col] += attackScore[eHuman];
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
                                if (player == 2)
                                    boardMarkState.getEBoard()[row + i][col + i] += attackScore[ePC];
                                else boardMarkState.getEBoard()[row + i][col + i] += defendScore[ePC];
                            if (ePC == 0)
                                if (player == 2)
                                    boardMarkState.getEBoard()[row + i][col + i] += defendScore[eHuman];
                                else boardMarkState.getEBoard()[row + i][col + i] += attackScore[eHuman];
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
                                if (player == 2)
                                    boardMarkState.getEBoard()[row - i][col + i] += attackScore[ePC];
                                else boardMarkState.getEBoard()[row - i][col + i] += defendScore[ePC];
                            if (ePC == 0)
                                if (player == 2)
                                    boardMarkState.getEBoard()[row - i][col + i] += defendScore[eHuman];
                                else boardMarkState.getEBoard()[row - i][col + i] += attackScore[eHuman];
                            if (eHuman == 4 || ePC == 4)
                                boardMarkState.getEBoard()[row - i][col + i] *= 2;
                        }

                    }
            }
    }

    public void alphaBeta(int alpha, int beta, int depth) {
        maxValue(boardState, alpha, beta, depth, true);
    }

    private int maxValue(BoardState state, int alpha, int beta, int depth, boolean pC) {
        // tinh toa do co diem cao nhat
        int value = boardMarkState.getEvaluationBoard(); // gia tri max hien tai
        if (depth >= maxDepth) {
            return value;
        }
        int v = 0;
       evalBoard(boardMarkState, 2);
        ArrayList<Point> list = new ArrayList<>(); // list cac nut con
        for (int i = 0; i < maxMove; i++) {
            Point node = boardMarkState.MaxPos();
            if (node == null)
                break;
            list.add(node);
            boardMarkState.getEBoard()[node.getX()][node.getY()] = 0;
        }


        if (pC) {

            v = Integer.MIN_VALUE;
            for (Point com : list) {
                state.setPosition(com.getX(), com.getY(), 2);
                System.out.println(com.getX() + " "+ com.getY());
                v = Math.max(v, maxValue(state, alpha, beta, depth + 1, false));
                state.setPosition(com.getX(), com.getY(), 0);
                alpha = Math.max(alpha, v);
                if (alpha >= beta || state.checkEnd(com.getX(), com.getY()) == 2) {
                    goAi = com;
                    break;
                }

            }
        } else {

            v = Integer.MAX_VALUE;
            for (Point com : list) {
                state.setPosition(com.getX(), com.getY(), 1);
                v = Math.min(v, maxValue(state, alpha, beta, depth + 1, true));
                state.setPosition(com.getX(), com.getY(), 0);
                beta = Math.min(beta, v);
                if (v <= alpha || state.checkEnd(com.getX(), com.getY()) == 1) {
                    go = com;
                    break;
                }
            }
        }
        return v;
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
        alphaBeta(Integer.MAX_VALUE, Integer.MAX_VALUE, 2);
        System.out.println(goAi.getX() + " " + goAi.getY());
        if (goAi != null) return goAi;
        return new Point(0, 0);
    }
}
