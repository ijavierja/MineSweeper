package ui

import domain.Cell
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class PromptsTest {

    private val stdOut = ByteArrayOutputStream()
    private val originalOut = System.out

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(stdOut))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(originalOut)
        stdOut.reset()
    }

    @Test
    fun `printWelcome outputs correct welcome message`() {
        Prompts.printWelcome()
        val expected = """
            Welcome to Minesweeper!
            Type "help" for list of commands
        """.trimIndent()
        assertEquals(expected, stdOut.toString().trim())
    }

    @Test
    fun `printHelp outputs correct help message`() {
        Prompts.printHelp()
        val expected = """
            Available Commands:
            - help             -> Show this help message
            - exit             -> Exit the game
            - restart          -> Restart the game
            During the game:
            - A1, B3, etc.     -> Reveal a cell at the specified position
            - show all         -> Show the location of the mines
        """.trimIndent()
        assertEquals(expected, stdOut.toString().trim())
    }

    @Test
    fun `printMinefieldHeader prints correct first time message`() {
        Prompts.printMinefieldHeader(true)
        assertEquals("Here is your minefield:", stdOut.toString().trim())
    }

    @Test
    fun `printMinefieldHeader prints correct updated message`() {
        Prompts.printMinefieldHeader(false)
        assertEquals("Here is your updated minefield:", stdOut.toString().trim())
    }

    @Test
    fun `printRevealedCell prints singular adjacent mine correctly`() {
        val cell = Cell().apply { adjacentMines = 1 }
        Prompts.printRevealedCell(cell)
        assertEquals("This square contains 1 adjacent mine.", stdOut.toString().trim())
    }

    @Test
    fun `printRevealedCell prints plural adjacent mines correctly`() {
        val cell = Cell().apply { adjacentMines = 3 }
        Prompts.printRevealedCell(cell)
        assertEquals("This square contains 3 adjacent mines.", stdOut.toString().trim())
    }

    @Test
    fun `printGetGridSize outputs correct prompt`() {
        Prompts.printGetGridSize()
        assertEquals("Enter the size of the grid (e.g. 4 for a 4x4 grid):", stdOut.toString().trim())
    }

    @Test
    fun `printInvalidGridSize outputs correct error`() {
        Prompts.printInvalidGridSize()
        assertEquals("Invalid input. Min is 2; Max is 26.", stdOut.toString().trim())
    }

    @Test
    fun `printGetNumMines outputs correct prompt`() {
        Prompts.printGetNumMines(10)
        assertEquals("Enter the number of mines to place on the grid (maximum is 10):", stdOut.toString().trim())
    }

    @Test
    fun `printErrorDefault outputs correct message`() {
        Prompts.printErrorDefault()
        assertEquals("Invalid input.", stdOut.toString().trim())
    }
}
