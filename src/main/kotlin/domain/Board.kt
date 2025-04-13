package domain

class Board(
    private val gameConfig: GameConfig
) {

    private val grid: Array<Array<Cell>> = Array(gameConfig.gridSize) { Array(gameConfig.gridSize) { Cell() } }
    private var unrevealed = (gameConfig.gridSize * gameConfig.gridSize) - gameConfig.numMines
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
            unrevealed--

            if (current.adjacentMines == 0 && !current.isMine) {
                for (nr in (r - 1)..(r + 1)) {
                    for (nc in (c - 1)..(c + 1)) {
                        if (nr in 0 until gameConfig.gridSize && nc in 0 until gameConfig.gridSize && !(nr == r && nc == c)) {
                            val neighbor = grid[nr][nc]
                            if (!neighbor.isRevealed && !neighbor.isMine) {
                                toReveal.add(nr to nc)
                            }
                        }
                    }
                }
            }
        }

        return true
    }


    fun hasWon(): Boolean {
        return unrevealed == 0
    }

    fun isMine(row: Int, col: Int): Boolean {
        return grid[row][col].isMine
    }

    fun printBoard(revealAll: Boolean = false) {
        Prompts.printBoard(gameConfig.gridSize, grid, revealAll)
    }
}
