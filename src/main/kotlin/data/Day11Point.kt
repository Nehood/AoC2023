package data

import kotlin.math.abs

data class Day11Point(val y: Int, val x: Int) {
    fun calculateManhattanDistance(other: Day11Point): Int {
        return (abs(this.y - other.y) + abs(this.x - other.x))
    }
}