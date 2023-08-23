package uk.co.avsoftware.martianrobots

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import uk.co.avsoftware.martianrobots.domain.GetPlanetaryBoundsInteractor
import uk.co.avsoftware.martianrobots.domain.GetRobotPositionInteractor
import uk.co.avsoftware.martianrobots.domain.RobotCommandProcessor
import uk.co.avsoftware.martianrobots.model.PlanetBounds
import uk.co.avsoftware.martianrobots.model.RobotPosition
import kotlin.coroutines.CoroutineContext

class RobotPositionCommandProcessor(
    private val getBoundsInteractor: GetPlanetaryBoundsInteractor,
    private val getPositionInteractor: GetRobotPositionInteractor,
    private val commandProcessor: RobotCommandProcessor,
) {
    fun processRobotCommandString(inputString: String): String {
        val lines = inputString.lines()
        val bounds = getBoundsInteractor(lines.first())

        return processRobotCommand(bounds = bounds, commands = lines.drop(1), results = emptyList())
            .joinToString(separator = "\n")
    }

    private tailrec fun processRobotCommand(bounds: PlanetBounds, commands: List<String>, results: List<String>): List<String> {
        if (commands.isEmpty()) return results
        val location: RobotPosition = getPositionInteractor(commands[0])
        val commandString: String = commands[1]
        val finalPosition: RobotPosition = commandProcessor.processCommands(bounds, location, commandString)
        println("Start position: $location command $commandString final $finalPosition")
        return processRobotCommand(bounds = bounds, commands = commands.drop(3), results = results.plus(finalPosition.toString()))
    }
}
