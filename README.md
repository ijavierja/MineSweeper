# Minesweeper Game

## Project Overview
This is a simple command-line implementation of the  Minesweeper game.

## Design Explanation

The game follows an object-oriented approach with these key components:

- **Board**: Represents the grid and tracks revealed cells and mines.
- **Cell**: Each cell can either contain a mine or show the number of adjacent mines.
- **GameConfig**: Holds game settings like grid size and number of mines.
- **Game**: Manages the game flow.
- **InputParser**: Handles and validates user input, including special commands like "exit," "restart," and "help."
- **Prompts**: Displays messages to the user (game start, error messages, etc.).
- **MineSweeperApp**: Controls the app flow (starting, restarting each game, exiting the app).

### Assumptions:
- The user sets grid size and number of mines at the start.
- The game ends when the user reveals all non-mine cells (win) or hits a mine (lose).
- If an invalid input happens at anytime during the game, the game will simply prompt again, ignoring the invalid input.

### Additonal Features:
- **Exit**: The user can type `exit` at any time to quit the game.
- **Restart**: The user can type `restart` to start a new game.
- **Help**: Typing `help` shows the available commands and instructions.
- **Show All**: The user can type `show all` to reveal the locations of all mines.

## How to Run the Game

### Pre-requisites
- Java 8 or later
### 1. Clone the repository and navigate into the project directory

### 2.Build the project

Linux/macOS:
```
./gradlew build
```

For Windows:
```
gradlew.bat build
```
### 3. Run the App

Linux/macOS:
```
./gradlew run
```

For Windows:
```
gradlew.bat run
```

## How to Run Tests

### Running Unit Tests

You can run the unit tests for the project by executing the following command:

Linux/macOS:
```
./gradlew test
```

For Windows:
```
gradlew.bat test
```

## Areas for Improvement

- **Thorough Testing**: More unit tests for better coverage and edge cases.
- **User Interface**: Consider a web-based UI (e.g. using React).
- **Flagging**: Add flagging functionality for suspected mines.
- **Game Status**: Display the number of unrevealed cells for better progress tracking.
- **Persistent Game State**: Save game state so that users can resume later.


