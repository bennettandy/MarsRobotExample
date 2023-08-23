package uk.co.avsoftware.martianrobots

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.skip
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import uk.co.avsoftware.martianrobots.domain.GetPlanetaryBoundsInteractor
import uk.co.avsoftware.martianrobots.domain.GetRobotPositionInteractor
import uk.co.avsoftware.martianrobots.domain.RobotCommandProcessor
import uk.co.avsoftware.martianrobots.model.PlanetBounds
import uk.co.avsoftware.martianrobots.model.RobotPosition
import kotlin.coroutines.CoroutineContext

class RobotPositionCommandProcessor(
    private val dispatcher: CoroutineContext = Dispatchers.IO,
    private val getBoundsInteractor: GetPlanetaryBoundsInteractor,
    private val getPositionInteractor: GetRobotPositionInteractor,
    private val commandProcessor: RobotCommandProcessor,
) {
    private val scope = CoroutineScope(dispatcher)
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

        return processRobotCommand(bounds = bounds, commands = commands.drop(2), results = results.plus(finalPosition.toString()))
    }


}
