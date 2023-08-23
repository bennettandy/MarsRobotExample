package uk.co.avsoftware.martianrobots.domain

import uk.co.avsoftware.martianrobots.model.RobotDirection
import uk.co.avsoftware.martianrobots.model.RobotPosition

class GetRobotPositionInteractor(
    private val getCoordinatesFromStringInteractor: GetCoordinatesFromStringInteractor,
) {
    operator fun invoke(position: String): RobotPosition {
        val location = position.trim().dropLast(1)
        return with(getCoordinatesFromStringInteractor(location)) {
            val positionLastCharacter = position.trim().takeLast(1)
            RobotPosition(x = first, y = second, direction = RobotDirection.of(positionLastCharacter))
        }.also {
            println("Robot Start Position: $it")
        }
    }
}
