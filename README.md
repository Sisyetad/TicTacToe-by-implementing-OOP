<!DOCTYPE html>
<body>
  <h1>TicTacToe Game</h1>

  <h2>Game Overview</h2>
  <p>TicTacToe is a two-player game played on a 3x3 grid. The players take turns placing their marks (X or O) on the grid, aiming to get three marks in a row, column, or diagonal. The first player to achieve this wins the game. If all the cells on the grid are filled and no player has won, the game ends in a draw.</p>

  <h2>Project Structure</h2>
  <p>The project consists of the following classes:</p>
  <ul>
    <li><code>TicTacToeGame</code>: The main class that represents the TicTacToe game. It handles the game logic and flow.</li>
    <li><code>Board</code>: A class representing the game board. It maintains the state of the board and provides methods for making moves and checking the game status.</li>
    <li><code>Player</code>: An abstract class representing a player in the game. It defines common behavior and properties for all types of players.</li>
    <li><code>HumanPlayer</code>: A concrete class representing a human player. It implements the <code>Player</code> abstract class and allows human input for making moves.</li>
    <li><code>Move</code>: A class representing a move made by a player. It stores the player's mark (X or O) and the coordinates of the move on the board.</li>
  </ul>

  <h2>OOP Concepts</h2>
  <p>The project demonstrates the following OOP concepts:</p>
  <ul>
    <li><strong>Abstract Class:</strong> The <code>Player</code> class is an abstract class that defines common behavior and properties for all players. It provides an abstract method for making moves, which is implemented by its concrete subclasses.</li>
    <li><strong>Interface:</strong> The <code>Movable</code> interface defines the behavior for objects that can make moves in the game. Both the <code>HumanPlayer</code> class implements this interface, providing their respective implementations for making moves.</li>
    <li><strong>Interface:</strong> The <code>Undoable</code> interface defines the behavior for objects that support undoing moves. The <code>Board</code> class implements this interface, allowing players to undo their moves.</li>
  </ul>

  <h2>Usage</h2>
  <p>To run the TicTacToe game, follow these steps:</p>
  <ol>
    <li>Clone the project repository:<br><code>git clone https://github.com/Sisyetad/TicTacToe-by-implementing-OOP.git</code></li>
    <li>Open the project in your preferred Java development environment (e.g., IntelliJ, Eclipse).</li>
    <li>Build and compile the project.</li>
    <li>Run the <code>TicTacToeGame</code> class to start the game.</li>
  </ol>
  <p>Follow the on-screen instructions to play the game. Each player takes turns making moves by selecting a cell on the 3x3 grid. The game will display the current state of the board and provide feedback on the game status (e.g., if a player has won or if the game ended in a draw). Players can also undo their moves if supported by the game implementation.</p>

  <h2>License</h2>
  <p>This project is licensed under the <a href="LICENSE">MIT License</a>. Feel free to modify and distribute the code as per the terms of the license.</p>

 
