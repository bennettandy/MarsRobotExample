package uk.co.avsoftware.martianrobots

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * End to end test of the challenge
 */
class TestRobotPositionCommandProcessor {
    @Test
    fun robotPositionsAreCalculatedCorrectly() {
        // given
        val sut = RobotPositionCommandProcessor()

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
