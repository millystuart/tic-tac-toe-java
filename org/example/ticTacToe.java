package org.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        
        // Define ArrayList to hold the open moves on the grid (all are available by default
        ArrayList<Integer> availableMoves = new ArrayList<Integer>();
        // Populate
        for (int i = 1; i < 10; i++) {
            availableMoves.add(i);
        }
        
        // Initialise isPlayerTurn to True for first turn
        boolean isPlayerTurn = true;
                
        // ---------------------------- GAME LOOP -----------------------------
        
        while (!hasWon && !availableMoves.isEmpty()) {
            
            if (isPlayerTurn == true) {
                int playerMove = getPlayerMove(scanner, availableMoves);
                // Once player move has been chosen and validated, remove the entry from availableMoves
                availableMoves.remove((Object) playerMove);
                
                updateGridWithMove(grid, playerMove, playerSymbol); 
                
                outputGrid(grid);
                
                isPlayerTurn = false;
                
                hasWon = checkForWin(grid, playerSymbol);
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
                
                updateGridWithMove(grid, computerMove, computerSymbol);
                
                outputGrid(grid);
                
                isPlayerTurn = true;
                
                hasWon = checkForWin(grid, computerSymbol);
            }
        }
        
        if (isPlayerTurn == false) {
            System.out.println("Congratulations! You beat the highly intelligent computer!");
        }
        else {
            System.out.println("GAME OVER- you were beat by the highly unintelligent computer. How impressive!");
        }
    }
    
    private static boolean checkForWin(char[][] grid, char symbol) {
        
        // Start by checking every row of the grid
        for (int row = 0; row < 3; row++) {
            ArrayList<Character> rowCheck = new ArrayList<Character>();
            
            for (int col = 0; col < 3; col++) {
                char charAtPos = grid[row][col];
                
                if (charAtPos == symbol) {
                    rowCheck.add(charAtPos);
                }
            }
            
            if (rowCheck.size() == 3) {
                return true;
            }
        }
        
     // Next check every column of the grid
        for (int col = 0; col < 3; col++) {
            ArrayList<Character> colCheck = new ArrayList<Character>();
            
            for (int row = 0; row < 3; row++) {
                char charAtPos = grid[row][col];
                
                if (charAtPos == symbol) {
                    colCheck.add(charAtPos);
                }
            }
            
            if (colCheck.size() == 3) {
                return true;
            }
        }
        
        // Finally, check diagonals, both with positive gradient and negative
        ArrayList<Character> diagCheckPos = new ArrayList<Character>();
        for (int col = 0; col < 3; col++) {
            char charAtPos = grid[2-col][col];
            if (charAtPos == symbol) {
                diagCheckPos.add(charAtPos);
            }
        }
        
        if (diagCheckPos.size() == 3) {
            return true;
        }  
        
        ArrayList<Character> diagCheckNeg = new ArrayList<Character>();
        for (int rowCol = 0; rowCol < 3; rowCol++) {
            char charAtPos = grid[rowCol][rowCol];
            if (charAtPos == symbol) {
                diagCheckNeg.add(charAtPos);
            }
        }
        
        if (diagCheckNeg.size() == 3) {
            return true;
        }
        
        return false;
    }

    private static int getPlayerMove(Scanner scanner, ArrayList<Integer> availableMoves) {
        
        // Variable accessible across method to hold user's validated move (initialised to dummy value -1)
        int move = -1;
         
        System.out.println("Where would you like to play your next move? (1-9) \n(type HELP to view grid indexes)");
        String userInput = scanner.nextLine();
        
        // Output gridIndexes if user types HELP:
        if (userInput.equals("HELP")) {
            outputGrid(GRID_INDEXES);
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
    
    private static void updateGridWithMove(char[][] grid, int move, char symbol) {
        
        switch (move) {
            case 1:
                grid[0][0] = symbol;
                break;
            case 2:
                grid[0][1] = symbol;
                break;
            case 3:
                grid[0][2] = symbol;
                break;
            case 4:
                grid[1][0] = symbol;
                break;
            case 5:
                grid[1][1] = symbol;
                break;
            case 6:
                grid[1][2] = symbol;
                break;
            case 7:
                grid[2][0] = symbol;
                break;
            case 8:
                grid[2][1] = symbol;
                break;
            case 9:
                grid[2][2] = symbol;
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