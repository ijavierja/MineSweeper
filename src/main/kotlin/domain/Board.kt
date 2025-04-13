package domain

class Board(
    private val gameConfig: GameConfig
) {

    private val grid: Array<Array<Cell>> = Array(gameConfig.gridSize) { Array(gameConfig.gridSize) { Cell() } }
    private var remainingSafeCells = (gameConfig.gridSize * gameConfig.gridSize) - gameConfig.numMines
    init {
        placeMines()
        calculateAdjacentMines()
    }

    // Randomly places mines on the grid
    private fun placeMines() {
        var placedMines = 0
        while (placedMines < gameConfig.numMines) {
            val row = (0 until gameConfig.gridSize).random()
            val col = (0 until gameConfig.gridSize).random()

            if (!grid[row][col].isMine) {
                grid[row][col].isMine = true
                placedMines++
            }
        }
    }

    private fun calculateAdjacentMines() {
        for (row in 0 until gameConfig.gridSize) {
            for (col in 0 until gameConfig.gridSize) {
                if (grid[row][col].isMine) continue
                var adjacentMines = 0

                // Check all neighboring cells
                for (r in (row - 1)..(row + 1)) {
                    for (c in (col - 1)..(col + 1)) {
                        if (r in 0 until gameConfig.gridSize && c in 0 until gameConfig.gridSize && grid[r][c].isMine) {
                            adjacentMines++
                        }
                    }
                }

                grid[row][col].adjacentMines = adjacentMines
            }
        }
    }

    private fun shouldReveal(row: Int, col: Int): Boolean {
        return row in 0 until gameConfig.gridSize &&
                col in 0 until gameConfig.gridSize &&
                !grid[row][col].isRevealed &&
                !grid[row][col].isMine
    }

    private fun neighborsOf(row: Int, col: Int): List<Pair<Int, Int>> {
        val neighbors = mutableListOf<Pair<Int, Int>>()
        for (r in (row - 1)..(row + 1)) {
            for (c in (col - 1)..(col + 1)) {
                if (r == row && c == col) continue
                if (r in 0 until gameConfig.gridSize && c in 0 until gameConfig.gridSize) {
                    neighbors.add(r to c)
                }
            }
        }
        return neighbors
    }


    fun revealCell(row: Int, col: Int): Boolean {
        val cell = grid[row][col]
        if (cell.isRevealed) return false

        Prompts.printRevealedCell(cell)

        val toReveal = ArrayDeque<Pair<Int, Int>>()
        toReveal.add(row to col)

        while (toReveal.isNotEmpty()) {
            val (r, c) = toReveal.removeFirst()
            val current = grid[r][c]
            if (current.isRevealed) continue

            current.isRevealed = true
            remainingSafeCells--

            if (current.adjacentMines == 0) {
                neighborsOf(r, c).filter { shouldReveal(it.first, it.second) }
                    .forEach { toReveal.add(it) }
            }
        }

        return true
    }


    fun hasWon(): Boolean {
        return remainingSafeCells == 0
    }

    fun isMine(row: Int, col: Int): Boolean {
        return grid[row][col].isMine
    }

    fun printBoard(revealAll: Boolean = false) {
        Prompts.printBoard(gameConfig.gridSize, grid, revealAll)
    }
}
