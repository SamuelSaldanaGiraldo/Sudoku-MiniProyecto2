package com.example.sudoku2.controller;

import com.example.sudoku2.model.game.Game;
import com.example.sudoku2.model.user.User;
import com.example.sudoku2.view.SudokuGameStage;
import com.example.sudoku2.view.SudokuHelpStage;
import com.example.sudoku2.view.SudokuWelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller class for the Sudoku welcome screen. Handles user interaction
 * for starting the game, exiting the application, and accessing the help view.
 */
public class SudokuWelcomeController {
    /**
     * TextField where the user enters their nickname before starting the game.
     */
    @FXML
    private TextField nicknameTxt;

    /**
     * Handles the action of pressing the "Play" button.
     * <p>
     * Validates the nickname entered by the user and, if valid,
     * initializes the game stage and sets the current user.
     * </p>
     *
     * @param event the ActionEvent triggered by clicking the play button.
     */
    @FXML
    void handlePlay(ActionEvent event){
        String nickname = nicknameTxt.getText().trim();
        try {
            if (!nickname.equals("")) {
                SudokuGameStage.getInstance().getController().setUser(new User(nickname));
                SudokuWelcomeStage.deleteInstance();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action of pressing the "Leave" button.
     * <p>
     * Closes the welcome stage and exits the application.
     * </p>
     *
     * @param event the ActionEvent triggered by clicking the leave button.
     */
    @FXML
    void handleLeave(ActionEvent event){
        SudokuWelcomeStage.deleteInstance();
    }

    /**
     * Handles the action of pressing the "Help" button.
     * <p>
     * Opens the help screen and closes the welcome screen.
     * </p>
     *
     * @param event the ActionEvent triggered by clicking the help button.
     */
    @FXML
    void handleHelp(ActionEvent event){
        try{
            SudokuHelpStage.getInstance();
            SudokuWelcomeStage.deleteInstance();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
