package domain

import Prompts
import ui.Console

class Game(private val gameConfig: GameConfig) {

    private val board = Board(gameConfig)

    fun play() {
        showInitialMinefield()
        board.printBoard()

        while (true) {
            val (row, col) = Console.getValidUserSelection(board, gameConfig)

            if (handleMineHit(row, col)) return
            if (board.hasWon()) {
                showVictory()
                return
            }

            showNextRound()
            board.printBoard()
        }
    }

    private fun showInitialMinefield() {
        Prompts.printMinefieldHeader(firstTime = true)
    }

    private fun showNextRound() {
        Prompts.printMinefieldHeader(firstTime = false)
    }

    private fun showVictory() {
        Prompts.printVictory()
    }

    private fun handleMineHit(row: Int, col: Int): Boolean {
        if (board.isMine(row, col)) {
            Prompts.printGameOver()
            return true
        }
        board.revealCell(row, col)
        return false
    }

    fun end() {
        Console.getAnyKeyGameEnd()
    }
}

