class Day13PointOfIncidence {
    fun summarizeNotes(notes: List<String>): Int {
        val patterns = notes.splitIntoPatterns()
        val horizontalReflections = patterns.map { it.findHorizontalReflections() }
        val verticalReflections = patterns.map { it.transpose() }.map { it.findHorizontalReflections(true) }
        val reflections = horizontalReflections.mapIndexed { index, pair ->
            if (pair.first > verticalReflections[index].first) pair else verticalReflections[index]
        }
        val filteredVertical = verticalReflections.filter { it.first != -1 }
        val filteredHorizontal = horizontalReflections.filter { it.first != -1 }
        return filteredVertical.sumOf { it.second } + filteredHorizontal.sumOf { it.second }
    }

    private fun List<String>.splitIntoPatterns(): List<List<String>> {
        var patterns = mutableListOf<List<String>>()
        var pattern = mutableListOf<String>()
        for (line in this) {
            if (line.isNotBlank()) {
                pattern.add(line)
            } else {
                patterns.add(pattern.toList())
                pattern.clear()
            }
        }
        patterns.add(pattern.toList())
        return patterns.toList()
    }

    private fun List<String>.transpose(): List<String> {
        val verticalLines = mutableListOf<String>()
        for (x in 0..this[0].lastIndex) {
            var verticalLine = ""
            for (y in 0..this.lastIndex) {
                verticalLine += (this[y][x])
            }
            verticalLines.add(verticalLine.toString())
        }
        return verticalLines.toList()
    }

    private fun List<String>.findHorizontalReflections(transposed: Boolean = false): Pair<Int, Int> {
        var biggestReflectionIndex = -1
        var biggestReflectionSize = -1
        for (index in 0..<this.lastIndex) {
            if (this[index] == this[index + 1]) {
                var currentReflectionSize = 1
                var leftExpansionindex = index - 1
                var rightExpansionIndex = index + 2
                while (leftExpansionindex >= 0 && rightExpansionIndex <= this.lastIndex &&
                    this[leftExpansionindex] == this[rightExpansionIndex]) {
                    --leftExpansionindex
                    ++rightExpansionIndex
                    ++currentReflectionSize
                }
                if (leftExpansionindex == -1 || rightExpansionIndex > this.lastIndex || (leftExpansionindex == 0 && rightExpansionIndex == this.lastIndex)) { // this is the perfect reflection
                    biggestReflectionSize = currentReflectionSize
                    biggestReflectionIndex = index
                }

            }
        }
        return if (transposed) Pair(biggestReflectionSize, biggestReflectionIndex + 1) else Pair(biggestReflectionSize, 100 * (biggestReflectionIndex + 1))
    }
}