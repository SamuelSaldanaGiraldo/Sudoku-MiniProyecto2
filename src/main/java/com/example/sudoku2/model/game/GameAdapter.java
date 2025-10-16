package com.example.sudoku2.model.game;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Adapter class that implements the {@link IGame} interface
 * and delegates all functionality to an instance of {@link Game}.
 * <p>
 * This class serves as a bridge between the {@code IGame} interface
 * and the concrete {@code Game} implementation, allowing the use
 * of {@code Game} objects where an {@code IGame} reference is expected.
 */
public class GameAdapter implements IGame {

    /** The underlying Game instance to which operations are delegated. */
    private final Game game;

    /**
     * Constructs a new {@code GameAdapter} that wraps a {@link Game} instance.
     *
     * @param boardGridpane the {@link GridPane} representing the Sudoku board in the UI.
     */
    public GameAdapter(GridPane boardGridpane) {
        this.game = new Game(boardGridpane);
    }

    /**
     * Starts the Sudoku game.
     * <p>
     * Delegates the call to {@link Game#startGame()}.
     */
    @Override
    public void startGame() {
        game.startGame();
    }

    /**
     * Returns the {@link TextField} at the specified row and column of the Sudoku board.
     * <p>
     * Delegates the call to {@link Game#getTextFieldAt(int, int)}.
     *
     * @param row the row index of the desired cell.
     * @param col the column index of the desired cell.
     * @return the {@link TextField} corresponding to the given position.
     */
    @Override
    public TextField getTextFieldAt(int row, int col) {
        return game.getTextFieldAt(row, col);
    }

    /**
     * Returns the {@link Game.SuggestionEngine} associated with this game.
     * <p>
     * Delegates the call to {@link Game#getSuggestionEngine()}.
     *
     * @return the {@link Game.SuggestionEngine} instance.
     */
    @Override
    public Game.SuggestionEngine getSuggestionEngine() {
        return game.getSuggestionEngine();
    }

    /**
     * Checks if the current game board is complete.
     * <p>
     * This method delegates the check to the underlying {@code game} object.
     * </p>
     *
     * @return {@code true} if the board is complete (all cells are filled correctly),
     *         {@code false} otherwise.
     */
    @Override
    public boolean isBoardComplete() {
        return game.isBoardComplete();
    }
}
