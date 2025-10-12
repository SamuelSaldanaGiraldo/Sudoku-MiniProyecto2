package com.example.sudoku2.view;

import com.example.sudoku2.controller.SudokuGameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuGameStage extends Stage {
    private SudokuGameController controller;
    private SudokuGameStage() throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/com/example/sudoku2/fxml/sudoku-game-view.fxml"));
        Parent root = Loader.load();
        controller = Loader.getController();

        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("Sudoku");
        setResizable(false);
        //getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/sudoku2/favicon.png"))));
        show();
    }

    private static class Holder {
        private static SudokuGameStage INSTANCE = null;
    }

    public static SudokuGameStage getInstance() throws IOException {
        SudokuGameStage.Holder.INSTANCE = SudokuGameStage.Holder.INSTANCE != null ?
                SudokuGameStage.Holder.INSTANCE : new SudokuGameStage();
        return  SudokuGameStage.Holder.INSTANCE;
    }
    public SudokuGameController getController(){
        return controller;
    }


    public static void deleteInstance() {
        SudokuGameStage.Holder.INSTANCE.close();
        Holder.INSTANCE = null;
    }

}
