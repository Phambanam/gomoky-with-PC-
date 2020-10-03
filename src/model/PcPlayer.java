package model;

public class PcPlayer implements Player {
   private  int playerFlag = 2;
   private  BoardState boardState;
   private BoardMarkState boardMarkState;
   private  int x,y;

   public PcPlayer(BoardState boardState){
       this.boardState = boardState;
   }
    public static int maxDepth = 6; // do sau toi da
    public static int maxMove = 6;  // so o tiep theo dem xet toi da

    private int[] AScore = {0,4,27,256,1458};// Mang diem tan cong 0,4,28,256,2308
    private  int[] DScore = {0,2,9,99,769};  // Mang diem phong ngu 0,1,9,85,769
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
