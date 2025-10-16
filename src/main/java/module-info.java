module com.example.sudoku2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;


    opens com.example.sudoku2 to javafx.fxml;
    opens com.example.sudoku2.controller to javafx.fxml;
    exports com.example.sudoku2;
}