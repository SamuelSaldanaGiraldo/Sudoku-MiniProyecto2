package com.example.sudoku2.model.game;

import javafx.scene.control.TextField;

/**
 * Defines the contract for a game class. Any class that implements
 * this interface must provide a method to start the game.
 */
public interface IGame {
    /**
     * Initializes and starts the game logic.
     */
    void startGame();
    TextField getTextFieldAt(int row, int col);
    Game.SuggestionEngine getSuggestionEngine();
}