object Messages {
    fun printWelcome() {
        println("Welcome to Minesweeper!")
        println("Type \"help\" for list of commands")
    }

    fun printHelp() {
        println(
            """
            Available Commands:
            - help             -> Show this help message
            - exit             -> Exit the game
            - restart          -> Restart the game
            During the game:
            - A1, B3, etc.     -> Reveal a cell at the specified position
            - show all         -> Show the location of the mines
            """.trimIndent()
        )
    }

    fun printExitMessage() {
        println("Thanks for playing!")
    }

    fun printRestartMessage() {
        println("Game restarted!")
    }

    fun printMinefieldHeader(firstTime: Boolean) {
        println("Here is your${if (firstTime) "" else " updated"} minefield:")
    }

    fun printGameOver() {
        println("Oh no, you detonated a mine! Game over.")
    }

    fun printVictory() {
        println("Congratulations, you have won the game!")
    }
}
