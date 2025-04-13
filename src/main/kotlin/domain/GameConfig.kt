package domain

import Prompts
import ui.Console
import utils.InputParser

class GameConfig() {
    val gridSize: Int
    val numMines: Int

    init {
        gridSize = Console.getGridSizeInput()
        val maxMines = (gridSize * gridSize * 0.35).toInt()
        numMines = Console.getNumMinesInput(maxMines)
    }
}
