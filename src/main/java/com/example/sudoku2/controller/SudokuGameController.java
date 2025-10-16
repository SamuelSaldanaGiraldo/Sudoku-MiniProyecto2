package com.example.sudoku2.controller;

import com.example.sudoku2.model.game.Game;
import com.example.sudoku2.model.game.GameAdapter;
import com.example.sudoku2.model.game.IGame;
import com.example.sudoku2.model.user.User;
import com.example.sudoku2.view.SudokuFinalStage;
import com.example.sudoku2.view.SudokuGameStage;
import com.example.sudoku2.view.SudokuWelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller class for the main Sudoku game view (sudoku-game-view.fxml).
 * <p>
 * This controller manages the game screen, initializes the Sudoku board,
 * handles user interactions such as requesting help or returning to the
 * welcome screen, and displays the current player's nickname.
 * </p>
 */
public class SudokuGameController implements Initializable {

    /**
     * The {@link GridPane} representing the visual Sudoku board where cells are displayed.
     */
    @FXML
    private GridPane boardGridPane;

    /**
     * Displays the nickname of the user currently playing.
     */
    @FXML
    private Label nicknameTxt;


    /**
     * Interface for game logic handling. Uses an adapter to connect UI and logic.
     */
    private IGame game;

    /**
     * The current user playing the game.
     */
    private User user;

    /**
     * Initializes the Sudoku game screen. It creates a new game instance,
     * loads the initial board, and shows the player's nickname if available.
     *
     * @param url            URL to locate the FXML file, not used here.
     * @param resourceBundle Resource bundle for localization, not used here.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new GameAdapter(boardGridPane);
        game.startGame();
        if (user != null) {
            nicknameTxt.setText("Waiting... " + user.getNickname());
        }
    }

    /**
     * Handles the action of returning to the welcome screen.
     *
     * @param event Button click event fired by the "Back" button.
     */
    @FXML
    void handleBack(ActionEvent event) {
        try {
            SudokuWelcomeStage.getInstance();
            SudokuGameStage.deleteInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the help button click event. Requests a safe suggestion
     * from the suggestion engine and applies it to the board if possible.
     * The suggested number is visually highlighted and made uneditable.
     */
    public void onHelpButtonClicked() {
        Game.SuggestionEngine engine = game.getSuggestionEngine();

        // pedir sugerencia
        int[] sug = engine.getSafeSuggestion();
        if (sug == null) {
            System.out.println("No suggestions");
        } else {
            // aplicar a modelo (board) y bloquearla
            boolean applied = engine.applySuggestionToBoard(sug);
            if (applied) {
                // actualizar la vista para que muestre el número
                TextField tf = game.getTextFieldAt(sug[0], sug[1]); // tu helper existente
                if (tf != null) {
                    tf.setText(String.valueOf(sug[2]));
                    tf.setStyle("-fx-text-fill: #ffb62d; -fx-font-weight: bold;");
                    tf.setEditable(false); // opcional: si quieres que quede no editable
                }
            } else {
                System.out.println("La sugerencia ya no es aplicable. Intenta de nuevo.");
            }
        }

    }

    /**
     * Checks if the Sudoku game has been completed.
     * <p>
     * If the board is complete, it calls {@link #endGame()} to handle
     * the end-of-game logic. Otherwise, it prints a message indicating
     * that the board is not yet complete.
     * </p>
     */
    @FXML
    private void checkIfGameFinished() {
        if (game.isBoardComplete()) {
            endGame();
        } else {
            System.out.println("The board is not complete.");
        }
    }

    /**
     * Handles the end-of-game logic when the Sudoku board is complete.
     * <p>
     * This method prints a confirmation message, opens the final game stage
     * via {@link SudokuFinalStage#getInstance()}, and deletes the current
     * game stage instance using {@link SudokuGameStage#deleteInstance()}.
     * Any {@link IOException} encountered during this process is caught
     * and its stack trace is printed.
     * </p>
     */
    @FXML
    private void endGame()  {
        System.out.println("The board is complete.");
        try {
            SudokuFinalStage.getInstance();
            SudokuGameStage.deleteInstance();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the user for the current session and updates the nickname label in the UI.
     *
     * @param user The {@link User} object representing the current player.
     */
    public void setUser(User user) {
        this.user = user;
        if (nicknameTxt != null && user != null) {
            nicknameTxt.setText("Waiting... " + user.getNickname());
        }
    }
}
