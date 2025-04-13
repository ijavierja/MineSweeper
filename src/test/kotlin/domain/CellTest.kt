import domain.Cell
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class CellTest {

    @Test
    fun `test default cell values`() {
        val cell = Cell()

        assertFalse(cell.isMine, "Expected cell to not be a mine by default")
        assertFalse(cell.isRevealed, "Expected cell to not be revealed by default")
        assertEquals(0, cell.adjacentMines, "Expected adjacentMines to be 0 by default")
    }
}
