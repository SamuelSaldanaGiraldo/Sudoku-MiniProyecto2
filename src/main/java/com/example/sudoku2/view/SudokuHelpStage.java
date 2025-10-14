package com.example.sudoku2.view;

import com.example.sudoku2.controller.SudokuGameController;
import com.example.sudoku2.controller.SudokuHelpController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuHelpStage extends Stage {
    private SudokuHelpController controller;
    private SudokuHelpStage() throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/com/example/sudoku2/fxml/sudoku-help-view.fxml"));
        Parent root = Loader.load();
        controller = Loader.getController();

        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("Help");
        setResizable(false);
        //getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/sudoku2/favicon.png"))));
        show();
    }

    private static class Holder {
        private static SudokuHelpStage INSTANCE = null;
    }

    public static SudokuHelpStage getInstance() throws IOException {
        SudokuHelpStage.Holder.INSTANCE = SudokuHelpStage.Holder.INSTANCE != null ?
                SudokuHelpStage.Holder.INSTANCE : new SudokuHelpStage();
        return  SudokuHelpStage.Holder.INSTANCE;
    }
    public SudokuHelpController getController(){
        return controller;
    }


    public static void deleteInstance() {
        SudokuHelpStage.Holder.INSTANCE.close();
        SudokuHelpStage.Holder.INSTANCE = null;
    }
}
