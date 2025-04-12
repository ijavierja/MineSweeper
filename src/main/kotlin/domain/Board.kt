package domain

class Board(
    private val size: Int,
    private val numMines: Int
) {

    private val grid: Array<Array<Cell>> = Array(size) { Array(size) { Cell() } }
    private var unrevealed = (size * size) - numMines;
    init {
        placeMines()
        calculateAdjacentMines()
    }

    // Randomly places mines on the grid
    private fun placeMines() {
        var placedMines = 0
        while (placedMines < numMines) {
            val row = (0 until size).random()
            val col = (0 until size).random()

            if (!grid[row][col].isMine) {
                grid[row][col].isMine = true
                placedMines++
            }
        }
    }

    // Calculates the number of adjacent mines for each non-mine cell
    private fun calculateAdjacentMines() {
        for (row in 0 until size) {
            for (col in 0 until size) {
                if (grid[row][col].isMine) continue
                var adjacentMines = 0

                // Check all neighboring cells
                for (r in (row - 1)..(row + 1)) {
                    for (c in (col - 1)..(col + 1)) {
                        if (r in 0 until size && c in 0 until size && grid[r][c].isMine) {
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
        println("This square contains ${grid[row][col].adjacentMines} adjacent mines.")
        val toReveal = ArrayDeque<Pair<Int, Int>>()
        toReveal.add(row to col)

        while (toReveal.isNotEmpty()) {
            val (r, c) = toReveal.removeFirst()
            val current = grid[r][c]
            if (current.isRevealed) continue

            current.isRevealed = true
            unrevealed--
            // If it's empty (no adjacent mines), reveal neighbors
            if (current.adjacentMines == 0 && !current.isMine) {
                for (nr in (r - 1)..(r + 1)) {
                    for (nc in (c - 1)..(c + 1)) {
                        if (nr in 0 until size && nc in 0 until size && !(nr == r && nc == c)) {
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
        print("  ")
        for (i in 1..size) print(" $i")
        println()
        for (row in 0 until size) {
            print("${'A' + row} ")
            for (col in 0 until size) {
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

}
