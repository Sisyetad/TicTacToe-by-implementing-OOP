package com.tictactoe.logic;

public class Move {
    public int row;
    public int col;
    public char player;

    public Move(int row, int col, char player) {
        this.row = row;
        this.col = col;
        this.player = player;
    }

    public char[] getPlayer() {
        return new char[0];
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
