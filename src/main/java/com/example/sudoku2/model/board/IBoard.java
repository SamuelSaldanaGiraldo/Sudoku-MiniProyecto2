package com.example.sudoku2.model.board;

import java.util.List;

/**
 * Defines the contract for a Sudoku board.
 * <p>
 * Implementations of this interface must provide methods for validating
 * number placements, managing locked cells, regenerating the board,
 * and checking for uniqueness of solutions.
 * </p>
 */
public interface IBoard {
    /**
     * Checks whether a candidate number can be placed at the specified cell
     * according to Sudoku rules (no duplicates in row, column, or block).
     *
     * @param row the row index
     * @param col the column index
     * @param candidate the number to place
     * @return true if the placement is valid; false otherwise
     */
    boolean isValid(int row, int col, int candidate);

    /**
     * Regenerates the board with a new random configuration,
     * typically ensuring a unique solution.
     */
    void regenerateBoard();

    /**
     * Locks a cell so it cannot be modified by the player.
     *
     * @param row the row index
     * @param col the column index
     */
    void lockCell(int row, int col);

    /**
     * Unlocks a previously locked cell.
     *
     * @param row the row index
     * @param col the column index
     */
    void unlockCell(int row, int col);

    /**
     * Checks whether a specific cell is locked.
     *
     * @param row the row index
     * @param col the column index
     * @return true if the cell is locked; false otherwise
     */
    boolean isCellLocked(int row, int col);

    /**
     * Unlocks all empty cells (cells with value 0) on the board.
     */
    void unlockEmptyCells();

    /**
     * Clears the board, setting all cells to empty and removing all locked cells.
     */
    void cleanBoard();

    /**
     * Checks whether the current board has exactly one valid solution.
     *
     * @return true if the board has a unique solution; false otherwise
     */
    boolean hasUniqueSolution();

    /**
     * Returns the current state of the board.
     *
     * @return a list of lists representing the board
     */
    List<List<Integer>> getBoard();
}

