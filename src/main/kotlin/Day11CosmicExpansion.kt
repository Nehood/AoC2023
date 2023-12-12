import data.Day11Point

class Day11CosmicExpansion {

    private val GALAXY = '#'
    private val SPACE = '.'

    fun sumShortestPathsBetweenGalaxies(image: List<String>): Long {
        val expandedImage = image.expand()
        val galaxies = expandedImage.findGalaxies()
        var pathLengthsSum = 0L
        for (index1 in 0..<galaxies.lastIndex) {
            for (index2 in index1 + 1..galaxies.lastIndex) {
                val distance = galaxies[index1].calculateManhattanDistance(galaxies[index2])
                pathLengthsSum += distance
            }
        }
        return pathLengthsSum
    }

    fun sumShortestPathsBetweenAncientGalaxies(image: List<String>): Long {
        val expandedRows = image.getExpandedRows()
        val expandedColumns = image.getExpandedColumns()
        val galaxies = image.findGalaxies()
        var pathLengthsSum = 0L
        for (index1 in 0..<galaxies.lastIndex) {
            for (index2 in index1 + 1..galaxies.lastIndex) {
                val distance = galaxies[index1].calculateAncientManhattanDistance(galaxies[index2], expandedRows, expandedColumns)
                pathLengthsSum += distance
            }
        }
        return pathLengthsSum
    }

    private fun List<String>.expand(): List<String> {
        val expandedImage = this.toMutableList()
        var insertions = 0
        for ((y, line) in this.withIndex()) {
            if (line.all { it == SPACE }) {
                expandedImage.add(y + insertions, line)
                ++insertions
            }
        }
        val xesToAdd = mutableListOf<Int>()
        for (x in 0..this[0].lastIndex) {
            var allSpace = true
            for (y in 0..this.lastIndex) {
                if (this[y][x] == GALAXY) {
                    allSpace = false
                    break
                }
            }
            if (allSpace) {
                xesToAdd.add(x)
            }
        }

        insertions = 0
        for (x in xesToAdd) {
            for (y in 0..expandedImage.lastIndex) {
                    val newString = expandedImage[y].substring(0, x + insertions) + SPACE + expandedImage[y].substring(x + insertions)
                    expandedImage[y] = newString
                }
            ++insertions
        }

        return expandedImage
    }

    private fun List<String>.findGalaxies(): List<Day11Point> {
        val galaxies = mutableListOf<Day11Point>()
        for ((y, line) in this.withIndex()) {
            for ((x, symbol) in line.withIndex()) {
                if (symbol == GALAXY) galaxies.add(Day11Point(y, x))
            }
        }
        return galaxies
    }

    private fun List<String>.draw() {
        for ((y, line) in this.withIndex()) {
            for ((x, symbol) in line.withIndex()) {
                print(this[y][x] + "\t")
            }
            println()
        }
    }

    private fun List<String>.getExpandedRows(): List<Int> {
        val expandedRows = mutableListOf<Int>()
        for ((y, line) in this.withIndex()) {
            if (line.all { it == SPACE }) {
                expandedRows.add(y)
            }
        }
        return expandedRows.toList()
    }

    private fun List<String>.getExpandedColumns(): List<Int> {
        val expandedColumns = mutableListOf<Int>()
        for (x in 0..this[0].lastIndex) {
            var allSpace = true
            for (y in 0..this.lastIndex) {
                if (this[y][x] == GALAXY) {
                    allSpace = false
                    break
                }
            }
            if (allSpace) {
                expandedColumns.add(x)
            }
        }
        return expandedColumns
    }
}