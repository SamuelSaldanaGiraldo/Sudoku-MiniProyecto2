package com.example.sudoku2.model.game;

public interface ISuggestionEngine {
    /**
     * Retorna una sugerencia en formato {fila, columna, número}
     * o null si no hay sugerencias válidas.
     */
    int[] getSafeSuggestion();

    /**
     * Aplica la sugerencia al tablero si es válida.
     */
    boolean applySuggestionToBoard(int[] suggestion);
}
