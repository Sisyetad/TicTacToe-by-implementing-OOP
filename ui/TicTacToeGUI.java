package com.tictactoe.ui;

import com.tictactoe.logic.Move;
import com.tictactoe.logic.TicTacToeLogic;

import javax.swing.*;
import java.awt.*;

public class TicTacToeGUI extends JFrame {
    private final TicTacToeLogic logic;
    private JButton[][] buttons;
    private final JLabel statusLabel;

    public TicTacToeGUI() {
        logic = new TicTacToeLogic();
        setTitle("Tic-Tac-Toe");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        add(boardPanel, BorderLayout.CENTER);
        JPanel controlPanel = new JPanel();
        add(controlPanel, BorderLayout.SOUTH);
        statusLabel = new JLabel("Player X's turn");
        add(statusLabel, BorderLayout.NORTH);

        initializeButtons(boardPanel);
        initializeControlButtons(controlPanel);

        setVisible(true);
    }

    private void initializeButtons(JPanel boardPanel) {
        buttons = new JButton[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton("");
                button.setFont(new Font("Arial", Font.PLAIN, 80));
                button.setFocusPainted(false);

                int finalRow = row;
                int finalCol = col;
                button.addActionListener(e -> handleButtonClick(finalRow, finalCol));
                buttons[row][col] = button;
                boardPanel.add(button);
            }
        }
    }

    private void initializeControlButtons(JPanel controlPanel) {
        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> handleUndo());
        controlPanel.add(undoButton);

        JButton viewMovesButton = new JButton("View Recent Moves");
        viewMovesButton.addActionListener(e -> displayRecentMoves());
        controlPanel.add(viewMovesButton);

        JButton viewAllMovesButton = new JButton("View All Moves");
        viewAllMovesButton.addActionListener(e -> displayAllMoves());
        controlPanel.add(viewAllMovesButton);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(e -> playAgain());
        controlPanel.add(playAgainButton);
    }

    private void handleButtonClick(int row, int col) {
        if (logic.isGameOver()) {
            return; // Don't handle clicks if the game is over
        }

        logic.makeMove(row, col);

        updateUI();

        if (logic.haveWon('X')) {
            JOptionPane.showMessageDialog(this, "Player X has won!");
            statusLabel.setText("Player X has won!");
        } else if (logic.haveWon('O')) {
            JOptionPane.showMessageDialog(this, "Player O has won!");
            statusLabel.setText("Player O has won!");
        } else if (logic.isBoardFull()) {
            JOptionPane.showMessageDialog(this, "The game is a draw!");
            statusLabel.setText("The game is a draw!");
        } else {
            // If the game is not over, update status label to next player's turn
            statusLabel.setText("Player " + logic.getCurrentPlayer() + "'s turn");
        }
    }

    private void handleUndo() {
        if (logic.isGameOver()) {
            return; // Don't handle undo if the game is over
        }

        logic.undoMove();
        updateUI();

        // Reset status label to current player's turn after undo
        statusLabel.setText("Player " + logic.getCurrentPlayer() + "'s turn");
    }

    private void updateUI() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText(String.valueOf(logic.board[row][col]));
            }
        }
        statusLabel.setText("Player " + logic.getCurrentPlayer() + "'s turn");
    }

    private void displayRecentMoves() {
        StringBuilder recentMovesStr = new StringBuilder("Recent moves:\n");
        for (Move move : logic.getRecentMoves()) {
            recentMovesStr.append("Player ").append(move.getPlayer()).append(" moved to (")
                    .append(move.getRow()).append(", ").append(move.getCol()).append(")\n");
        }
        JOptionPane.showMessageDialog(this, recentMovesStr.toString());
    }

    private void displayAllMoves() {
        StringBuilder moveHistoryStr = new StringBuilder("Move history:\n");
        for (Move move : logic.getMoveHistory()) {
            moveHistoryStr.append("Player ").append(move.getPlayer()).append(" moved to (")
                    .append(move.getRow()).append(", ").append(move.getCol()).append(")\n");
        }
        JOptionPane.showMessageDialog(this, moveHistoryStr.toString());
    }

    private void playAgain() {
        logic.initializeGame();
        updateUI();
        statusLabel.setText("Player X's turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeGUI::new);
    }
}
