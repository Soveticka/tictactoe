package cz.Soveticka;

import java.util.Scanner;

public class Tictactoe {
    private Board board;
    private int places;
    private String[] playerChars;
    private int maxPlaces;
    private Scanner scr;
    private boolean win;
    private String[][] gameBoard;
    private boolean draw;
    private int foundInRow;
    private int foundInColumn;
    private int foundInDiagonal;

    public Tictactoe(int boardSize){
        board = new Board(boardSize);
        gameBoard = board.getBoard();
        int turns = 0;
        playerChars = new String[]{"O","X"};
        maxPlaces = boardSize * boardSize;
        scr = new Scanner(System.in);
        win = false;
        draw = false;
    }

    public void startGame(){
        board.printBoard();
        while(true){
            if(win){
                break;
            }
            if(places == maxPlaces){
                System.out.println("Remíza");
                break;
            }
            nextTurn();
        }
    }

    private void nextTurn(){
        System.out.println("Player 1: ");
        checkInput(scr.nextLine(), playerChars[0]);
        places++;
        if(places >= gameBoard.length*2-1){
            checkWin(playerChars[0]);
        }
        if(!win){
            System.out.println("Player 2: ");
            checkInput(scr.nextLine(), playerChars[1]);
            places++;
            if(places >= gameBoard.length*2){
                checkWin(playerChars[0]);
            }
        }
    }

    private void checkInput(String input, String playerChar){
        String[] pInput = input.split(" ");
        int x = 0;
        int y = Integer.parseInt(pInput[1])-1;

        for(int i=0; i < board.getAlfa().length; i++){
            if(board.getAlfa()[i].equals(pInput[0].toUpperCase())){
                x = i;
            }
        }

        if(x < gameBoard.length && y < gameBoard.length){
            if(gameBoard[y][x].equals("_")) {
                gameBoard[y][x] = playerChar;
                updateBoard();
            }else{
                System.out.println("Invalid input, try again: ");
                checkInput(scr.nextLine(), playerChar);
            }
        }else{
            System.out.println("Invalid input, try again: ");
            checkInput(scr.nextLine(), playerChar);
        }
    }

    private void updateBoard(){
        board.setBoard(gameBoard);
        board.printBoard();
    }

    private void checkWin(String playerChar){
        //check rows for win

        for (String[] strings : gameBoard) { //row
            if (win) {
                break;
            }
            for (int j = 0; j < gameBoard.length; j++) { //positions in row
                if (playerChar.equals("O")) {
                    if (strings[j].equals(playerChars[1])) {
                        break;
                    }
                } else {
                    if (strings[j].equals(playerChars[0])) {
                        break;
                    }
                }
                if (strings[j].equals(playerChar)) {
                    foundInRow++;
                }
            }
            if (foundInRow == gameBoard.length) {
                win = true;
                System.out.println("Výhra!");
                break;
            }
            foundInRow = 0;
        }

        //check columns for win
        if(!win) {
            for (int i = 0; i < gameBoard.length; i++) { //column
                if(win){
                    break;
                }
                for (int j = 0; j < gameBoard.length; j++) { //positions in column
                    if (gameBoard[j][i].equals(playerChar)) {
                        foundInColumn++;
                    }
                    if (playerChar.equals("O")) {
                        if (gameBoard[j][i].equals(playerChars[1])) {
                            break;
                        }
                    } else {
                        if (gameBoard[j][i].equals(playerChars[0])) {
                            break;
                        }
                    }
                }

                if (foundInColumn == gameBoard.length) {
                    win = true;
                    break;
                }
                foundInColumn = 0;
            }
        }

        //check diagonals for win
        if(!win) {
            for (int i = 0; i < gameBoard.length; i++) {
                if(win){
                    break;
                }
                if (gameBoard[i][i].equals(playerChar)) {
                    foundInDiagonal++;
                }
                if (playerChar.equals("O")) {
                    if (gameBoard[i][i].equals(playerChars[1])) {
                        break;
                    }
                } else {
                    if (gameBoard[i][i].equals(playerChars[0])) {
                        break;
                    }
                }

                if (foundInDiagonal == gameBoard.length) {
                    win = true;
                    break;
                }

            }
            foundInDiagonal = 0;

            for (int i = gameBoard.length - 1; i > 0; i--) {
                if(win){
                    break;
                }
                if (gameBoard[i][i].equals(playerChar)) {
                    foundInDiagonal++;
                }
                if (playerChar.equals("O")) {
                    if (gameBoard[i][i].equals(playerChars[1])) {
                        break;
                    }
                } else {
                    if (gameBoard[i][i].equals(playerChars[0])) {
                        break;
                    }
                }

                if (foundInDiagonal == gameBoard.length) {
                    win = true;
                    break;
                }

            }
            foundInDiagonal = 0;
        }
    }
}
