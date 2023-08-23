package uk.co.avsoftware.martianrobots.model

sealed class RobotDirection(val statusString: String) {
    object North : RobotDirection("N")
    object South : RobotDirection("S")
    object East : RobotDirection("E")
    object West : RobotDirection("W")
    object Lost : RobotDirection("LOST")
}