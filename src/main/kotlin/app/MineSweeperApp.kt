package app

import domain.Game
import domain.GameConfig
import exceptions.ExitGameException
import exceptions.RestartGameException

class MineSweeperApp {
    companion object {
        fun run() {
            Prompts.printWelcome()
            runGameLoop()
        }

        private fun runGameLoop() {
            while (true) {
                try {
                    val gameConfig = GameConfig.fromConsole()
                    val game = Game(gameConfig)
                    game.play()
                    game.end()
                } catch (e: ExitGameException) {
                    Prompts.printExitMessage()
                    break
                } catch (e: RestartGameException) {
                    Prompts.printRestartMessage()
                }
            }
        }
    }
}
