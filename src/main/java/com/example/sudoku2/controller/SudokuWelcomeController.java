package com.example.sudoku2.controller;

import com.example.sudoku2.view.SudokuGameStage;
import com.example.sudoku2.view.SudokuHelpStage;
import com.example.sudoku2.view.SudokuWelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;


public class SudokuWelcomeController {
    @FXML
    private TextField nicknameTxt;

    @FXML
    void handlePlay(ActionEvent event){
        try {
            String nickname = nicknameTxt.getText();
            SudokuGameStage.getInstance();
            SudokuWelcomeStage.deleteInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void handleLeave(ActionEvent event){
        SudokuWelcomeStage.deleteInstance();
    }
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
