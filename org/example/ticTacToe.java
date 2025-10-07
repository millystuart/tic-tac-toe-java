package org.example;

import java.util.Scanner;

public class ticTacToe {
    
    // This is an example grid used to show the user how to reference each node
    final static char[][] GRID_INDEXES = {{'1', '2', '3'},
                                          {'4', '5', '6'},
                                          {'7', '8', '9'}};

    public static void main(String[] args) {
        
        // Define 2D array that will hold the state of the grid.
        // A grid node can hold X, O or ' '
        char[][] grid = {{' ', ' ', ' '},
                         {' ', ' ', ' '},
                         {' ', ' ', ' '}};
        
        // ----------------------- INITIALISATION STEPS -----------------------
        
        // Define scanner to take user input
        Scanner scanner = new Scanner(System.in);
        
        // Define symbol player will use to play (' ' dummy value as default)
        char playerSymbol = choosePlayerSymbol(scanner);
        // Give the computer the opposing symbol
        char computerSymbol = (playerSymbol == 'X') ? 'O' : 'X';
        
        // Set win tracking boolean to false for initialisation
        boolean hasWon = false;
        
        // Initialise isPlayerTurn to True for first turn
        boolean isPlayerTurn = true;
                
        // ---------------------------- GAME LOOP -----------------------------
        
        while (hasWon == false) {
            
            int playerMove = getPlayerMove(scanner);
            
            updateGridWithMove(grid, playerMove, playerSymbol);
            
            outputGrid(grid);
        }
    }

    private static int getPlayerMove(Scanner scanner) {
        
        // Variable accessible across method to hold user's validated move (initialised to dummy value -1)
        int move = -1;
         
        System.out.println("Where would you like to play your next move? (1-9) \n(type HELP to view grid indexes)");
        String userInput = scanner.nextLine();
        
        // Output gridIndexes if user types HELP:
        if (userInput.equals("HELP")) {
            outputGrid(GRID_INDEXES);
            return getPlayerMove(scanner);
        }
        else {
            
            try {
                move = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Illegal value inputted. Please ensure that your choice is a number.");
                return getPlayerMove(scanner);
            }
            
            if (move > 9 || move <= 0 ) {
                System.out.println("Illegal position on the grid. Please ensure that your choice is between 1 & 9 inclusive");
                return getPlayerMove(scanner);
            }
        }
        
        return move;
    }
    
    private static void updateGridWithMove(char[][] grid, int playerMove, char playerSymbol) {
        
        switch (playerMove) {
            case 1:
                grid[0][0] = playerSymbol;
                break;
            case 2:
                grid[0][1] = playerSymbol;
                break;
            case 3:
                grid[0][2] = playerSymbol;
                break;
            case 4:
                grid[1][0] = playerSymbol;
                break;
            case 5:
                grid[1][1] = playerSymbol;
                break;
            case 6:
                grid[1][2] = playerSymbol;
                break;
            case 7:
                grid[2][0] = playerSymbol;
                break;
            case 8:
                grid[2][1] = playerSymbol;
                break;
            case 9:
                grid[2][2] = playerSymbol;
                break;
            default:
                System.out.println("ERROR: none of the cases were entered, despite validation");
                break;
        }
    }
    
    private static char choosePlayerSymbol(Scanner scanner) {
        
        System.out.println("Would you like to play as a nought (O) or a cross (X)?");
        String userInput = scanner.nextLine().trim();
        userInput = userInput.toUpperCase();
        
        if (!userInput.equals("X") && !userInput.equals("O")) {
            System.out.println("Please choose between O or X only");
            return choosePlayerSymbol(scanner);
        }
        
        return userInput.charAt(0);
    }
    
    private static void outputGrid(char[][] grid) {
        
        System.out.println(" " + grid[0][0] + " | " + grid[0][1] + " | " + grid[0][2]);
        System.out.println("---+---+---");
        System.out.println(" " + grid[1][0] + " | " + grid[1][1] + " | " + grid[1][2]);
        System.out.println("---+---+---");
        System.out.println(" " + grid[2][0] + " | " + grid[2][1] + " | " + grid[2][2]);
    }
}