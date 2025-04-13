package domain

import Prompts
import ui.Console

class Game(private val gameConfig: GameConfig) {

    private val board = Board(gameConfig)

    fun play() {
        Prompts.printMinefieldHeader(firstTime = true)
        board.printBoard()

        while (true) {
            val (row, col) = Console.getValidUserSelection(board, gameConfig)

            if (board.isMine(row, col)) {
                Prompts.printGameOver()
                return
            }

            board.revealCell(row, col)

            if (board.hasWon()) {
                Prompts.printVictory()
                return
            }

            Prompts.printMinefieldHeader(firstTime = false)
            board.printBoard()
        }
    }

    fun end() {
        Console.getAnyKeyGameEnd()
    }

}
