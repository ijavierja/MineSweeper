package domain

import input.InputParser

class GameConfig() {
    val gridSize: Int
    val numMines: Int

    init {
        gridSize = getGridSizeInput()
        val maxMines = (gridSize * gridSize * 0.35).toInt()
        numMines = getNumMinesInput(maxMines)
    }

    private fun getGridSizeInput(): Int {
        return InputParser.getValidatedInput(
            prompt = "Enter the size of the grid (e.g. 4 for a 4x4 grid):",
            errorMessage = "Invalid input. Min is 2; Max is 26.",
            parser = { it.toIntOrNull()?.takeIf { n -> n in 2..26 } }
        )
    }

    private fun getNumMinesInput(maxMines: Int): Int {
        return InputParser.getValidatedInput(
            prompt = "Enter the number of mines to place on the grid (maximum is $maxMines):",
            parser = { it.toIntOrNull()?.takeIf { n -> n in 1..maxMines } }
        )
    }
}
