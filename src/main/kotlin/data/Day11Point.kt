package data

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Day11Point(val y: Int, val x: Int) {
    fun calculateManhattanDistance(other: Day11Point): Int {
        return (abs(this.y - other.y) + abs(this.x - other.x))
    }

    fun calculateAncientManhattanDistance(other: Day11Point, expandedRows: List<Int>, expandedColumns: List<Int>): Long {
        val distance =  (abs(this.y - other.y) + abs(this.x - other.x)).toLong()
        val affectedRows = expandedRows.filter { it > min(this.y, other.y) && it < max(this.y, other.y)}.size
        val affectedColumns = expandedColumns.filter { it > min(this.x, other.x) && it < max(this.x, other.x)}.size

        val exp = 1000000
        return distance + ((affectedRows + affectedColumns) * exp) - (affectedRows + affectedColumns)
    }
}