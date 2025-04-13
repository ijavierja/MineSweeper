package utils

import exceptions.ExitGameException
import exceptions.HelpCalledException
import exceptions.RestartGameException
import java.util.*

object InputParser {
    private val scanner = Scanner(System.`in`)

    private fun getInput(): String {
        val input = scanner.nextLine().trim()

        if (input.equals("exit", ignoreCase = true)) {
            throw ExitGameException()
        }
        if (input.equals("restart", ignoreCase = true)) {
            throw RestartGameException()
        }
        if (input.equals("help", ignoreCase = true)) {

            throw HelpCalledException()
        }

        return input
    }

    fun <T> getValidatedInput(
        promptPrinter: () -> Unit,
        errorPrinter: () -> Unit = { Prompts.printErrorDefault() },
        parser: (String) -> T?
    ): T {
        while (true) {
            promptPrinter()
            try {
                val input = getInput()
                val result = parser(input)
                if (result != null) return result
                errorPrinter()
            } catch (e: HelpCalledException) {
                Prompts.printHelp()
                continue
            }
        }
    }
}


