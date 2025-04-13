package ui

import domain.Board
import domain.GameConfig
import utils.InputParser
import utils.parseCoordinates

object Console {

    fun parseGridSize(input: String): Int? =
        input.toIntOrNull()?.takeIf { it in 2..26 }

    fun getGridSizeInput(): Int {
        return InputParser.getValidatedInput(
            promptPrinter = { Prompts.printGetGridSize() },
            errorPrinter = { Prompts.printInvalidGridSize() },
            parser = ::parseGridSize
        )
    }

    fun parseNumMines(input: String, maxMines: Int): Int? =
        input.toIntOrNull()?.takeIf { it in 1..maxMines }

    fun getNumMinesInput(maxMines: Int): Int {
        return InputParser.getValidatedInput(
            promptPrinter = { Prompts.printGetNumMines(maxMines) },
            parser = { parseNumMines(it, maxMines) }
        )
    }

    fun parseUserSelection(input: String, board: Board, gridSize: Int): Pair<Int, Int>? {
        if (input == "show all") {
            board.printBoard(revealAll = true)
            return null
        }
        return parseCoordinates(input, gridSize)
    }

    fun getValidUserSelection(board: Board, gameConfig: GameConfig): Pair<Int, Int> {
        return InputParser.getValidatedInput(
            promptPrinter = { Prompts.printSelectSquare() },
            errorPrinter = { Prompts.printInvalidSquareSelection() }
        ) { input -> parseUserSelection(input, board, gameConfig.gridSize) }
    }

    fun getAnyKeyGameEnd() {
        InputParser.getValidatedInput(
            promptPrinter = { Prompts.printPlayAgain() },
            parser = { it }
        )
    }
}
