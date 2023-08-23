package uk.co.avsoftware.martianrobots.domain

import uk.co.avsoftware.martianrobots.model.RobotDirection
import uk.co.avsoftware.martianrobots.model.RobotPosition

class GetRobotPositionInteractor(
    private val getCoordinatesFromStringInteractor: GetCoordinatesFromStringInteractor
) {
    operator fun invoke(position: String): RobotPosition =
        with(getCoordinatesFromStringInteractor(position.dropLast(1))) {
            RobotPosition(x = first, y = second, direction = RobotDirection.of(position.trim().last().toString()))
        }
}
