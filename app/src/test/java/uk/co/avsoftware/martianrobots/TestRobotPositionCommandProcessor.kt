package uk.co.avsoftware.martianrobots

import org.junit.Assert.assertEquals
import org.junit.Test
import uk.co.avsoftware.martianrobots.domain.GetCoordinatesFromStringInteractor
import uk.co.avsoftware.martianrobots.domain.GetPlanetaryBoundsInteractor
import uk.co.avsoftware.martianrobots.domain.GetRobotPositionInteractor
import uk.co.avsoftware.martianrobots.domain.RobotCommandProcessor

/**
 * End to end test of the challenge
 */
class TestRobotPositionCommandProcessor {
    @Test
    fun robotPositionsAreCalculatedCorrectly() {
        // given
        val sut = RobotPositionCommandProcessor(
            commandProcessor = RobotCommandProcessor(),
            getPositionInteractor = GetRobotPositionInteractor(
                getCoordinatesFromStringInteractor = GetCoordinatesFromStringInteractor(),
            ),
            getBoundsInteractor = GetPlanetaryBoundsInteractor(
                getCoordinatesFromStringInteractor = GetCoordinatesFromStringInteractor(),
            ),
        )

        // when
        val result: String = sut.processRobotCommandString(TestInput)

        // then
        assertEquals(ExpectedOutput, result)
    }

    companion object {
        private val TestInput = """
            53
            11E 
            RFRFRFRF
            
            32N 
            FRRFLLFFRRFLL
            
            03W 
            LLFFFLFLFL

        """.trimIndent()

        private val ExpectedOutput = """
            11E
            33NLOST 
            23S
        """.trimIndent()
    }
}
