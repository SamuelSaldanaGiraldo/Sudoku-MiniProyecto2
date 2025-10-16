package com.example.sudoku2.controller;

import com.example.sudoku2.view.SudokuFinalStage;
import com.example.sudoku2.view.SudokuWelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SudokuFinalController {
    @FXML
    void handleMenu(ActionEvent event){
        try {
            SudokuWelcomeStage.getInstance();
            SudokuFinalStage.deleteInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
