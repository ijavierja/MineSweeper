package input

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
        prompt: String,
        errorMessage: String = "Invalid input.",
        parser: (String) -> T?
    ): T {
        while (true) {
            println(prompt)
            try {
                val input = getInput()
                val result = parser(input)
                if (result != null) return result
                println(errorMessage)
            } catch (e: HelpCalledException) {
                Messages.printHelp()
                continue
            }
        }
    }

}


