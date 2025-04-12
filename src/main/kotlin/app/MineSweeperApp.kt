package app

import domain.Game
import domain.GameConfig
import exceptions.ExitGameException
import exceptions.RestartGameException

class MineSweeperApp {
    companion object {
        fun run() {
            Messages.printWelcome()
            runGameLoop()
        }

        private fun runGameLoop() {
            while (true) {
                try {
                    val gameConfig = GameConfig()
                    val game = Game(gameConfig)
                    game.play()
                    game.end()
                } catch (e: ExitGameException) {
                    Messages.printExitMessage()
                    break
                } catch (e: RestartGameException) {
                    Messages.printRestartMessage()
                }
            }
        }
    }
}
