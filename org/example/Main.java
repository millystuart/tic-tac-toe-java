package org.example;

import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        // ----------------------- INITIALISATION STEPS -----------------------
        
        // Create new grid object to hold the state of the game
        Grid grid = new Grid();
        
        // Create both human and computer player objects for the game
        HumanPlayer player      = new HumanPlayer(grid);
        ComputerPlayer computer = new ComputerPlayer(grid, player.getPlayerSymbol());
        
        // Holds the available moves on the grid
        ArrayList<Integer> availableMoves = new ArrayList<Integer>();
        // Populate
        for (int i = 1; i < 10; i++) {
            availableMoves.add(i);
        }
        
        boolean hasWon = false;
        
        // Initialise isPlayerTurn to false for first turn (computer plays first)
        boolean isPlayerTurn = false;        
                
        // ---------------------------- GAME LOOP -----------------------------
        
        while (!hasWon && !availableMoves.isEmpty()) {
            if (isPlayerTurn == true) {
                player.makeMove(availableMoves);
                                   
                isPlayerTurn = false;
                
                hasWon = grid.detectWin(player.getPlayerSymbol());
            }
            else {
                computer.makeMove(availableMoves);
                
                isPlayerTurn = true;
                
                hasWon = grid.detectWin(computer.getSymbol());
            }
        }
        
        if (hasWon == true) {
            if (!isPlayerTurn) {
                System.out.println("Congratulations! You beat the highly intelligent computer!");
            }
            else {
                System.out.println("GAME OVER- you were beat by the highly unintelligent computer. How impressive!");
            }
        }
        else {
            System.out.println("It is a draw!");
        }
    }
}