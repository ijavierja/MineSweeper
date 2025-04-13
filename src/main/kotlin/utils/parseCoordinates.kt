package utils

fun parseCoordinates(input: String, gridSize: Int): Pair<Int, Int>? {
    val regex = Regex("([A-Za-z])(\\d+)")
    val match = regex.matchEntire(input) ?: return null

    val rowChar = match.groupValues[1].uppercase()[0]
    val col = match.groupValues[2].toIntOrNull()?.minus(1) ?: return null
    val row = rowChar - 'A'

    return if (row in 0 until gridSize && col in 0 until gridSize) {
        Pair(row, col)
    } else null
}