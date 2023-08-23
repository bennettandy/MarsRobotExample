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
        return runCommand(bounds, robotPosition, commands)
    }

    private tailrec fun runCommand(bounds: PlanetBounds, currentPosition: RobotPosition, commandString: String): RobotPosition {
        if (commandString.isEmpty() || currentPosition.lost) return currentPosition

        val command = commandString[0]

        println("xxx command $command")

        val newPosition = when (RobotCommand.valueOf(command.toString())) {
            RobotCommand.F -> moveForward(
                planetBounds = bounds,
                startPosition = currentPosition,
            )

            RobotCommand.L -> turnLeft(startPosition = currentPosition)
            RobotCommand.R -> turnRight(startPosition = currentPosition)
        }

        println("New position $newPosition")

        return runCommand(bounds, newPosition, commandString.drop(1))
    }

    private fun turnLeft(startPosition: RobotPosition): RobotPosition =
        when (startPosition.direction) {
            RobotDirection.N -> startPosition.copy(direction = RobotDirection.W)
            RobotDirection.S -> startPosition.copy(direction = RobotDirection.E)
            RobotDirection.E -> startPosition.copy(direction = RobotDirection.N)
            RobotDirection.W -> startPosition.copy(direction = RobotDirection.S)
        }

    private fun turnRight(startPosition: RobotPosition): RobotPosition =
        when (startPosition.direction) {
            RobotDirection.N -> startPosition.copy(direction = RobotDirection.E)
            RobotDirection.S -> startPosition.copy(direction = RobotDirection.W)
            RobotDirection.E -> startPosition.copy(direction = RobotDirection.S)
            RobotDirection.W -> startPosition.copy(direction = RobotDirection.N)
        }

    private fun moveForward(
        planetBounds: PlanetBounds,
        startPosition: RobotPosition,
    ): RobotPosition {
        val result = if (!startPosition.lost) {
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
        return result
    }

    private fun RobotPosition.checkBounds(bounds: PlanetBounds, startPosition: RobotPosition): RobotPosition {
        println("Check Bounds dir: $direction x:$x, y:$y, lost=$lost")

        val result = when {
            (x >= bounds.width || x < 0) -> startPosition.copy(lost = true)
            (y >= bounds.height || y < 0) -> startPosition.copy(lost = true)
            else -> this
        }
        return result
    }
}
