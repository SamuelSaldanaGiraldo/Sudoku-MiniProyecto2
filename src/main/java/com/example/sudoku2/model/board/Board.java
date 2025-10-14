package com.example.sudoku2.model.board;

import java.util.*;

/**
 * This class generates a 6x6 board divided into 2x3 blocks.
 * In each 2x3 block exactly one cell is assigned a random number (from 1 to 6),
 * and all the other cells are left as 0. Additionally, the placed number is not repeated
 * in any row or column across the entire board.
 * <p>
 * The board is represented as a list of lists (ArrayLists) rather than using arrays,
 * and the board is generated using a backtracking algorithm that works block by block.
 * <p>
 * Java JDK 17.
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
     * Constructor initializes the board with zeros and then fills each block with one number.
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

    public void regenerateBoard() {
        int attempts = 0;
        boolean success = false;

        while (attempts < MAX_GENERATION_ATTEMPTS && !success) {
            attempts++;
            cleanBoard();
            lockedCells.clear();

            // LLenar 2 por bloque (aleatorio)
            if (!fillBlocksRandomTwoPerBlock()) {
                // Si algo raro pasa (aunque la implementación no devuelve false), volvemos a intentar
                continue;
            }

            // Marcar las celdas no nulas como bloqueadas (iniciales)
            markInitialLockedCells();

            // Verificar unicidad de solución
            if (hasUniqueSolution()) {
                success = true;
                break;
            } else {
                // Si no es única, continuar intentando
                // (limpiarTablero ya se hará al inicio del siguiente ciclo)
            }
        }

        // Si no encontró único en MAX attempts, dejamos el último generado (mejor que bloquear).
        // Puedes loggear attempts para debug.
        if (!success) {
            System.out.println("Warning: couldn't generate unique-solution board in " + MAX_GENERATION_ATTEMPTS + " attempts. Using last generated board.");
        }
    }

    /**
     * Recursively fills each 2x3 block with one number.
     *
     * @param blockIndex the index of the current block (ranging from 0 to TOTAL_BLOCKS - 1).
     * @return true if all blocks have been successfully filled; false otherwise.
     */
    public boolean fillBlocksRandomTwoPerBlock() {
        // Recorre bloques (blockIndex 0..5)
        for (int blockIndex = 0; blockIndex < TOTAL_BLOCKS; blockIndex++) {
            int blockRow = blockIndex / TOTAL_BLOCK_COLS;
            int blockCol = blockIndex % TOTAL_BLOCK_COLS;

            int startRow = blockRow * BLOCK_ROWS; // 0,2,4
            int startCol = blockCol * BLOCK_COLS; // 0,3

            int filled = 0;
            int innerAttempts = 0;
            // Para evitar quedarse colgado dentro de un bloque, limitamos intentos internos
            int maxInnerAttempts = 200;

            while (filled < 2 && innerAttempts < maxInnerAttempts) {
                innerAttempts++;

                int r = startRow + random.nextInt(BLOCK_ROWS); // 0..1 offset
                int c = startCol + random.nextInt(BLOCK_COLS); // 0..2 offset

                if (board.get(r).get(c) != 0) continue; // ya ocupado

                // Generamos lista de números del 1 al 6 en orden aleatorio para intentar
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

                // Si no se colocó, probamos otra posición; si no encontramos en maxInnerAttempts,
                // salimos y dejamos que la generación global vuelva a intentarlo.
            }

            if (filled < 2) {
                // No se pudo llenar este bloque con 2 números válidos dentro de los intentos,
                // devolvemos false para forzar regeneración desde regenerateBoard.
                return false;
            }
        }
        return true;
    }

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
     * Checks whether placing a candidate number at cell (row, col) violates the row or column uniqueness.
     *
     * @param row       the row index.
     * @param col       the column index.
     * @param candidate the number to place (from 1 to 6).
     * @return true if the candidate can be placed without conflict; false otherwise.
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

    private String key(int row, int col) {
        return row + "," + col;
    }

    public void lockCell(int row, int col) {
        lockedCells.add(key(row, col));
    }

    public void unlockCell(int row, int col) {
        lockedCells.remove(key(row, col));
    }

    public boolean isCellLocked(int row, int col) {
        return lockedCells.contains(key(row, col));
    }

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

    public void cleanBoard() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                board.get(i).set(j, 0);
            }
        }
        lockedCells.clear();
    }

    public boolean hasUniqueSolution() {
        int[] count = {0};
        // Hacemos una copia temporal del tablero? No es necesario porque el backtracking restaura.
        solveAndCount(count);
        return count[0] == 1;
    }

    /**
     * Backtracking que cuenta soluciones. Devuelve true si debe parar pronto (cuando count>1).
     * count es un array de tamaño 1 para pasar por referencia.
     */
    private boolean solveAndCount(int[] count) {
        if (count[0] > 1) return true; // ya encontramos más de una
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
                    // Si no hay número válido aquí, se retrocede (sin incrementar count)
                    return false;
                }
            }
        }
        // Si llegamos aquí, no hay celdas vacías: encontramos una solución completa
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
