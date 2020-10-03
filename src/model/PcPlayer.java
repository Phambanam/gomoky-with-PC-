package model;

import org.jetbrains.annotations.NotNull;

public class PcPlayer implements Player {
    private int playerFlag = 2;
    private BoardState boardState;
    private BoardMarkState boardMarkState;
    private int x, y;

    public PcPlayer(BoardState boardState) {
        this.boardState = boardState;
    }

    public static int maxDepth = 6; // do sau toi da
    public static int maxMove = 6;  // so o tiep theo dem xet toi da

    private int[] attackScore = {0, 4, 27, 256, 1458};// Mang diem tan cong 0,4,28,256,2308
    private int[] defendScore = {0, 2, 9, 99, 769};  // Mang diem phong ngu 0,1,9,85,769


    public void evalBoard(int player, @NotNull BoardMarkState boardMarkState) {
        int col, row;
        int ePC, eHuman;
        boardMarkState.resetBoard();
        // xet 5 quan theo hang ngang
        for (col = 0; col < boardMarkState.getHeight(); col++)
            for (row = 0; row < boardMarkState.getWidth(); col++) {
                ePC = 0;
                eHuman = 0;
                for (int i = 0; i < 5; i++) {
                    int positionPlayer = boardState.getPosition(col, row + i);
                    if (positionPlayer == 1) eHuman++;
                    if (positionPlayer == 2) ePC++;
                }
                if (ePC * eHuman == 0) {
                    for (int i = 0; i < 5; i++) {
                        if (boardState.getPosition(col, row + i) == 0) {
                            if (ePC != 0) {
                                if (player == 1) boardMarkState.getEBoard()[col][row + i] += defendScore[ePC];
                                else boardMarkState.getEBoard()[col][row + i] += attackScore[ePC];
                            }
                            if (eHuman != 0) {
                                if (player == 1) boardMarkState.getEBoard()[col][row + i] += attackScore[eHuman];
                                else boardMarkState.getEBoard()[col][row + i] += defendScore[eHuman];
                            }
                            if(eHuman ==4 || ePC ==4) boardMarkState.getEBoard()[col][row + i] *=2;
                        }
                    }
                }

            }
        // xet hang doc
        for(row = 0; row < boardState.getWidth(); row++)
            for(col = 0; col < boardState.getHeight() - 4; col++){
                ePC = 0;
                eHuman = 0;
                for (int i = 0; i < 5; i++) {
                    int positionPlayer = boardState.getPosition(col + i, row );
                    if (positionPlayer == 1) eHuman++;
                    if (positionPlayer == 2) ePC++;
                }
                if (ePC * eHuman == 0) {
                    for (int i = 0; i < 5; i++) {
                        if (boardState.getPosition(col +i, row) == 0) {
                            if (ePC != 0) {
                                if (player == 1) boardMarkState.getEBoard()[col + i][row] += defendScore[ePC];
                                else boardMarkState.getEBoard()[col + i][row] += attackScore[ePC];
                            }
                            if (eHuman != 0) {
                                if (player == 1) boardMarkState.getEBoard()[col + i][row] += attackScore[eHuman];
                                else boardMarkState.getEBoard()[col + i][row] += defendScore[eHuman];
                            }
                            if(eHuman ==4 || ePC ==4) boardMarkState.getEBoard()[col + i][row] *=2;
                        }
                    }
                }

            }
        //duyet cheo xuong
        for(col = 0; col < boardState.getHeight() - 4; col++)
            for(row = 0; row < boardState.getWidth() -4; row++){
                ePC = 0;
                eHuman = 0;
                for (int i = 0; i < 5; i++) {
                    int positionPlayer = boardState.getPosition(col +i, row + i);
                    if (positionPlayer == 1) eHuman++;
                    if (positionPlayer == 2) ePC++;
                }
                if (ePC * eHuman == 0) {
                    for (int i = 0; i < 5; i++) {
                        if (boardState.getPosition(col +i, row + i) == 0) {
                            if (ePC != 0) {
                                if (player == 1) boardMarkState.getEBoard()[col +i][row + i] += defendScore[ePC];
                                else boardMarkState.getEBoard()[col +i][row + i] += attackScore[ePC];
                            }
                            if (eHuman != 0) {
                                if (player == 1) boardMarkState.getEBoard()[col +i][row + i] += attackScore[eHuman];
                                else boardMarkState.getEBoard()[col +i][row + i] += defendScore[eHuman];
                            }
                            if(eHuman ==4 || ePC ==4) boardMarkState.getEBoard()[col + i][row + i] *=2;
                        }
                    }
                }

            }
        // duyet cheo len
        for(col = boardState.getHeight() -1; col > 4; col--)
            for(row = 0; row < boardState.getWidth() -4; row++){
                ePC = 0;
                eHuman = 0;
                for (int i = 0; i < 5; i++) {
                    int positionPlayer = boardState.getPosition(col +i, row + i);
                    if (positionPlayer == 1) eHuman++;
                    if (positionPlayer == 2) ePC++;
                }
                if (ePC * eHuman == 0) {
                    for (int i = 0; i < 5; i++) {
                        if (boardState.getPosition(col - i, row + i) == 0) {
                            if (ePC != 0) {
                                if (player == 1) boardMarkState.getEBoard()[col - i][row + i] += defendScore[ePC];
                                else boardMarkState.getEBoard()[col - i][row + i] += attackScore[ePC];
                            }
                            if (eHuman != 0) {
                                if (player == 1) boardMarkState.getEBoard()[col - i][row + i] += attackScore[eHuman];
                                else boardMarkState.getEBoard()[col - i][row + i] += defendScore[eHuman];
                            }
                            if(eHuman ==4 || ePC ==4) boardMarkState.getEBoard()[col - i][row + i] *=2;
                        }
                    }
                }
            }
    }
    public void alphaBeta(int depth, int a, int b, BoardState boardState){

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
        return null;
    }
}
