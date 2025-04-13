package ui

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ConsoleTest {

    @Test
    fun `parseGridSize returns null for non-integer input`() {
        assertNull(Console.parseGridSize("abc"))
    }

    @Test
    fun `parseGridSize returns null for out-of-range numbers`() {
        assertNull(Console.parseGridSize("1"))
        assertNull(Console.parseGridSize("27"))
    }

    @Test
    fun `parseGridSize returns valid integer within range`() {
        assertEquals(5, Console.parseGridSize("5"))
        assertEquals(26, Console.parseGridSize("26"))
    }

    @Test
    fun `parseNumMines returns null for non-integer input`() {
        assertNull(Console.parseNumMines("abc", 10))
    }

    @Test
    fun `parseNumMines returns null for out-of-range numbers`() {
        assertNull(Console.parseNumMines("0", 5))
        assertNull(Console.parseNumMines("6", 5))
    }

    @Test
    fun `parseNumMines returns valid integer within range`() {
        assertEquals(1, Console.parseNumMines("1", 5))
        assertEquals(5, Console.parseNumMines("5", 5))
    }
}
