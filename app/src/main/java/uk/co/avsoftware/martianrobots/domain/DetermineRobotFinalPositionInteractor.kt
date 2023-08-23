package uk.co.avsoftware.martianrobots.domain

import uk.co.avsoftware.martianrobots.model.PlanetBounds
import uk.co.avsoftware.martianrobots.model.RobotDirection
import uk.co.avsoftware.martianrobots.model.RobotPosition

class DetermineRobotFinalPositionInteractor {
    fun determineFinalPosition(planetBounds: PlanetBounds, startPosition: RobotPosition, instructions: String): String {
        return RobotDirection.Lost.statusString
    }
}
