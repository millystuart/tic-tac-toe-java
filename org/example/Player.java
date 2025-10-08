package org.example;

import java.util.ArrayList;

/**
 * Dictates that all players must have a way of making a move using the remaining moves available.
 */
public interface Player {    
    public void makeMove(ArrayList<Integer> availableMoves);
}