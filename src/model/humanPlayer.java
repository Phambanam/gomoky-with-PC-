package model;

public class humanPlayer implements Player {
    private int playerFlag = 1;
    private BoardState boardState;
    public  humanPlayer(BoardState boardState){
        this.boardState = boardState;
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
