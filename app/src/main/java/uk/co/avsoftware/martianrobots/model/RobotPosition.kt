package uk.co.avsoftware.martianrobots.model

data class RobotPosition(
    val x: Int,
    val y: Int,
    val direction: RobotDirection,
) {
    override fun toString(): String =
        "$x$y${direction.statusString}"
}
