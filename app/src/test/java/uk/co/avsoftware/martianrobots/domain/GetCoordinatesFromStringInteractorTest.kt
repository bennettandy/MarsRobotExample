package uk.co.avsoftware.martianrobots.domain

import org.junit.Assert
import org.junit.Test

class GetCoordinatesFromStringInteractorTest {
    @Test
    fun testGetCoordinatesFromString() {
        val sut = GetCoordinatesFromStringInteractor()

        val result: Pair<Int, Int> = sut.invoke("53")
        Assert.assertEquals(5 to 3, result)
    }
}