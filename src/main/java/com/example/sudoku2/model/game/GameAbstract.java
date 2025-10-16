package com.example.sudoku2.model.game;

import com.example.sudoku2.model.board.BoardAdapter;
import com.example.sudoku2.model.board.IBoard;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

/**
 * An abstract base class for game logic, providing common properties
 * for a Sudoku game.
 */
public class GameAbstract implements IGame {
    /**
     * The UI grid where the board is displayed.
     */
    protected GridPane boardGridpane;
    /**
     * The underlying data structure and logic for the Sudoku board.
     */
    protected IBoard board;
    /**
     * A list of TextFields representing the cells on the board.
     */
    protected ArrayList<TextField> numberFields;

    /**
     * Constructs a GameAbstract instance, initializing the board and UI components.
     *
     * @param boardGridpane The GridPane that will contain the Sudoku cells.
     */
    public GameAbstract(GridPane boardGridpane) {
        this.boardGridpane = boardGridpane;
        this.board = new BoardAdapter();
        this.numberFields = new ArrayList<TextField>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
    }

    @Override
    public TextField getTextFieldAt(int row, int col) {
        return null;
    }

    @Override
    public Game.SuggestionEngine getSuggestionEngine() {
        return null;
    }

}
