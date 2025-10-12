package com.example.sudoku2.controller;

import com.example.sudoku2.view.SudokuGameStage;
import com.example.sudoku2.view.SudokuWelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;



public class SudokuGameController {

    @FXML
    void handleBack(ActionEvent event){
        try {
            SudokuWelcomeStage.getInstance();
            SudokuGameStage.deleteInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
