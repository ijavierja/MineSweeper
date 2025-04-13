import domain.Board
import domain.GameConfig
import kotlin.random.Random
import kotlin.test.*

class BoardTest {

    @Test
    fun `test mine placement`() {
        val gameConfig = GameConfig(gridSize = 5, numMines = 5)
        val board = Board(gameConfig)

        var mineCount = 0

        for (row in 0 until gameConfig.gridSize) {
            for (col in 0 until gameConfig.gridSize) {
                if (board.isMine(row, col)) {
                    mineCount++
                }
            }
        }
        assertEquals(gameConfig.numMines, mineCount, "The number of mines placed does not match the expected value.")
    }

    @Test
    fun `test adjacent mines count`() {
        val gameConfig = GameConfig(gridSize = 5, numMines = 5)
        val board = Board(gameConfig)

        for (row in 0 until gameConfig.gridSize) {
            for (col in 0 until gameConfig.gridSize) {
                if (!board.isMine(row, col)) {
                    val expectedCount = countAdjacentMines(row, col, gameConfig.gridSize, board)
                    assertEquals(expectedCount, board.getCell(row, col).adjacentMines, "Adjacent mines count at ($row, $col) is incorrect.")
                }
            }
        }
    }
    private fun countAdjacentMines(row: Int, col: Int, gridSize: Int, board: Board): Int {
        var adjacentMines = 0
        for (r in (row - 1)..(row + 1)) {
            for (c in (col - 1)..(col + 1)) {
                if (r in 0 until gridSize && c in 0 until gridSize && board.isMine(r, c)) {
                    adjacentMines++
                }
            }
        }
        return adjacentMines
    }

    @Test
    fun `test reveal random non-mine cell`() {
        val gameConfig = GameConfig(gridSize = 5, numMines = 5)
        val board = Board(gameConfig)

        var row: Int
        var col: Int
        var cell: domain.Cell
        do {
            row = Random.nextInt(gameConfig.gridSize)
            col = Random.nextInt(gameConfig.gridSize)
            cell = board.getCell(row, col)
        } while (cell.isMine) // If it's a mine, pick another cell

        // Reveal the selected cell
        board.revealCell(row, col)

        // Verify the cell is revealed
        assertTrue(board.getCell(row, col).isRevealed, "The selected cell should be revealed.")
    }

    @Test
    fun `test flood fill of revealCell`() {
        // create a board with no mines
        val gameConfig = GameConfig(gridSize = 5, numMines = 0)
        val board = Board(gameConfig)
        assertFalse(board.getCell(4,4).isRevealed, "The selected cell should be initialized to unrevealed.")
        board.revealCell(0, 0)
        assertTrue(board.getCell(4,4).isRevealed, "The selected cell should be revealed.")
    }

    @Test
    fun `test revealCell updates win condition`() {
        // create a board with no mines
        val gameConfig = GameConfig(gridSize = 5, numMines = 0) // create a board with no mines
        val board = Board(gameConfig)
        assertFalse(board.hasWon(), "Internal remainingSafeCells parameter should be more than 0")
        board.revealCell(0, 0)
        assertTrue(board.hasWon(), "Internal remainingSafeCells parameter should be 0")
    }
}
