package com.example.sudoku2.model.board;

import java.util.List;

/**
 * Adapter class for using the concrete {@link Board} class
 * through the {@link IBoard} interface.
 * <p>
 * This class delegates all method calls to an internal {@link Board} instance,
 * allowing the rest of the application to interact with the board via the
 * {@link IBoard} interface without depending on the concrete implementation.
 * </p>
 */
public class BoardAdapter implements IBoard {

    /** Internal instance of the concrete Board class. */
    private final Board board;

    /**
     * Constructs a new {@code BoardAdapter} with a fresh {@link Board} instance.
     */
    public BoardAdapter() {
        this.board = new Board();
    }

    /**
     * Checks whether a candidate number can be placed at the given cell.
     *
     * @param row the row index
     * @param col the column index
     * @param candidate the number to place
     * @return true if the placement is valid; false otherwise
     */
    @Override
    public boolean isValid(int row, int col, int candidate) {
        return board.isValid(row, col, candidate);
    }

    /**
     * Regenerates the board with a new random configuration
     * while ensuring a unique solution.
     */
    @Override
    public void regenerateBoard() {
        board.regenerateBoard();
    }

    /**
     * Locks a cell so that it cannot be modified.
     *
     * @param row the row index
     * @param col the column index
     */
    @Override
    public void lockCell(int row, int col) {
        board.lockCell(row, col);
    }

    /**
     * Unlocks a previously locked cell.
     *
     * @param row the row index
     * @param col the column index
     */
    @Override
    public void unlockCell(int row, int col) {
        board.unlockCell(row, col);
    }

    /**
     * Checks whether a specific cell is locked.
     *
     * @param row the row index
     * @param col the column index
     * @return true if the cell is locked; false otherwise
     */
    @Override
    public boolean isCellLocked(int row, int col) {
        return board.isCellLocked(row, col);
    }

    /**
     * Unlocks all empty cells (cells with value 0) on the board.
     */
    @Override
    public void unlockEmptyCells() {
        board.unlockEmptyCells();
    }

    /**
     * Resets all cells on the board to empty and clears locked cells.
     */
    @Override
    public void cleanBoard() {
        board.cleanBoard();
    }

    /**
     * Checks whether the board has exactly one valid solution.
     *
     * @return true if the board has a unique solution; false otherwise
     */
    @Override
    public boolean hasUniqueSolution() {
        return board.hasUniqueSolution();
    }

    /**
     * Returns the current board state as a list of rows.
     *
     * @return the board represented as a {@link List} of {@link List} of integers
     */
    @Override
    public List<List<Integer>> getBoard() {
        return board.getBoard();
    }
}
