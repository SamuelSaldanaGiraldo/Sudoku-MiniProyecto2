package com.example.sudoku2.model.board;

import java.util.*;

/**
 * Represents a 6x6 Sudoku board divided into 2x3 blocks.
 * <p>
 * Each 2x3 block contains exactly two pre-filled numbers (from 1 to 6) placed randomly
 * such that no number is repeated in any row, column, or block across the board.
 * Empty cells are represented with 0. The board is internally represented as a
 * {@link List} of {@link List} of {@link Integer}.
 * </p>
 * <p>
 * The board generation uses a backtracking algorithm that fills the board block by block
 * and ensures a unique solution. Locked cells are those that were pre-filled and cannot
 * be changed during gameplay.
 * </p>
 * <p>
 * Java JDK 17.
 * </p>
 */
public class Board implements IBoard {
    // Board dimensions and block dimensions.
    private final int SIZE = 6;
    private final int BLOCK_ROWS = 2;
    private final int BLOCK_COLS = 3;

    // Number of block rows and block columns.
    private final int TOTAL_BLOCK_ROWS = SIZE / BLOCK_ROWS; // 6/2 = 3
    private final int TOTAL_BLOCK_COLS = SIZE / BLOCK_COLS; // 6/3 = 2
    private final int TOTAL_BLOCKS = TOTAL_BLOCK_ROWS * TOTAL_BLOCK_COLS; // 3 * 2 = 6

    // The board represented as a List of Lists (each inner list is a row)
    private final List<List<Integer>> board;

    // Celdas iniciales bloqueadas (formato "r,c")
    private final Set<String> lockedCells = new HashSet<>();

    private final Random random = new Random();

    // Limit of attempts to create a board
    private final int MAX_GENERATION_ATTEMPTS = 2000;

    /**
     * Constructs a new Board object, initializing all cells to 0 and generating
     * a board with two pre-filled numbers per 2x3 block.
     */
    public Board() {
        board = new ArrayList<>();
        // Initialize the board with zeros.
        for (int i = 0; i < SIZE; i++) {
            List<Integer> row = new ArrayList<>(Collections.nCopies(SIZE, 0));
            board.add(row);
        }

        // Attempt to fill each block with 2 valid numbers.
        regenerateBoard();
    }

    /**
     * Regenerates the board until a valid board with a unique solution is found.
     * <p>
     * This method attempts up to {@link #MAX_GENERATION_ATTEMPTS} times. If a unique
     * solution cannot be generated, it keeps the last generated board and logs a warning.
     * </p>
     */
    public void regenerateBoard() {
        int attempts = 0;
        boolean success = false;

        while (attempts < MAX_GENERATION_ATTEMPTS && !success) {
            attempts++;
            cleanBoard();
            lockedCells.clear();

            if (!fillBlocksRandomTwoPerBlock()) {
                continue;
            }

            markInitialLockedCells();

            if (hasUniqueSolution()) {
                success = true;
                break;
            } else {

            }
        }

        if (!success) {
            System.out.println("Warning: couldn't generate unique-solution board in " + MAX_GENERATION_ATTEMPTS + " attempts. Using last generated board.");
        }
    }

    /**
     * Fills each 2x3 block with two valid numbers randomly, ensuring no duplicates
     * in rows, columns, or blocks.
     *
     * @return true if all blocks were successfully filled; false otherwise
     */
    public boolean fillBlocksRandomTwoPerBlock() {
        for (int blockIndex = 0; blockIndex < TOTAL_BLOCKS; blockIndex++) {
            int blockRow = blockIndex / TOTAL_BLOCK_COLS;
            int blockCol = blockIndex % TOTAL_BLOCK_COLS;

            int startRow = blockRow * BLOCK_ROWS; // 0,2,4
            int startCol = blockCol * BLOCK_COLS; // 0,3

            int filled = 0;
            int innerAttempts = 0;

            int maxInnerAttempts = 200;

            while (filled < 2 && innerAttempts < maxInnerAttempts) {
                innerAttempts++;

                int r = startRow + random.nextInt(BLOCK_ROWS); // 0..1 offset
                int c = startCol + random.nextInt(BLOCK_COLS); // 0..2 offset

                if (board.get(r).get(c) != 0) continue; // ya ocupado

                List<Integer> nums = new ArrayList<>();
                for (int n = 1; n <= SIZE; n++) nums.add(n);
                Collections.shuffle(nums, random);

                boolean placed = false;
                for (int num : nums) {
                    if (isValid(r, c, num)) {
                        board.get(r).set(c, num);
                        placed = true;
                        filled++;
                        break;
                    }
                }

            }

            if (filled < 2) {
                return false; // Failed to fill block
            }
        }
        return true;
    }

    /**
     * Marks all non-zero cells as locked (initial) cells.
     */
    private void markInitialLockedCells() {
        lockedCells.clear();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board.get(r).get(c) != 0) {
                    lockedCells.add(key(r, c));
                }
            }
        }
    }

    /**
     * Checks if a number can be placed at a given cell without violating
     * Sudoku rules (row, column, and block uniqueness).
     *
     * @param row       the row index
     * @param col       the column index
     * @param candidate the number to place
     * @return true if the placement is valid; false otherwise
     */
    @Override
    public boolean isValid(int row, int col, int candidate) {
        // Check the current row for an existing occurrence of the candidate.
        for (int j = 0; j < SIZE; j++) {
            if (board.get(row).get(j) == candidate) {
                return false;
            }
        }
        // Check the current column for an existing occurrence of the candidate.
        for (int i = 0; i < SIZE; i++) {
            if (board.get(i).get(col) == candidate) {
                return false;
            }
        }

        // Check the current 2x3 Block for an existing occurrence of the candidate
        int startRow = (row / 2) * 2;
        int startCol = (col / 3) * 3;

        for (int i = startRow; i < startRow + 2; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board.get(i).get(j) == candidate) return false;
            }
        }

        return true;
    }

    /**
     * Generates a key string for a cell in the format "row,col".
     */
    private String key(int row, int col) {
        return row + "," + col;
    }

    /**
     * Locks a specific cell so it cannot be modified.
     */
    public void lockCell(int row, int col) {
        lockedCells.add(key(row, col));
    }

    /**
     * Unlocks a specific cell.
     */
    public void unlockCell(int row, int col) {
        lockedCells.remove(key(row, col));
    }

    /**
     * Checks whether a cell is locked (pre-filled).
     */
    public boolean isCellLocked(int row, int col) {
        return lockedCells.contains(key(row, col));
    }

    /** Unlocks all empty cells (cells with value 0). */
    public void unlockEmptyCells() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (board.get(row).get(col) == 0) {
                    // Unlocks cell when empty
                    unlockCell(row, col);
                }
            }
        }
    }

    /** Sets all cells to 0 and clears all locked cells. */
    public void cleanBoard() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                board.get(i).set(j, 0);
            }
        }
        lockedCells.clear();
    }

    /**
     * Checks whether the board has a unique solution.
     *
     * @return true if there is exactly one solution; false otherwise
     */
    public boolean hasUniqueSolution() {
        int[] count = {0};
        // Hacemos una copia temporal del tablero? No es necesario porque el backtracking restaura.
        solveAndCount(count);
        return count[0] == 1;
    }

    /**
     * Backtracking helper method that counts the number of solutions.
     * Stops early if more than one solution is found.
     *
     * @param count an array of size 1 used to store the number of solutions
     * @return true if more than one solution is found (stop recursion); false otherwise
     */
    private boolean solveAndCount(int[] count) {
        if (count[0] > 1) return true; // found more than one
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board.get(r).get(c) == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(r, c, num)) {
                            board.get(r).set(c, num);
                            boolean stop = solveAndCount(count);
                            board.get(r).set(c, 0); // backtrack
                            if (stop) return true;
                        }
                    }
                    return false;
                }
            }
        }
        count[0]++;
        return count[0] > 1;
    }

    /**
     * Returns the generated board.
     *
     * @return a list of lists representing the board.
     */
    public List<List<Integer>> getBoard() {
        return board;
    }
}
