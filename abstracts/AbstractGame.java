package com.tictactoe.abstracts;

public abstract class AbstractGame {
    public char[][] board;
    protected char currentPlayer;
    protected boolean gameOver;

    protected abstract void initializeGame();
    protected abstract boolean haveWon(char player);
    protected abstract boolean isBoardFull();
    protected abstract void switchPlayer();
}
