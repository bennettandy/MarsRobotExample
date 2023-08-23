package uk.co.avsoftware.martianrobots.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import uk.co.avsoftware.martianrobots.model.PlanetBounds

class TestPlanetaryBoundsInteractor {

    @Test
    fun testBoundsInteractor() {
        val sut = GetPlanetaryBoundsInteractor(
            getCoordinatesFromStringInteractor = GetCoordinatesFromStringInteractor(),
        )

        val result: PlanetBounds = sut.invoke("53")
        assertEquals(PlanetBounds(width = 6, height = 4), result)
    }
}