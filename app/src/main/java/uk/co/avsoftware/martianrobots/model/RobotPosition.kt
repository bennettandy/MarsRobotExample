package uk.co.avsoftware.martianrobots.model

data class RobotPosition(
    val x: Int,
    val y: Int,
    val direction: RobotDirection,
    val lost: Boolean = false,
) {
    override fun toString(): String =
        if (lost) {
            "$x$y${direction.name}LOST"
        } else {
            "$x$y${direction.name}"
        }
}
