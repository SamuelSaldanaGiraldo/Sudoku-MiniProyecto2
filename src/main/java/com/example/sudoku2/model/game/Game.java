package com.example.sudoku2.model.game;

import com.example.sudoku2.utils.AlertBox;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.control.Alert.AlertType.ERROR;

/**
 * Concrete implementation of the Sudoku game logic.
 * <p>
 * This class is responsible for rendering the Sudoku board in a {@link GridPane},
 * handling user input, validating numbers according to Sudoku rules, and managing
 * the game state.
 * </p>
 */
public class Game extends GameAbstract {

    private AlertBox alertBox;

    /**
     * Constructs a new Game instance.
     *
     * @param boardGridpane The {@link GridPane} from the view where the Sudoku board will be rendered.
     */
    public Game(GridPane boardGridpane) {
        super(boardGridpane);
    }

    /**
     * Starts the game by generating the board and creating UI components
     * ({@link TextField}) for each cell.
     * <p>
     * Sets properties for each cell, including editability and locking
     * pre-filled cells. Attaches input handlers to validate user entries.
     * </p>
     */
    @Override
    public void startGame() {
        alertBox = new AlertBox();
        for (int i = 0; i < board.getBoard().size(); i++) {
            for (int j = 0; j < board.getBoard().get(i).size(); j++) {
                int number = board.getBoard().get(i).get(j);

                TextField textField = getTextFieldAt(i, j);
                textField.setAlignment(Pos.CENTER);
                textField.setText(number != 0 ? String.valueOf(number) : "");
                if (number != 0) {
                    textField.setEditable(false);
                    board.lockCell(i, j);
                }
                handleNumberField(textField, i, j);
            }
        }
    }

    /**
     * Attaches a key released handler to a {@link TextField} cell to validate
     * user input in real time according to Sudoku rules.
     *
     * @param txt The {@link TextField} to attach the handler to.
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     */
    private void handleNumberField(TextField txt, int row, int col) {
        TextField textField = getTextFieldAt(row, col);
        txt.setOnKeyReleased(event -> {
            textField.setStyle("-fx-text-fill: #56b5c1");
            String input = txt.getText().trim();
            if(!input.isEmpty()){
                if (!validateInput(input)) {
                    textField.setText("");
                    alertBox.showAlert("Input error",
                            "Please enter a number between 1 and 6",
                            ERROR);
                }

                boolean result = board.isValid(row, col, Integer.parseInt(input));
                if(!result){
                    textField.setStyle("-fx-text-fill: red;");
                }
            }
        });
        board.unlockEmptyCells();
    }

    /**
     * Validates that the input is a number between 1 and 6.
     *
     * @param input the string input to validate
     * @return true if the input is valid; false otherwise
     */
    private boolean validateInput(String input) {
        return input.matches("[1-6]");
    }

    /**
     * Returns the {@link TextField} at a specific board cell.
     *
     * @param row the row index
     * @param col the column index
     * @return the {@link TextField} at the specified position
     */
    public TextField getTextFieldAt(int row, int col) {
        for (Node node : boardGridpane.getChildren()) {
            Integer r = GridPane.getRowIndex(node);
            Integer c = GridPane.getColumnIndex(node);
            int nodeRow = (r == null) ? 0 : r;
            int nodeCol = (c == null) ? 0 : c;

            if (nodeRow == row && nodeCol == col) {
                return (TextField) node;
            }
        }
        return null;
    }

    /**
     * Checks whether the board is completely filled and valid according
     * to Sudoku rules (rows, columns, and 2x3 blocks).
     *
     * @return true if the board is complete and valid; false otherwise
     */
    public boolean isBoardComplete() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                int num = board.getBoard().get(row).get(col);
                if (num == 0) return false;

                for (int c = 0; c < 6; c++) {
                    if (c != col && board.getBoard().get(row).get(c) == num) return false;
                }
                for (int r = 0; r < 6; r++) {
                    if (r != row && board.getBoard().get(r).get(col) == num) return false;
                }
                int startRow = (row / 2) * 2;
                int startCol = (col / 3) * 3;
                for (int r = startRow; r < startRow + 2; r++) {
                    for (int c = startCol; c < startCol + 3; c++) {
                        if ((r != row || c != col) && board.getBoard().get(r).get(c) == num) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Returns a new instance of {@link SuggestionEngine} for providing
     * hints or suggestions during gameplay.
     *
     * @return a new {@link SuggestionEngine} instance
     */
    public Game.SuggestionEngine getSuggestionEngine() {
        return new SuggestionEngine();
    }

    /**
     * Provides logic for generating human-like safe suggestions (hints)
     * for the next move in the Sudoku game.
     */
    public class SuggestionEngine {

        /**
         * Returns the first safe suggestion according to human logic.
         * <p>
         * Format: {row, col, number} or null if no suggestion is available.
         * </p>
         *
         * @return an int array representing the suggestion or null
         */
        public int[] getSafeSuggestion() {
            int[] singleCandidate = findSingleCandidate();
            if (singleCandidate != null) return singleCandidate;

            int[] hiddenSingle = findHiddenSingle();
            if (hiddenSingle != null) return hiddenSingle;

            return null;
        }

        /** Finds a cell with only one possible candidate number (Phase 1). */
        private int[] findSingleCandidate() {
            int size = board.getBoard().size();
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < board.getBoard().get(r).size(); c++) {
                    if (board.getBoard().get(r).get(c) == 0 && !board.isCellLocked(r, c)) {
                        List<Integer> options = getPossibleNumbers(r, c);
                        if (options.size() == 1) {
                            return new int[]{r, c, options.get(0)};
                        }
                    }
                }
            }
            return null;
        }

        /** Finds hidden singles in rows, columns, and 2x3 blocks (Phase 2). */
        private int[] findHiddenSingle() {
            int size = board.getBoard().size();

            // Rows
            for (int r = 0; r < size; r++) {
                for (int num = 1; num <= 6; num++) {
                    int colCandidate = -1;
                    for (int c = 0; c < size; c++) {
                        if (board.getBoard().get(r).get(c) == 0 && !board.isCellLocked(r, c)
                                && board.isValid(r, c, num)) {
                            if (colCandidate != -1) {
                                colCandidate = -1;
                                break;
                            }
                            colCandidate = c;
                        }
                    }
                    if (colCandidate != -1) return new int[]{r, colCandidate, num};
                }
            }

            // Columns
            for (int c = 0; c < size; c++) {
                for (int num = 1; num <= 6; num++) {
                    int rowCandidate = -1;
                    for (int r = 0; r < size; r++) {
                        if (board.getBoard().get(r).get(c) == 0 && !board.isCellLocked(r, c)
                                && board.isValid(r, c, num)) {
                            if (rowCandidate != -1) {
                                rowCandidate = -1;
                                break;
                            }
                            rowCandidate = r;
                        }
                    }
                    if (rowCandidate != -1) return new int[]{rowCandidate, c, num};
                }
            }

            // Blocks 2x3
            int blockRows = 2;
            int blockCols = 3;
            for (int br = 0; br < size; br += blockRows) {
                for (int bc = 0; bc < size; bc += blockCols) {
                    for (int num = 1; num <= 6; num++) {
                        int rCandidate = -1, cCandidate = -1;
                        for (int r = br; r < br + blockRows; r++) {
                            for (int c = bc; c < bc + blockCols; c++) {
                                if (board.getBoard().get(r).get(c) == 0 && !board.isCellLocked(r, c)
                                        && board.isValid(r, c, num)) {
                                    if (rCandidate != -1) {
                                        rCandidate = -1;
                                        cCandidate = -1;
                                        break;
                                    }
                                    rCandidate = r;
                                    cCandidate = c;
                                }
                            }
                            if (rCandidate == -1) break;
                        }
                        if (rCandidate != -1 && cCandidate != -1)
                            return new int[]{rCandidate, cCandidate, num};
                    }
                }
            }

            return null;
        }

        /**
         * Applies a suggestion to the board if it is valid.
         *
         * @param suggestion an int array {row, col, number}
         * @return true if the suggestion was successfully applied; false otherwise
         */
        public boolean applySuggestionToBoard(int[] suggestion) {
            if (suggestion == null || suggestion.length < 3) return false;

            int r = suggestion[0];
            int c = suggestion[1];
            int num = suggestion[2];

            if (board.getBoard().get(r).get(c) != 0) return false;
            if (board.isCellLocked(r, c)) return false;
            if (!board.isValid(r, c, num)) return false;

            board.getBoard().get(r).set(c, num);
            return true;
        }

        /**
         * Returns a list of possible numbers that can be placed in a cell.
         *
         * @param row the row index
         * @param col the column index
         * @return a list of valid numbers
         */
        private List<Integer> getPossibleNumbers(int row, int col) {
            List<Integer> list = new ArrayList<>();
            int currentValue = board.getBoard().get(row).get(col);
            if (currentValue != 0 && !board.isCellLocked(row, col)) {
                board.getBoard().get(row).set(col, 0);
            }

            for (int n = 1; n <= 6; n++) {
                if (board.isValid(row, col, n)) {
                    list.add(n);
                }
            }

            if (currentValue != 0 && !board.isCellLocked(row, col)) {
                board.getBoard().get(row).set(col, currentValue);
            }

            return list;
        }
    }
}
