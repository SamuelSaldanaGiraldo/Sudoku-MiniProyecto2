package com.example.sudoku2.model.game;

import javafx.scene.control.TextField;

/**
 * Defines the contract for a Sudoku game class.
 * <p>
 * Any class that implements this interface must provide methods for starting the game,
 * accessing UI cells, retrieving the suggestion engine, and checking board completion.
 * </p>
 */
public interface IGame {

    /**
     * Initializes and starts the game logic.
     * <p>
     * Implementing classes should set up the game board, initialize UI components,
     * and attach event handlers for user interactions.
     * </p>
     */
    void startGame();

    /**
     * Returns the {@link TextField} at a specific cell in the board.
     *
     * @param row the row index of the cell
     * @param col the column index of the cell
     * @return the {@link TextField} at the specified position
     */
    TextField getTextFieldAt(int row, int col);

    /**
     * Returns an instance of {@link Game.SuggestionEngine} for providing hints
     * or suggestions for the next move.
     *
     * @return a {@link Game.SuggestionEngine} instance
     */
    Game.SuggestionEngine getSuggestionEngine();

    /**
     * Checks whether the board is complete according to Sudoku rules.
     *
     * @return true if the board is fully filled and valid; false otherwise
     */
    boolean isBoardComplete();
}
