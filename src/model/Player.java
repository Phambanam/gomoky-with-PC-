package model;

public interface Player {
    int getPlayerFlag();
    void setPlayerFlag(int playerFlag);
    BoardState getBoardState();
    Point movePoint(int player);
}
