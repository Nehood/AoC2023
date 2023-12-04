class Day3GearRatios {
    private val DOT = '.'
    private val DEFAULT_INDEX = -1
    private val GEAR_SYMBOL = '*'

    fun sumSchematicPartNumbers(engineSchematic: List<CharArray>): Int {
        return engineSchematic.getPartNumbers().sum()
    }

    fun sumGearRatios(engineSchematic: List<CharArray>): Int {
        return engineSchematic.getGearRatios().sum()
    }

    private fun List<CharArray>.getGearRatios(): List<Int> {
        val gearRatios = mutableListOf<Int>()
        this.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c == GEAR_SYMBOL) gearRatios.add(this.getGearRatio(y, x))
            }
        }
        return gearRatios
    }

    private fun List<CharArray>.getGearRatio(y: Int, x: Int): Int {
        val gears = mutableListOf<Int>()
        for (curY in y-1..y+1) {
            if (curY < 0 || curY > this.lastIndex) continue
            var firstIndex = DEFAULT_INDEX
            var lastIndex = DEFAULT_INDEX
            for (curX in x-1..x+1) {
                if (curY == y && curX == x) continue
                if (curX < 0 || curX > this[y].lastIndex) continue
                if (lastIndex >= curX) continue
                if (this[curY][curX].isDigit()) {
                    firstIndex = this.expandLeft(curY, curX)
                    lastIndex = this.expandRight(curY, curX)
                    gears.add(this[curY].readPartNumber(firstIndex, lastIndex))
                }
            }
        }
        return if (gears.size == 2) gears[0] * gears[1] else 0
    }

    private fun List<CharArray>.expandLeft(y: Int, x: Int): Int {
        var curX = x
        while (curX > 0 && this[y][curX - 1].isDigit()) {
            curX--
        }
        return curX
    }

    private fun List<CharArray>.expandRight(y: Int, x: Int): Int {
        var curX = x
        while (curX < this[y].lastIndex && this[y][curX + 1].isDigit()) {
            curX++
        }
        return curX
    }

    private fun List<CharArray>.getPartNumbers(): List<Int> {
        val partNumbers = mutableListOf<Int>()
        this.forEachIndexed { y, line ->
            var firstIndex = DEFAULT_INDEX
            var lastIndex = DEFAULT_INDEX
            line.forEachIndexed { x, c ->
                when {
                    c.isDigit() && firstIndex == DEFAULT_INDEX -> {
                        firstIndex = x
                        lastIndex = x
                    }
                    c.isDigit() && firstIndex != DEFAULT_INDEX -> {
                        lastIndex = x
                        if (lastIndex == line.lastIndex) {
                            if (this.isPartNumber(y, firstIndex, lastIndex)) partNumbers.add(line.readPartNumber(firstIndex, lastIndex))
                            firstIndex = DEFAULT_INDEX
                            lastIndex = DEFAULT_INDEX
                        }
                    }
                    !c.isDigit() && lastIndex != DEFAULT_INDEX ||
                    x == line.lastIndex && lastIndex != DEFAULT_INDEX -> {
                        if (this.isPartNumber(y, firstIndex, lastIndex)) partNumbers.add(line.readPartNumber(firstIndex, lastIndex))
                        firstIndex = DEFAULT_INDEX
                        lastIndex = DEFAULT_INDEX
                    }
                }
            }
        }
        return partNumbers
    }

    private fun CharArray.readPartNumber(firstIndex: Int, lastIndex: Int): Int {
        return String(this).substring(firstIndex, lastIndex + 1).toInt()
    }

    private fun List<CharArray>.isPartNumber(y: Int, x1: Int, x2: Int): Boolean {
        val neighbors = mutableListOf<Pair<Int, Int>>()

        for (curY in y-1..y+1) {
            if (curY < 0 || curY > this.lastIndex) continue
            for (x in x1-1..x2+1) {
                if (x < 0 || x > this[curY].lastIndex) continue
                if (curY == y && x >= x1 && x <= x2) continue
                neighbors.add(Pair(curY, x))
            }
        }

        return neighbors.any { this[it.first][it.second] != DOT && !this[it.first][it.second].isDigit() }
    }

}