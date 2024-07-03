package com.tictactoe.logic;

import com.tictactoe.abstracts.AbstractGame;
import com.tictactoe.interfaces.Movable;
import com.tictactoe.interfaces.Undoable;

import java.util.*;

public class TicTacToeLogic extends AbstractGame implements Movable, Undoable {
    private static final int MAX_UNDO = 2;
    private static final int HISTORY_SIZE = 3;

    private LinkedList<Move> moveHistory;
    private Queue<Move> recentMoves;
    private Stack<Move> undoStackX;
    private Stack<Move> undoStackO;
    private int undoCountX;
    private int undoCountO;

    public TicTacToeLogic() {
        initializeGame();
    }

    @Override
    public void initializeGame() {
        board = new char[3][3];
        for (char[] chars : board) {
            Arrays.fill(chars, ' ');
        }
        currentPlayer = 'X';
        moveHistory = new LinkedList<>();
        recentMoves = new LinkedList<>();
        undoStackX = new Stack<>();
        undoStackO = new Stack<>();
        undoCountX = MAX_UNDO;
        undoCountO = MAX_UNDO;
        gameOver = false;
    }

    @Override
    public void makeMove(int row, int col) {
        if (gameOver || board[row][col] != ' ') {
            return;
        }

        board[row][col] = currentPlayer;
        Move move = new Move(row, col, currentPlayer);
        moveHistory.add(move);
        if (currentPlayer == 'O') {
            undoStackX.push(move);
        } else {
            undoStackO.push(move);
        }

        recentMoves.add(move);
        if (recentMoves.size() > HISTORY_SIZE) {
            recentMoves.poll();
        }

        if (haveWon(currentPlayer)) {
            gameOver = true;
            onGameEnd();
        } else if (isBoardFull()) {
            gameOver = true;
            onGameEnd();
        } else {
            switchPlayer();
        }
    }

    @Override
    public void undoMove() {
        if (gameOver) {
            return;
        }

        if (currentPlayer == 'X' && !undoStackX.isEmpty() && undoCountX > 0) {
            undo(undoStackX);
            undoCountX--;
        } else if (currentPlayer == 'O' && !undoStackO.isEmpty() && undoCountO > 0) {
            undo(undoStackO);
            undoCountO--;
        }
    }

    private void undo(Stack<Move> undoStack) {
        Move lastMove = undoStack.pop();
        board[lastMove.row][lastMove.col] = ' ';
        moveHistory.removeLast();
        recentMoves.remove(lastMove);
        switchPlayer();
    }

    @Override
    public boolean haveWon(char player) {
        for (char[] chars : board) {
            if (chars[0] == player && chars[1] == player && chars[2] == player) {
                return true;
            }
        }
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        return board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    @Override
    public boolean isBoardFull() {
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void switchPlayer() {
        currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
    }

    protected void onGameEnd() {
        // This method can be overridden to handle end game scenarios (e.g., updating UI)
    }

    public LinkedList<Move> getMoveHistory() {
        return moveHistory;
    }

    public Queue<Move> getRecentMoves() {
        return recentMoves;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
