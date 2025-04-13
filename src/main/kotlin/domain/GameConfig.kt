package domain

import ui.Console

class GameConfig(
    val gridSize: Int,
    val numMines: Int
) {
    companion object {
        fun fromConsole(): GameConfig {
            val size = Console.getGridSizeInput()
            val maxMines = (size * size * 0.35).toInt()
            val mines = Console.getNumMinesInput(maxMines)
            return GameConfig(size, mines)
        }
    }
}

