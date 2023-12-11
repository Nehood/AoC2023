import data.Day10Point

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

    fun getDistanceToFurthestPosition(pipesSketch: List<String>): Int {
        val pipesMap = pipesSketch.map { it.toCharArray() }
        val distancesMap = pipesMap.traversePipeLoop()
        return distancesMap.findMax()
    }

    private fun List<CharArray>.findStartingPoint(): Day10Point {
        for ( (y, line) in this.withIndex() ) {
            if (line.contains(START)) return Day10Point(y, line.indexOf(START))
        }
        return Day10Point(-1, -1)
    }

    private fun List<CharArray>.traversePipeLoop(): List<IntArray> {
        val distancesMap = this.map { IntArray(it.size).map { DEFAULT_DISTANCE }.toIntArray() }
        val startingPoint = this.findStartingPoint()
        this.replaceStartingPoint(startingPoint)
        distancesMap[startingPoint.y][startingPoint.x] = 0
        val connectedPipes = this.getConnectedPipes(startingPoint, startingPoint)
        for (connectedPipe in connectedPipes) {
            this.traversePipeLoopHelper(startingPoint, startingPoint, connectedPipe, distancesMap, 1)
        }

        return distancesMap
    }

    private tailrec fun List<CharArray>.traversePipeLoopHelper(startingPoint: Day10Point, previous: Day10Point, currentPoint: Day10Point, distancesMap: List<IntArray>, currentDistance: Int){
        if (startingPoint == currentPoint) return
        if (distancesMap[currentPoint.y][currentPoint.x] > currentDistance || distancesMap[currentPoint.y][currentPoint.x] == DEFAULT_DISTANCE) distancesMap[currentPoint.y][currentPoint.x] = currentDistance
        val connectedPipes = this.getConnectedPipes(previous, currentPoint)
        for (connectedPipe in connectedPipes) {
            return traversePipeLoopHelper(startingPoint, currentPoint, connectedPipe, distancesMap, currentDistance + 1)
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
}