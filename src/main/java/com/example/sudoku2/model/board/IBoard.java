package com.example.sudoku2.model.board;

import java.util.List;

/**
 * Defines the contract for a Sudoku board. Implementations must provide
 * methods for filling blocks and validating number placements.
 */
public interface IBoard {
    boolean isValid(int row, int col, int candidate);
    void regenerateBoard();
    void lockCell(int row, int col);
    void unlockCell(int row, int col);
    boolean isCellLocked(int row, int col);
    void unlockEmptyCells();
    void cleanBoard();
    boolean hasUniqueSolution();
    List<List<Integer>> getBoard();
}

