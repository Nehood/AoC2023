import data.Day4ScratchCard
import kotlin.math.pow

class Day4Scratchcards {

    private val COLON = ':'
    private val BAR = '|'
    private val SPACE = ' '

    fun sumPoints(scratchCardsRecord: List<String>): Int {
        val scratchCards = scratchCardsRecord.map { Day4ScratchCard.fromScratchCard(it) }
        return scratchCards.sumOf {it.getPoints()
        }
    }

    fun sumScratchCards(scratchCardsRecord: List<String>): Int {
        val scratchCards = scratchCardsRecord.map { Day4ScratchCard.fromScratchCard(it) }
        val scratchCardCounts = scratchCards.associate { Pair(it.id, 1) }.toMutableMap()
        scratchCards.forEach {
            val matchingNumbersCount = it.getScratchedWinningNumbers().size
            val rangeEnd = if (matchingNumbersCount + it.id - 1 > scratchCards.lastIndex) scratchCards.lastIndex else matchingNumbersCount + it.id
            for (cardId in it.id + 1..rangeEnd) {
                val newCount = scratchCardCounts[it.id]!! + scratchCardCounts[cardId]!!
                scratchCardCounts[cardId] = newCount
            }
        }
        return scratchCardCounts.values.sum()
    }
}