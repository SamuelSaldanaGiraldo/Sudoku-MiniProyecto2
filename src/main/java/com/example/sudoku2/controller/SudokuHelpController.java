package com.example.sudoku2.controller;

import com.example.sudoku2.view.SudokuHelpStage;
import com.example.sudoku2.view.SudokuWelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SudokuHelpController {
    @FXML
    void handleBack(ActionEvent event){
        try {
            SudokuWelcomeStage.getInstance();
            SudokuHelpStage.deleteInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
