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
 * Represents the concrete implementation of the Sudoku game logic.
 * This class is responsible for setting up the game board UI and handling user input.
 */
public class Game extends GameAbstract {
    private AlertBox alertBox;
    /**
     * Constructs a new Game instance.
     *
     * @param boardGridpane The GridPane from the view where the Sudoku board will be rendered.
     */
    public Game(GridPane boardGridpane) {
        super(boardGridpane);
    }

    /**
     * Starts the game by generating a board, creating UI components (TextFields) for each cell,
     * and adding them to the GridPane. It also sets properties for each cell, such as editability.
     */
    @Override
    public void startGame() {
        alertBox = new AlertBox();
        for (int i = 0; i < board.getBoard().size(); i++) {
            for (int j = 0; j < board.getBoard().get(i).size(); j++) {
                int number = board.getBoard().get(i).get(j);
                System.out.print(number + " ");

                TextField textField = getTextFieldAt(i, j);
                textField.setAlignment(Pos.CENTER);
                textField.setText(String.valueOf(number));
                if (number != 0) {
                    textField.setEditable(false);
                } else{
                    textField.setText("");
                }
                handleNumberField(textField, i, j);
            }
            System.out.println();
        }
    }

    /**
     * Attaches a key released event handler to a TextField cell. When the key is released,
     * it validates the number entered by the user against the Sudoku rules.
     *
     * @param txt The TextField to which the handler will be attached.
     * @param row The row index of the cell in the board.
     * @param col The column index of the cell in the board.
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
                board.lockCell(row, col);
                if(!result){
                    textField.setStyle("-fx-text-fill: red;");
                }
            }
        });
        board.unlockEmptyCells();
    }

    private boolean validateInput(String input) {
        return input.matches("[1-6]");
    }

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


    public SuggestionEngine getSuggestionEngine() {
        return new SuggestionEngine();
    }

    public class SuggestionEngine {
        /**
         * Busca y devuelve la primera sugerencia segura según lógica humana (Fase 1 + Fase 2)
         * Formato: {row, col, num} o null si no hay sugerencia.
         */
        public int[] getSafeSuggestion() {
            int[] singleCandidate = findSingleCandidate();
            if (singleCandidate != null) return singleCandidate;

            int[] hiddenSingle = findHiddenSingle();
            if (hiddenSingle != null) return hiddenSingle;

            return null; // no hay sugerencias por ahora
        }

        // ---------------- Fase 1 ----------------
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

        // ---------------- Fase 2 ----------------
        private int[] findHiddenSingle() {
            int size = board.getBoard().size();

            // Filas
            for (int r = 0; r < size; r++) {
                for (int num = 1; num <= 6; num++) {
                    int colCandidate = -1;
                    for (int c = 0; c < size; c++) {
                        if (board.getBoard().get(r).get(c) == 0 && !board.isCellLocked(r, c)
                                && board.isValid(r, c, num)) {
                            if (colCandidate != -1) {
                                colCandidate = -1; // más de una opción
                                break;
                            }
                            colCandidate = c;
                        }
                    }
                    if (colCandidate != -1) return new int[]{r, colCandidate, num};
                }
            }

            // Columnas
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

            // Regiones 2x3
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
                                    if (rCandidate != -1) { // más de una opción
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

            return null; // no hay Hidden Single
        }

        // ---------------- Aplicar sugerencia ----------------
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

        // ---------------- Candidatos posibles ----------------
        private List<Integer> getPossibleNumbers(int row, int col) {
            List<Integer> list = new ArrayList<>();
            for (int n = 1; n <= 6; n++) {
                if (board.isValid(row, col, n)) list.add(n);
            }
            return list;
        }


    }

}
