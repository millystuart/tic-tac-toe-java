package org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    
    public static void main(String[] args) {
        // ----------------------- INITIALISATION STEPS -----------------------
        
        // Create new grid object to hold the state of the game
        Grid grid = new Grid();
        
        // Create both human and computer player objects for the game
        HumanPlayer player = new HumanPlayer(grid);
        
        // Holds the available moves on the grid
        ArrayList<Integer> availableMoves = new ArrayList<Integer>();
        // Populate
        for (int i = 1; i < 10; i++) {
            availableMoves.add(i);
        }
        
        // Give the computer the opposing symbol
        NodeState computerSymbol = (player.getPlayerSymbol() == NodeState.X) ? NodeState.O : NodeState.X;
        
        boolean hasWon = false;
        
        // Initialise isPlayerTurn to false for first turn (computer plays first)
        boolean isPlayerTurn = false;
        
        // At the very beginning of the game, output the empty grid as a reference
        
                
        // ---------------------------- GAME LOOP -----------------------------
        
        while (!hasWon && !availableMoves.isEmpty()) {
            if (isPlayerTurn == true) {
                player.makeMove(availableMoves);
                                   
                isPlayerTurn = false;
                
                hasWon = grid.detectWin(player.getPlayerSymbol());
            }
            else {
                // Computer's turn!
                System.out.println("The computer is now taking its turn...");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                // Computer will decide randomly based on the available positions (it's very smart)
                Random random = new Random();
                int randomIndex = random.nextInt(availableMoves.size());
                int computerMove = availableMoves.get(randomIndex);
                availableMoves.remove(randomIndex);
                
                grid.updateGridWithMove(computerMove, computerSymbol);
                
                grid.displayGrid();
                
                isPlayerTurn = true;
                
                hasWon = grid.detectWin(computerSymbol);
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