package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private Grid grid;
    private NodeState symbol;
    private Scanner scanner;
    
    public NodeState getPlayerSymbol() {
        return symbol;
    }
    
    public HumanPlayer(Grid grid) {
        this.grid = grid;
        scanner = new Scanner(System.in);        
        symbol = chooseSymbol(scanner);
    }

    @Override
    public void makeMove(ArrayList<Integer> availableMoves) {
        int move = getPlayerMove(scanner, availableMoves);
        
        // Once player move has been chosen and validated, remove the entry from availableMoves
        availableMoves.remove((Object) move);
        
        grid.updateGridWithMove(move, symbol); 
        
        grid.displayGrid();
    }
    
    private NodeState chooseSymbol(Scanner scanner) {
        System.out.println("Would you like to play as a nought (O) or a cross (X)?");
        String userInput = scanner.nextLine().trim().toUpperCase();
        
        if (!userInput.equals("X") && !userInput.equals("O")) {
            System.out.println("Please choose between O or X only");
            return chooseSymbol(scanner);
        }
        
        NodeState desiredSymbol = (userInput.equals("X")) ? NodeState.X : NodeState.O;
        return desiredSymbol;
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
    
    private static void displayHelpGrid() {
        System.out.println(" " + "1" + " | " + "2" + " | " + "3");
        System.out.println("---+---+---");
        System.out.println(" " + "4" + " | " + "5" + " | " + "6");
        System.out.println("---+---+---");
        System.out.println(" " + "7" + " | " + "8" + " | " + "9");
    }
    
    public void closeScanner() {
        scanner.close();
    }
}
