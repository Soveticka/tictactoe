package cz.Soveticka;

public class Board {
    private String[][] board;
    private String[] alfa;

    public Board(int boardSize){
        board = new String[boardSize][boardSize];
        alfa = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
        boardFill();
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public String[] getAlfa() {
        return alfa;
    }

    public void boardFill(){
        for(int i=0;i < board.length; i++){
            for(int j=0;j < board.length; j++){
                board[i][j] = "_";
            }
        }
    }

    public void printBoard(){
        String export = "  ";
        if(board.length >= 10){
            export += " ";
        }
        for(int i=0; i < board.length; i++){
            if(i != board.length-1){
                export += alfa[i] + " ";
            }else{
                export += alfa[i] + "\n";
            }
        }
        for(int i=0;i < board.length; i++){
            if(board.length > 9){
                if(i+1 < 10){
                    export +=  " " + (i+1) + " ";
                }else{
                    export += i+1 + " ";
                }
            }else{
                export += i+1 + " ";
            }

            for(int j=0;j < board.length; j++){
                if(j != board.length-1){
                    export += board[i][j] + " ";
                }else{
                    export += board[i][j] + "\n";
                }
            }
        }
        System.out.println(export);
    }
}
