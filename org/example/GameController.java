package org.example;

import java.util.ArrayList;

public class GameController {
    private Grid grid;
    private HumanPlayer player;
    private ComputerPlayer computer;
    private ArrayList<Integer> remainingMoves;
    private boolean isWinner;
    private boolean isPlayerTurn;
    
    public GameController() {
        grid = new Grid();
        player = new HumanPlayer(grid);
        computer = new ComputerPlayer(grid, player.getPlayerSymbol());
        remainingMoves = populateRemainingMoves();
        isWinner = false;
        isPlayerTurn = false;
    }

    public void gameLoop() {
                        
        while (!isWinner && !remainingMoves.isEmpty()) {
            if (isPlayerTurn == true) {
                player.makeMove(remainingMoves);
                isPlayerTurn = false;
                isWinner = grid.detectWin(player.getPlayerSymbol());
            }
            else {
                computer.makeMove(remainingMoves);
                isPlayerTurn = true;
                isWinner = grid.detectWin(computer.getSymbol());
            }
        }
        endGameSequence();
    }
    
    private void endGameSequence() {
        if (isWinner == true) {
            if (!isPlayerTurn) {
                System.out.println("Congratulations! You beat the highly intelligent computer!");
            }
            else {
                System.out.println("GAME OVER- you lost to the highly unintelligent computer. How impressive!");
            }
        }
        else {
            System.out.println("It is a draw!");
        }
        player.closeScanner();
    }
    
    private ArrayList<Integer> populateRemainingMoves() {
        ArrayList<Integer> initialisedRemainingMoves = new ArrayList<Integer>();
        
        for (int i = 1; i < 10; i++) {
            initialisedRemainingMoves.add(i);
        }
        
        return initialisedRemainingMoves;
    }
}