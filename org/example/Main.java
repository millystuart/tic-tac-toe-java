package org.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    
    public static void main(String[] args) {
        // ----------------------- INITIALISATION STEPS -----------------------
        
        // Define scanner to take user input
        Scanner scanner = new Scanner(System.in);
        
        // Make a new grid to hold the state of the game
        Grid grid = new Grid();
        
        // Holds the available moves on the grid
        ArrayList<Integer> availableMoves = new ArrayList<Integer>();
        // Populate
        for (int i = 1; i < 10; i++) {
            availableMoves.add(i);
        }
        
        // Define symbol player will use to play
        NodeState playerSymbol = choosePlayerSymbol(scanner);
        // Give the computer the opposing symbol
        NodeState computerSymbol = (playerSymbol == NodeState.X) ? NodeState.O : NodeState.X;
        
        boolean hasWon = false;
        
        // Initialise isPlayerTurn to false for first turn (computer plays first)
        boolean isPlayerTurn = false;
        
        // At the very beginning of the game, output the empty grid as a reference
        
                
        // ---------------------------- GAME LOOP -----------------------------
        
        while (!hasWon && !availableMoves.isEmpty()) {
            if (isPlayerTurn == true) {
                int playerMove = getPlayerMove(scanner, availableMoves);
                
                // Once player move has been chosen and validated, remove the entry from availableMoves
                availableMoves.remove((Object) playerMove);
                
                grid.updateGridWithMove(playerMove, playerSymbol); 
                
                grid.displayGrid();
                
                isPlayerTurn = false;
                
                hasWon = grid.detectWin(playerSymbol);
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
        
        // Close the scanner
        scanner.close();
    }

    private static int getPlayerMove(Scanner scanner, ArrayList<Integer> availableMoves) {
        // Variable accessible across method to hold user's validated move (initialised to dummy value -1)
        int move = -1;
         
        System.out.println("Where would you like to play your next move? (1-9) \n(type HELP to view grid indexes)");
        String userInput = scanner.nextLine();
        
        // Output gridIndexes if user types HELP:
        if (userInput.equalsIgnoreCase("HELP")) {
            displayHelpGrid();
            return getPlayerMove(scanner, availableMoves);
        }
        else {
            try {
                move = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Illegal value inputted. Please ensure that your choice is a number.");
                return getPlayerMove(scanner, availableMoves);
            }
   
            if (move > 9 || move <= 0 ) {
                System.out.println("Illegal position on the grid. Please ensure that your choice is between 1 & 9 inclusive");
                return getPlayerMove(scanner, availableMoves);
            }
            
            if (!availableMoves.contains(move)) {
                System.out.println("The position you chose is already occupied by a symbol. Please choose an unoccupied space");
                return getPlayerMove(scanner, availableMoves);
            }
        }
        
        return move;
    }
    
    private static NodeState choosePlayerSymbol(Scanner scanner) {
        System.out.println("Would you like to play as a nought (O) or a cross (X)?");
        String userInput = scanner.nextLine().trim().toUpperCase();
        
        if (!userInput.equals("X") && !userInput.equals("O")) {
            System.out.println("Please choose between O or X only");
            return choosePlayerSymbol(scanner);
        }
        
        NodeState desiredSymbol = (userInput.equals("X")) ? NodeState.X : NodeState.O;
        return desiredSymbol;
    }
    
    private static void displayHelpGrid() {
        System.out.println(" " + "1" + " | " + "2" + " | " + "3");
        System.out.println("---+---+---");
        System.out.println(" " + "4" + " | " + "5" + " | " + "6");
        System.out.println("---+---+---");
        System.out.println(" " + "7" + " | " + "8" + " | " + "9");
    }
}