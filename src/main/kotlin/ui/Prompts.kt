import domain.Board
import domain.Cell
import domain.GameConfig

object Prompts {

    fun printWelcome() {
        println("Welcome to Minesweeper!")
        println("Type \"help\" for list of commands")
    }

    fun printHelp() {
        println(
            """
            Available Commands:
            - help             -> Show this help message
            - exit             -> Exit the game
            - restart          -> Restart the game
            During the game:
            - A1, B3, etc.     -> Reveal a cell at the specified position
            - show all         -> Show the location of the mines
            
            """.trimIndent()
        )
    }

    fun printExitMessage() {
        println("Thanks for playing!")
    }

    fun printRestartMessage() {
        println("Game restarted!")
    }

    fun printMinefieldHeader(firstTime: Boolean) {
        println("Here is your${if (firstTime) "" else " updated"} minefield:")
    }

    fun printGameOver() {
        println("Oh no, you detonated a mine! Game over.")
    }

    fun printVictory() {
        println("Congratulations, you have won the game!")
    }

    fun printGetGridSize() {
        println("Enter the size of the grid (e.g. 4 for a 4x4 grid):")
    }

    fun printInvalidGridSize() {
        println("Invalid input. Min is 2; Max is 26.")
    }

    fun printGetNumMines(maxMines: Int) {
        println("Enter the number of mines to place on the grid (maximum is $maxMines):")
    }

    fun printPlayAgain() {
        println("Press any key to play again...")
    }

    fun printSelectSquare() {
        println("Select a square to reveal (e.g. A1):")
    }

    fun printInvalidSquareSelection() {
        println("Invalid input. Please enter a valid square (e.g. A1).")
    }

    fun printErrorDefault() {
        println("Invalid input.")
    }

    fun printBoard(gridSize: Int, grid: Array<Array<Cell>>, revealAll: Boolean = false) {
        print("  ")
        for (i in 1..gridSize) print(" $i")
        println()
        for (row in 0 until gridSize) {
            print("${'A' + row} ")
            for (col in 0 until gridSize) {
                val cell = grid[row][col]
                val display = when {
                    cell.isRevealed -> cell.adjacentMines.toString()
                    revealAll -> cell.adjacentMines.toString()
                    else -> "_"
                }
                print(" $display")
            }
            println()
        }
    }
    fun printRevealedCell(cell: Cell) {
        println("This square contains ${cell.adjacentMines} adjacent mine${if (cell.adjacentMines != 1) "s" else ""}.")
    }
}
