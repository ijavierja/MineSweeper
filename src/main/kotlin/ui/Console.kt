package ui

import domain.Board
import domain.GameConfig
import utils.InputParser
import utils.parseCoordinates

object Console {
    fun getGridSizeInput(): Int {
        return InputParser.getValidatedInput(
            promptPrinter = {Prompts.printGetGridSize()},
            errorPrinter = {Prompts.printInvalidGridSize()},
            parser = { it.toIntOrNull()?.takeIf { n -> n in 2..26 } }
        )
    }

    fun getNumMinesInput(maxMines: Int): Int {
        return InputParser.getValidatedInput(
            promptPrinter = { Prompts.printGetNumMines(maxMines) },
            parser = { it.toIntOrNull()?.takeIf { n -> n in 1..maxMines } }
        )
    }

    fun getValidUserSelection(board: Board, gameConfig: GameConfig): Pair<Int, Int> {
        return InputParser.getValidatedInput(
            promptPrinter = {Prompts.printSelectSquare()},
            errorPrinter = {Prompts.printInvalidSquareSelection()}
        ) { input ->
            if (input == "show all") {
                board.printBoard(revealAll = true)
                return@getValidatedInput null
            }
            parseCoordinates(input, gameConfig.gridSize)
        }
    }

    fun getAnyKeyGameEnd() {
        InputParser.getValidatedInput(
            promptPrinter = {Prompts.printPlayAgain()},
            parser = { it }
        )
    }
}