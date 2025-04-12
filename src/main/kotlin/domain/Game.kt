package domain

import input.InputParser

class Game(private val gameConfig: GameConfig) {

    private val board = Board(gameConfig.gridSize, gameConfig.numMines)

    fun play() {
        Messages.printMinefieldHeader(firstTime = true)
        board.printBoard()

        while (true) {
            val (row, col) = getValidUserSelection()

            if (board.isMine(row, col)) {
                Messages.printGameOver()
                return
            }

            board.revealCell(row, col)

            if (board.hasWon()) {
                Messages.printVictory()
                return
            }

            Messages.printMinefieldHeader(firstTime = false)
            board.printBoard()
        }
    }

    fun end() {
        InputParser.getValidatedInput(
            prompt = "Press any key to play again...",
            parser = { it }
        )
    }

    private fun getValidUserSelection(): Pair<Int, Int> {
        return InputParser.getValidatedInput(
            prompt = "Select a square to reveal (e.g. A1):",
            errorMessage = "Invalid input. Please enter a valid square (e.g. A1)."
        ) { input ->
            if (input == "show all") {
                board.printBoard(revealAll = true)
                return@getValidatedInput null
            }

            parseCoordinates(input, gameConfig.gridSize)
        }!!
    }

    private fun parseCoordinates(input: String, gridSize: Int): Pair<Int, Int>? {
        val regex = Regex("([A-Za-z])(\\d+)")
        val match = regex.matchEntire(input) ?: return null

        val rowChar = match.groupValues[1].uppercase()[0]
        val col = match.groupValues[2].toIntOrNull()?.minus(1) ?: return null
        val row = rowChar - 'A'

        return if (row in 0 until gridSize && col in 0 until gridSize) {
            Pair(row, col)
        } else null
    }
}
