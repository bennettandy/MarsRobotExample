package uk.co.avsoftware.martianrobots.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import uk.co.avsoftware.martianrobots.model.PlanetBounds
import uk.co.avsoftware.martianrobots.model.RobotDirection
import uk.co.avsoftware.martianrobots.model.RobotPosition

class GetRobotPositionInteractorTest {

    @Test
    fun testGetRobotPosition() {
        val sut = GetRobotPositionInteractor(
            getCoordinatesFromStringInteractor = GetCoordinatesFromStringInteractor(),
        )

        val result: RobotPosition = sut.invoke("53N")
        assertEquals(RobotPosition(x = 5, y = 3, RobotDirection.N), result)
    }
}