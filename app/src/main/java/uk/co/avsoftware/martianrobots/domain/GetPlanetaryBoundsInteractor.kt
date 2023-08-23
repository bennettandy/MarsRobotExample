package uk.co.avsoftware.martianrobots.domain

import uk.co.avsoftware.martianrobots.model.PlanetBounds

class GetPlanetaryBoundsInteractor(
    private val getCoordinatesFromStringInteractor: GetCoordinatesFromStringInteractor
) {
    operator fun invoke(boundsString: String): PlanetBounds =
        with(getCoordinatesFromStringInteractor(boundsString.trim())) {
            PlanetBounds(
                width = first,
                height = second,
            )
        }
}
