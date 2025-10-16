package com.example.sudoku2.controller;

import com.example.sudoku2.model.game.Game;
import com.example.sudoku2.model.game.GameAdapter;
import com.example.sudoku2.model.game.IGame;
import com.example.sudoku2.model.user.User;
import com.example.sudoku2.view.SudokuGameStage;
import com.example.sudoku2.view.SudokuWelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller for the main Sudoku game view (sudoku-game-view.fxml).
 * This class is responsible for initializing and managing the game board's UI.
 */
public class SudokuGameController implements Initializable {

    /**
     * The GridPane element from the FXML file that holds the Sudoku board cells.
     */
    @FXML
    private GridPane boardGridPane;

    private IGame game;
    private Game.SuggestionEngine suggestionEngine;
    private User user;

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded. It creates a new game instance
     * and starts the game.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new GameAdapter(boardGridPane);
        game.startGame();
    }

    @FXML
    void handleBack(ActionEvent event){
        try {
            SudokuWelcomeStage.getInstance();
            SudokuGameStage.deleteInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onHelpButtonClicked() {
        Game.SuggestionEngine engine = game.getSuggestionEngine();

        // pedir sugerencia
        int[] sug = engine.getSafeSuggestion();
        if (sug == null) {
            System.out.println("No hay sugerencias disponibles");
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
     * Sets the user for the current game session. This method is called by the
     * welcome controller to pass the user's data.
     *
     * @param user The user object containing player information, such as the nickname.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
