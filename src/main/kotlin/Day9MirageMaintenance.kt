class Day9MirageMaintenance {

    private val SPACE = " "

    fun sumReportExtrapolations(report: List<String>): Long {
        val histories = report.map { it.split(SPACE).map { number -> number.toLong() } }
        val sequencesOfDifferences = histories.map { it.generateSequenceOfDifferences() }
        return sequencesOfDifferences.sumOf { it.sumExtrapolations() }
    }

    fun sumReportExtrapolationsBackwards(report: List<String>): Long {
        val histories = report.map { it.split(SPACE).map { number -> number.toLong() } }
        val sequencesOfDifferences = histories.map { it.generateSequenceOfDifferences() }
        return sequencesOfDifferences.sumOf { it.sumExtrapolationsBackwards() }
    }

    private fun List<Long>.generateSequenceOfDifferences(): List<List<Long>> {
        val extrapolations = mutableListOf<List<Long>>()
        extrapolations.add(this.toList())
        var currentExtrapolation = this.toList()
        while (currentExtrapolation.any { it != 0L }) {
            val newExtrapolation = currentExtrapolation.zipWithNext().map { it.second - it.first }
            extrapolations.add(newExtrapolation)
            currentExtrapolation = newExtrapolation.toList()
        }
        return extrapolations.toList()
    }

    private fun List<List<Long>>.sumExtrapolationsBackwards(): Long {
        var belowValue = 0L
        return this.reversed().drop(1).map {
            belowValue = it.extrapolateBackwards(belowValue)
            belowValue
        }.last()
    }

    private fun List<Long>.extrapolateBackwards(belowValue: Long): Long {
        return this.first() - belowValue
    }

    private fun List<List<Long>>.sumExtrapolations(): Long {
        var belowValue = 0L
        return this.reversed().drop(1).map {
            belowValue = it.extrapolate(belowValue)
            belowValue
        }.last()
    }

    private fun List<Long>.extrapolate(belowValue: Long): Long {
        return this.last() + belowValue
    }
}