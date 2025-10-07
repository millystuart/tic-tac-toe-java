package org.example;

import java.util.Scanner;

public class ticTacToe {
    
    // This is an example grid used to show the references to each node
    static char[][] gridIndexes = {{'1', '2', '3'},
                                   {'4', '5', '6'},
                                   {'7', '8', '9'}};
    

    public static void main(String[] args) {  
        // Define 2D array that will hold the state of the grid.
        // A grid node can hold X, O or ' '
        char[][] grid = {{' ', ' ', ' '},
                         {' ', ' ', ' '},
                         {' ', ' ', ' '}};
        
        // Define scanner to take user input
        Scanner scanner = new Scanner(System.in);
           
        fetchMove(scanner);
        scanner.close();
        
        System.out.println("Yes, we are freeeeeeeeeee from recursion");
    }





    private static void fetchMove(Scanner scanner) {
        
        // Gather input
        System.out.println("Where would you like to play your next move? (1-9) \n(type HELP to view grid indexes)");
        String userInput = scanner.nextLine();
        
        // Output gridIndexes if user types HELP:
        if (userInput.equals("HELP")) {
            outputGrid(gridIndexes);
            fetchMove(scanner);
            return;
        }
        else {
            // Initialise userIndex with dummy value
            int userIndex = -1;
            
            try {
                userIndex = Integer.parseInt(userInput);
                
            } catch (NumberFormatException e) {
                System.out.println("Illegal value inputted. Please ensure that your choice is a number.");
                fetchMove(scanner);
                return;
            }
            
            if (userIndex > 9 || userIndex <= 0 ) {
                System.out.println("Illegal position on the grid. Please ensure that your choice is between 1 & 9 inclusive");
                fetchMove(scanner);
                return;
            }
        }
        return;
    }
    
    private static void outputGrid(char[][] grid) {
        System.out.println(" " + grid[0][0] + " | " + grid[0][1] + " | " + grid[0][2]);
        System.out.println("---+---+---");
        System.out.println(" " + grid[1][0] + " | " + grid[1][1] + " | " + grid[1][2]);
        System.out.println("---+---+---");
        System.out.println(" " + grid[2][0] + " | " + grid[2][1] + " | " + grid[2][2]);
    }
    
}