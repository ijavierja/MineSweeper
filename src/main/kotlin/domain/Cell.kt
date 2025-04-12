package domain

data class Cell(
    var isMine: Boolean = false,
    var isRevealed: Boolean = false,
    var adjacentMines: Int = 0
)
