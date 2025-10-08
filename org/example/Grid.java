package org.example;

/**
 * Handles all operations pertaining to the grid. The grid is what holds the state of the game at any given point.
 */
public class Grid {
    
    private NodeState[][] grid;
    
    public Grid() {
        // Define grid as a 2D array of NodeStates with nine slots, each initialised to EMPTY
        grid = new NodeState[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid[row][col] = NodeState.EMPTY;
            }
        }
    }
    
    /**
     * Updates the grid with a given position to be placed on the grid.
     * @param position the integer value representing a location on the grid.
     * @param symbol the NodeState to be placed in the position.
     */
    public void updateGridWithMove(int position, NodeState symbol) {
        switch (position) {
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
        }
    }
    
    /**
     * Checks each row, column and diagonal to see if a passed symbol occurs three times in a row.
     * @param grid grid holding the current game state.
     * @param symbol the symbol of the last player who made a move.
     * @return true if a win is detected, or false otherwise.
     */
    public boolean detectWin(NodeState symbol) {
        // Start by checking every row and column of the grid
        for (int i = 0; i < 3; i++) {
            if (grid[i][0].equals(symbol) && grid[i][1].equals(symbol) && grid[i][2].equals(symbol)) return true;
            if (grid[0][i].equals(symbol) && grid[1][i].equals(symbol) && grid[2][i].equals(symbol)) return true;
        }
        
        // Finally, check diagonals, both with positive gradient and negative
        if (grid[0][0].equals(symbol) && grid[1][1].equals(symbol) && grid[2][2].equals(symbol)) return true;
        if (grid[0][2].equals(symbol) && grid[1][1].equals(symbol) && grid[2][0].equals(symbol)) return true;
        
        return false;
    }
    
    /**
     * Outputs a provided grid to the console.
     */
    public void displayGrid() {
        // Convert grid to correct char[][] format
        char charGrid[][] = nodestateToCharGrid();
        
        System.out.println(" " + charGrid[0][0] + " | " + charGrid[0][1] + " | " + charGrid[0][2]);
        System.out.println("---+---+---");
        System.out.println(" " + charGrid[1][0] + " | " + charGrid[1][1] + " | " + charGrid[1][2]);
        System.out.println("---+---+---");
        System.out.println(" " + charGrid[2][0] + " | " + charGrid[2][1] + " | " + charGrid[2][2]);
    }
    
    /**
     * Helper function to convert a grid of NodeStates to a grid of chars for outputting to the console.
     * @return the converted NodeState grid as a 2D-array of chars.
     */
    private char[][] nodestateToCharGrid() {
        char[][] charGrid = {{' ', ' ', ' '},
                             {' ', ' ', ' '},
                             {' ', ' ', ' '}};
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                switch (grid[row][col]) {
                    case X:
                        charGrid[row][col] = 'X';
                        break;
                    case O:
                        charGrid[row][col] = 'O';
                        break;
                    default:
                        break;
                }
            }
        }
        return charGrid;
    } 
}