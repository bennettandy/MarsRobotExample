package uk.co.avsoftware.martianrobots.domain

/**
 * Extract the Coordinates from the passed in String,
 * 2 digits, no separator character with max value between 0 and 50.
 * The lack of separator makes this nasty, when we have 3 digits. XYZ = X:YZ or XY:Z
 * todo: see if we can enforce either 2 or 4 digits making splitting simple
 */
class GetCoordinatesFromStringInteractor {
    operator fun invoke(coordinateString: String): Pair<Int, Int> =
        with(coordinateString) {
            when (length) {
                2 -> intPair(substring(0..0), substring(1..1))
                4 -> intPair(substring(0..1), substring(2..3))
                // anything else is error 1, 3 or more than 4 digits
                else -> -1 to -1
            }
        }

    private fun intPair(width: String, height: String): Pair<Int, Int> =
        (width.toIntOrNull() ?: -1) to (height.toIntOrNull() ?: -1)
}
