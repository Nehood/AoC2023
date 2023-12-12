import data.Day10Point
import kotlin.math.abs

class Day10PipeMaze {

    private val NS = '|'
    private val EW = '-'
    private val NE = 'L'
    private val NW = 'J'
    private val SW = '7'
    private val SE = 'F'

    private val GROUND = '.'
    private val START = 'S'

    private val PIPES = setOf(NS, EW, NE, NW, SW, SE)

    private val DEFAULT_DISTANCE = -1
    private val INSIDE_LOOP = -2

    fun getDistanceToFurthestPosition(pipesSketch: List<String>): Int {
        val pipesMap = pipesSketch.map { it.toCharArray() }
        val distancesMap = pipesMap.traversePipeLoop()
        return distancesMap.findMax()
    }

    fun countSpacesInsideLoop(pipesSketch: List<String>): Int {
        val pipesMap = pipesSketch.map { it.toCharArray() }
        val distancesMap = pipesMap.traversePipeLoop()
        distancesMap.draw()
        distancesMap.markInnerAndOuterLoops()
        println()
        println()
        distancesMap.draw()
        return distancesMap.countSpacesInside()
    }

    private fun List<CharArray>.findStartingPoint(): Day10Point {
        for ( (y, line) in this.withIndex() ) {
            if (line.contains(START)) return Day10Point(y, line.indexOf(START))
        }
        return Day10Point(-1, -1)
    }

    private fun List<CharArray>.traversePipeLoop(onePath: Boolean = false): List<IntArray> {
        val distancesMap = this.map { IntArray(it.size).map { DEFAULT_DISTANCE }.toIntArray() }
        val startingPoint = this.findStartingPoint()
        this.replaceStartingPoint(startingPoint)
        distancesMap[startingPoint.y][startingPoint.x] = 0
        val connectedPipes = this.getConnectedPipes(startingPoint, startingPoint)
        for (connectedPipe in connectedPipes) {
            this.traversePipeLoopHelper(startingPoint, startingPoint, connectedPipe, distancesMap, 1, onePath)
        }

        return distancesMap
    }

    private tailrec fun List<CharArray>.traversePipeLoopHelper(startingPoint: Day10Point, previous: Day10Point, currentPoint: Day10Point, distancesMap: List<IntArray>, currentDistance: Int, onePath: Boolean){
        if (startingPoint == currentPoint) return
        if (onePath){
            if (distancesMap[currentPoint.y][currentPoint.x] == DEFAULT_DISTANCE) distancesMap[currentPoint.y][currentPoint.x] = currentDistance
        } else {
            if (distancesMap[currentPoint.y][currentPoint.x] > currentDistance || distancesMap[currentPoint.y][currentPoint.x] == DEFAULT_DISTANCE) distancesMap[currentPoint.y][currentPoint.x] = currentDistance
        }

        val connectedPipes = this.getConnectedPipes(previous, currentPoint)
        for (connectedPipe in connectedPipes) {
            return traversePipeLoopHelper(startingPoint, currentPoint, connectedPipe, distancesMap, currentDistance + 1, onePath)
        }
        return
    }

    private fun List<CharArray>.getConnectedPipes(previousPoint: Day10Point, currentPoint: Day10Point): List<Day10Point> {
        if (previousPoint == currentPoint) {
            return when (this[currentPoint.y][currentPoint.x]) {
                NS -> listOf(
                    Day10Point(currentPoint.y + 1, currentPoint.x),
                    Day10Point(currentPoint.y - 1, currentPoint.x)
                )

                EW -> listOf(
                    Day10Point(currentPoint.y, currentPoint.x - 1),
                    Day10Point(currentPoint.y, currentPoint.x + 1)
                )

                NE -> listOf(
                    Day10Point(currentPoint.y + 1, currentPoint.x),
                    Day10Point(currentPoint.y, currentPoint.x + 1)
                )

                NW -> listOf(
                    Day10Point(currentPoint.y - 1, currentPoint.x),
                    Day10Point(currentPoint.y, currentPoint.x - 1)
                )

                SW -> listOf(
                    Day10Point(currentPoint.y + 1, currentPoint.x),
                    Day10Point(currentPoint.y, currentPoint.x - 1)
                )

                SE -> listOf(
                    Day10Point(currentPoint.y + 1, currentPoint.x),
                    Day10Point(currentPoint.y, currentPoint.x + 1)
                )

                else -> throw Exception("Outside of loop!")
            }
        }
        val connectedPipe = when (this[currentPoint.y][currentPoint.x]) {
            NS -> if (previousPoint.y < currentPoint.y) Day10Point(currentPoint.y + 1, currentPoint.x) else Day10Point(currentPoint.y - 1, currentPoint.x)
            EW -> if (previousPoint.x < currentPoint.x) Day10Point(currentPoint.y, currentPoint.x + 1) else Day10Point(currentPoint.y, currentPoint.x - 1)
            NE -> if (previousPoint.y != currentPoint.y) Day10Point(currentPoint.y, currentPoint.x + 1) else Day10Point(currentPoint.y - 1, currentPoint.x)
            NW -> if (previousPoint.y != currentPoint.y) Day10Point(currentPoint.y, currentPoint.x - 1) else Day10Point(currentPoint.y - 1, currentPoint.x)
            SW -> if (previousPoint.y != currentPoint.y) Day10Point(currentPoint.y, currentPoint.x - 1) else Day10Point(currentPoint.y + 1, currentPoint.x)
            SE -> if (previousPoint.y != currentPoint.y) Day10Point(currentPoint.y, currentPoint.x + 1) else Day10Point(currentPoint.y + 1, currentPoint.x)
            else -> throw Exception("Outside of loop!")
        }
        return listOf(connectedPipe)
    }

    private fun List<CharArray>.replaceStartingPoint(startingPoint: Day10Point) {
        val isSouthPipe = if (startingPoint.y > 0) PIPES.contains(this[startingPoint.y - 1][startingPoint.x]) else false
        val isNorthPipe = if (startingPoint.y < this.lastIndex) PIPES.contains(this[startingPoint.y + 1][startingPoint.x]) else false
        val isWestPipe = if (startingPoint.x > 0) PIPES.contains(this[startingPoint.y][startingPoint.x - 1]) else false
        val isEastPipe = if (startingPoint.x < this[startingPoint.y].lastIndex) PIPES.contains(this[startingPoint.y][startingPoint.x + 1]) else false
        val startingPointPipeType = when {
            isNorthPipe && isSouthPipe -> NS
            isWestPipe && isEastPipe -> EW
            isNorthPipe && isEastPipe -> NE
            isNorthPipe && isWestPipe -> NW
            isSouthPipe && isWestPipe -> SW
            isSouthPipe && isEastPipe -> SE
            else -> throw Exception("Start point is not a pipe!")
        }
        this[startingPoint.y][startingPoint.x] = startingPointPipeType
    }

    private fun List<IntArray>.findMax(): Int {
        var max = Integer.MIN_VALUE
        for (line in this) {
            if (line.max() > max) max = line.max()
        }
        return max
    }

    private fun List<IntArray>.markInnerAndOuterLoops() {
        for ((y, line) in this.withIndex()) {
            for ((x, symbol) in line.withIndex()) {
                if (this[y][x] == DEFAULT_DISTANCE) {
                    if (y == 0 || x == 0 || y == this.lastIndex || x == this[y].lastIndex ) continue
                    if (this.castRayXNew(y, x, x + 1 .. this[y].lastIndex)) this[y][x] = INSIDE_LOOP
                }
            }
        }
    }

    private fun List<IntArray>.castRayXNew(y: Int, x: Int, xProgression: IntProgression): Boolean {
        var intersections = 0
        var continuous = false
        var fromTop = false
        var previousValue = this[y][x]
        for (curX in xProgression) {
            val currentValue = this[y][curX]

            if (currentValue > DEFAULT_DISTANCE || continuous) {
                if (abs(currentValue - previousValue) == 1) {// we are in continuous space
                    if (!continuous) {
                        continuous = true
                        val previousPreviousFromTopValue = this[y-1][curX-1]
                        if (abs(previousValue - previousPreviousFromTopValue) == 1) { // yes it is
                            fromTop = true
                        }
                        // check if the line is going from the top, or from the bottom
                    }
                } else if (continuous) { // we had a continuous space before, let's check that
                    val previousPreviousFromTopValue = this[y-1][curX-1]
                    if (abs(previousValue - previousPreviousFromTopValue) == 1) {
                        // yes, now we also have last value from top
                        if (fromTop) {   // both were from the same sign - this should not be counted as intersection
                            --intersections
                        } else {// both were from different signs - this should be counted as intersection

                        }
                    } else {    // now we have value from bottom
                        if (fromTop) {   // both were from different signs - this should be counted as intersection

                        } else { // both were from the same sign - this should not be counted as intersection
                            --intersections
                        }
                    }
                    fromTop = false
                    continuous = false
                    if (currentValue > DEFAULT_DISTANCE) {
                        ++intersections
                    }
                } else {
                    ++intersections
                }
            }
            previousValue = currentValue
        }
        // handle last element case
        if (continuous) { // we had a continuous space before, let's check that
            val previousPreviousFromTopValue = this[y-1][this[y].lastIndex]
            if (abs(previousValue - previousPreviousFromTopValue) == 1) {
                // yes, now we also have last value from top
                if (fromTop) {   // both were from the same sign - this should not be counted as intersection
                    --intersections
                } else {// both were from different signs - this should be counted as intersection

                }
            } else {    // now we have value from bottom
                if (fromTop) {   // both were from different signs - this should be counted as intersection

                } else { // both were from the same sign - this should not be counted as intersection
                    --intersections
                }
            }
        }
        return (intersections % 2 != 0)
    }

    private fun List<IntArray>.draw() {
        for ((y, line) in this.withIndex()) {
            for ((x, symbol) in line.withIndex()) {
                print(symbol.toString() + "\t")
            }
            println()
        }
    }

    private fun List<IntArray>.countSpacesInside(): Int {
        var spacesInsideCount = 0
        for ((y, line) in this.withIndex()) {
            for ((x, symbol) in line.withIndex()) {
                if (this[y][x] == INSIDE_LOOP ) ++spacesInsideCount
            }
        }
        return spacesInsideCount
    }
}