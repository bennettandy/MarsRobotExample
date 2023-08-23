package uk.co.avsoftware.martianrobots.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import uk.co.avsoftware.martianrobots.model.PlanetBounds
import uk.co.avsoftware.martianrobots.model.RobotDirection
import uk.co.avsoftware.martianrobots.model.RobotPosition

class TestRobotCommandProcessor {

    @Test
    fun testFullRotation() {
        performRobotCommandTest(
            start = RobotPosition(x = 1, y = 1, RobotDirection.N),
            commands = "LLLL",
            expected = RobotPosition(x = 1, y = 1, RobotDirection.N),
        )
    }

    @Test
    fun testHalfRotation() {
        performRobotCommandTest(
            start = RobotPosition(x = 1, y = 1, RobotDirection.N),
            commands = "LL",
            expected = RobotPosition(x = 1, y = 1, RobotDirection.S),
        )
    }

    @Test
    // example 1 from test
    fun testExample1() {
        performRobotCommandTest(
            start = RobotPosition(x = 1, y = 1, RobotDirection.E),
            commands = "RFRFRFRF",
            expected = RobotPosition(x = 1, y = 1, RobotDirection.E, lost = false),
        )
    }

    @Test
    fun testExample2() {
        performRobotCommandTest(
            start = RobotPosition(x = 3, y = 2, RobotDirection.N),
            commands = "FRRFLLFFRRFLL",
            expected = RobotPosition(x = 3, y = 3, RobotDirection.N, lost = true),
        )
    }

    @Test
    fun testExample3() {
        performRobotCommandTest(
            start = RobotPosition(x = 0, y = 3, RobotDirection.W),
            commands = "LLFFFLFFLF",
            expected = RobotPosition(x = 3, y = 3, RobotDirection.N, lost = true),
        )
    }

    @Test
    fun testNorthOutOfBounds() {
        performRobotCommandTest(
            start = RobotPosition(x = 1, y = 1, RobotDirection.N),
            commands = "FFF",
            expected = RobotPosition(x = 1, y = 3, RobotDirection.N, lost = true),
        )
    }

    private fun performRobotCommandTest(start: RobotPosition, commands: String, expected: RobotPosition, bounds: PlanetBounds = PlanetBounds(width = 6, height = 4)) {
        val sut = RobotCommandProcessor()

        val result: RobotPosition = sut.processCommands(
            bounds = bounds,
            robotPosition = start,
            commands = commands,
        )

        assertEquals(expected, result)
    }
}
