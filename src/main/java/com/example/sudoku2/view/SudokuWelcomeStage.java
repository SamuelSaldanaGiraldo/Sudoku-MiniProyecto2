package com.example.sudoku2.view;
import com.example.sudoku2.controller.SudokuGameController;
import com.example.sudoku2.controller.SudokuWelcomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuWelcomeStage extends Stage {
    private SudokuWelcomeController controller;
    private SudokuWelcomeStage() throws IOException{
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/com/example/sudoku2/fxml/sudoku-welcome-view.fxml"));
        Parent root = Loader.load();

        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("Sudoku");
        setResizable(false);
        //getIcons().add(new Image(String.valueOf(getClass().getResource("/com/example/sudoku2/favicon.png"))));
        show();
    }

    private static class Holder {
        private static SudokuWelcomeStage INSTANCE = null;
    }

    public static SudokuWelcomeStage getInstance() throws IOException {
        Holder.INSTANCE = Holder.INSTANCE != null ?
                Holder.INSTANCE : new SudokuWelcomeStage();
        return  Holder.INSTANCE;
    }

    public SudokuWelcomeController getController() {return controller;}

    public static void deleteInstance() {
        Holder.INSTANCE.close();
        Holder.INSTANCE = null;
    }
}
