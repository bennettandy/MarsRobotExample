package uk.co.avsoftware.martianrobots.model

enum class RobotDirection(val statusString: String) {
    North("N"), South("S"), East("E"), West("W"), Lost("LOST");

    companion object {
        fun of(directionString: String) =
            enumValues<RobotDirection>().firstOrNull { it.statusString == directionString }
                ?: Lost
    }
}
