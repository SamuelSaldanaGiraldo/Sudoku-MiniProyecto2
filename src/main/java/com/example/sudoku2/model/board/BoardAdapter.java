package com.example.sudoku2.model.board;

import java.util.List;

/**
 * Adaptador para usar la clase concreta Board
 * detrás de la interfaz IBoard
 */
public class BoardAdapter implements IBoard {

    private final Board board;

    public BoardAdapter() {
        this.board = new Board();
    }

    @Override
    public boolean isValid(int row, int col, int candidate) {
        return board.isValid(row, col, candidate);
    }

    @Override
    public void regenerateBoard() {
        board.regenerateBoard();
    }

    @Override
    public void lockCell(int row, int col) {
        board.lockCell(row, col);
    }

    @Override
    public void unlockCell(int row, int col) {
        board.unlockCell(row, col);
    }

    @Override
    public boolean isCellLocked(int row, int col) {
        return board.isCellLocked(row, col);
    }

    @Override
    public void unlockEmptyCells() {
        board.unlockEmptyCells();
    }

    @Override
    public void cleanBoard() {
        board.cleanBoard();
    }

    @Override
    public boolean hasUniqueSolution() {
        return board.hasUniqueSolution();
    }

    @Override
    public List<List<Integer>> getBoard() {
        return board.getBoard();
    }
}
