package org.example;

import java.util.ArrayList;

/**
 * Handles all of the logic needed to play the game.
 */
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
        isPlayerTurn = false; // Computer will make the first move
    }
    
    /**
     * Handles the logic behind the game loop, dictating turn order and variable reassignments.
     * Also runs the end-game sequence once either a winner has been detected or there are no remaining moves.
     */
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
        
    /**
     * Outputs relevant message to the console depending on how the end game sequence has been reached.
     * Also closes the scanner in the player object.
     */
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
    
    /**
     * Helper function used to initialise the remainingMoves ArrayList with numbers between 1-9 (since all 9 nodes are
     * available at the beginning of the game).
     * @return An ArrayList populated with integers 1-9.
     */
    private ArrayList<Integer> populateRemainingMoves() {
        ArrayList<Integer> initialisedRemainingMoves = new ArrayList<Integer>();
        
        for (int i = 1; i < 10; i++) {
            initialisedRemainingMoves.add(i);
        }
        
        return initialisedRemainingMoves;
    }
}