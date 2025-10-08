package org.example;

import java.util.ArrayList; 
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Handles computer-specific operations.
 */
public class ComputerPlayer implements Player {
    
    private Grid grid;
    private NodeState symbol;
    
    public NodeState getSymbol() {
        return symbol;
    }

    public ComputerPlayer(Grid grid, NodeState playerSymbol) {
        this.grid = grid;
        // Set the computer's symbol to the remaining symbol after the player's choice
        symbol = (playerSymbol == NodeState.X) ? NodeState.O : NodeState.X;
    }
    
    /**
     * Handles the logic behind the computer's turn.
     */
    @Override
    public void makeMove(ArrayList<Integer> availableMoves) {
        System.out.println("The computer is now taking its turn...");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Computer will decide where to play randomly based on the available positions (it's very smart)
        Random random = new Random();
        int randomIndex = random.nextInt(availableMoves.size());
        int computerMove = availableMoves.get(randomIndex);
        availableMoves.remove(randomIndex);
        grid.updateGridWithMove(computerMove, symbol);
        grid.displayGrid();
    }
}