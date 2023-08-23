package uk.co.avsoftware.martianrobots.domain

import uk.co.avsoftware.martianrobots.model.PlanetBounds
import uk.co.avsoftware.martianrobots.model.RobotCommand
import uk.co.avsoftware.martianrobots.model.RobotDirection
import uk.co.avsoftware.martianrobots.model.RobotPosition

class RobotCommandProcessor {
    fun processCommands(
        bounds: PlanetBounds,
        robotPosition: RobotPosition,
        commands: String,
    ): RobotPosition {
        // mutable variables for simplicity
        var currentPosition = robotPosition

        commands.forEach {
            currentPosition = when (RobotCommand.valueOf(it.toString())) {
                RobotCommand.F -> moveForward(planetBounds = bounds, startPosition = currentPosition)
                RobotCommand.L -> turnLeft(startPosition = currentPosition)
                RobotCommand.R -> turnRight(startPosition = currentPosition)
            }
        }

        return with(currentPosition) {
            RobotPosition(x, y, direction = direction)
        }
    }

    private fun turnLeft(startPosition: RobotPosition): RobotPosition =
        when (startPosition.direction) {
            RobotDirection.N -> startPosition.copy(direction = RobotDirection.W)
            RobotDirection.S -> startPosition.copy(direction = RobotDirection.E)
            RobotDirection.E -> startPosition.copy(direction = RobotDirection.N)
            RobotDirection.W -> startPosition.copy(direction = RobotDirection.S)
            else -> startPosition
        }

    private fun turnRight(startPosition: RobotPosition): RobotPosition =
        when (startPosition.direction) {
            RobotDirection.N -> startPosition.copy(direction = RobotDirection.E)
            RobotDirection.S -> startPosition.copy(direction = RobotDirection.W)
            RobotDirection.E -> startPosition.copy(direction = RobotDirection.S)
            RobotDirection.W -> startPosition.copy(direction = RobotDirection.N)
            else -> startPosition
        }

    private fun moveForward(planetBounds: PlanetBounds, startPosition: RobotPosition): RobotPosition =
        if (!startPosition.lost) {
            when (startPosition.direction) {
                RobotDirection.N -> startPosition.copy(y = startPosition.y + 1)
                    .checkBounds(planetBounds, startPosition)

                RobotDirection.S -> startPosition.copy(y = startPosition.y - 1)
                    .checkBounds(planetBounds, startPosition)

                RobotDirection.E -> startPosition.copy(x = startPosition.x + 1)
                    .checkBounds(planetBounds, startPosition)

                RobotDirection.W -> startPosition.copy(x = startPosition.x - 1)
                    .checkBounds(planetBounds, startPosition)
            }
        } else {
            startPosition
        }

    private fun RobotPosition.checkBounds(bounds: PlanetBounds, startPosition: RobotPosition): RobotPosition =
        when {
            (x >= bounds.width || x <= 0) -> copy(lost = true)
            (y >= bounds.height || y <= 0) -> copy(lost = true)
            else -> this
        }.also {
            println("Position: $this , bounds $bounds")
        }
}
