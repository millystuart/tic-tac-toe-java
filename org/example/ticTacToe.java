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
        
        // Define scanner to take user input
        Scanner scanner = new Scanner(System.in);
                  
        getPlayerMove(grid, scanner);
        
        outputGrid(grid);
    }

    private static void getPlayerMove(char[][] grid, Scanner scanner) {
        
        // Variable accessible across method to hold user's validated move (initialised to dummy value -1)
        int move = -1;
         
        System.out.println("Where would you like to play your next move? (1-9) \n(type HELP to view grid indexes)");
        String userInput = scanner.nextLine();
        
        // Output gridIndexes if user types HELP:
        if (userInput.equals("HELP")) {
            outputGrid(GRID_INDEXES);
            getPlayerMove(grid, scanner);
            return;
        }
        else {
            
            try {
                move = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Illegal value inputted. Please ensure that your choice is a number.");
                getPlayerMove(grid, scanner);
                return;
            }
            
            if (move > 9 || move <= 0 ) {
                System.out.println("Illegal position on the grid. Please ensure that your choice is between 1 & 9 inclusive");
                getPlayerMove(grid, scanner);
                return;
            }
        }
        
        updateGridWithMove(grid, move);
        return;
    }
    
    private static void updateGridWithMove(char[][] grid, int playerMove) {
        
        switch (playerMove) {
            case 1:
                grid[0][0] = 'X';
                break;
            case 2:
                grid[0][1] = 'X';
                break;
            case 3:
                grid[0][2] = 'X';
                break;
            case 4:
                grid[1][0] = 'X';
                break;
            case 5:
                grid[1][1] = 'X';
                break;
            case 6:
                grid[1][2] = 'X';
                break;
            case 7:
                grid[2][0] = 'X';
                break;
            case 8:
                grid[2][1] = 'X';
                break;
            case 9:
                grid[2][2] = 'X';
                break;
            default:
                System.out.println("ERROR: none of the cases were entered, despite validation");
                break;
        }
    }
    
    private static void outputGrid(char[][] grid) {
        
        System.out.println(" " + grid[0][0] + " | " + grid[0][1] + " | " + grid[0][2]);
        System.out.println("---+---+---");
        System.out.println(" " + grid[1][0] + " | " + grid[1][1] + " | " + grid[1][2]);
        System.out.println("---+---+---");
        System.out.println(" " + grid[2][0] + " | " + grid[2][1] + " | " + grid[2][2]);
    }
}