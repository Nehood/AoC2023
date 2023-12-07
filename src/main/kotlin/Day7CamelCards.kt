import data.Day7Hand

class Day7CamelCards {
    fun calculateTotalWinnings(handStrings: List<String>): Long {
        return handStrings.map { Day7Hand.fromHand(it) }.sorted().calculateWinnings()
    }

    private fun List<Day7Hand>.calculateWinnings(): Long {
        return this.mapIndexed { index, it -> (index + 1) * it.bid }.sum()
    }
}