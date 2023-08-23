package uk.co.avsoftware.martianrobots.model

enum class RobotDirection {
    N, S, E, W;

    companion object {
        fun of(name: String): RobotDirection =
            values().firstOrNull { it.name == name } ?: N
    }
}
