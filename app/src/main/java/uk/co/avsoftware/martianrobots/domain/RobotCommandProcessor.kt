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

        var x = robotPosition.x
        var y = robotPosition.y
        var direction = robotPosition.direction

        commands.forEach {
            currentPosition = when (RobotCommand.valueOf(it.toString())){
                RobotCommand.F -> moveForward(planetBounds = bounds, startPosition = currentPosition)
                RobotCommand.L -> turnLeft(startPosition = currentPosition)
                RobotCommand.R -> turnRight(startPosition = currentPosition)
            }
        }

        return RobotPosition(-1, 0-1, RobotDirection.Lost)
    }

    private fun turnLeft(startPosition: RobotPosition): RobotPosition =
        when (startPosition.direction){
            RobotDirection.North -> startPosition.copy(direction = RobotDirection.West)
            RobotDirection.South -> startPosition.copy(direction = RobotDirection.East)
            RobotDirection.East -> startPosition.copy(direction = RobotDirection.North)
            RobotDirection.West -> startPosition.copy(direction = RobotDirection.South)
            else -> startPosition
        }

    private fun turnRight(startPosition: RobotPosition): RobotPosition =
        when (startPosition.direction){
            RobotDirection.North -> startPosition.copy(direction = RobotDirection.East)
            RobotDirection.South -> startPosition.copy(direction = RobotDirection.West)
            RobotDirection.East -> startPosition.copy(direction = RobotDirection.South)
            RobotDirection.West -> startPosition.copy(direction = RobotDirection.North)
            else -> startPosition
        }

    private fun moveForward(planetBounds: PlanetBounds, startPosition: RobotPosition): RobotPosition =
        when (startPosition.direction){
            RobotDirection.North -> startPosition.copy(y = startPosition.y+1).checkBounds(planetBounds)
            RobotDirection.South -> startPosition.copy(y = startPosition.y-1).checkBounds(planetBounds)
            RobotDirection.East -> startPosition.copy(x = startPosition.x+1).checkBounds(planetBounds)
            RobotDirection.West -> startPosition.copy(x = startPosition.x-1).checkBounds(planetBounds)
            else -> startPosition
        }

    private fun RobotPosition.checkBounds(bounds: PlanetBounds): RobotPosition =
        when {
            (x > bounds.width || x < 0 ) -> copy(direction = RobotDirection.Lost)
            (y > bounds.height || y < 0 ) -> copy(direction = RobotDirection.Lost)
            else -> this
        }
}