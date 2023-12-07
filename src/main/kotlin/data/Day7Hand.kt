package data

data class Day7Hand(val cards: List<Card>, val handType: HandType, val bid: Long): Comparable<Day7Hand> {

    companion object {

        private val SPACE = ' '

        fun fromHand(handString: String): Day7Hand {
            val parts = handString.split(SPACE)
            val cards = parts[0].map { Card.create(it) }
            val bid = parts[1].toLong()
            val handType = establishHandType(cards)

            return Day7Hand(cards, handType, bid)
        }

        private fun establishHandType(cards: List<Card>): HandType {
            val groupings = cards.groupingBy { it }.eachCount()
            return when {
                groupings.size == 1 -> HandType.FIVE_OF_A_KIND
                groupings.size == 2 && groupings.filter { it.value >= 4 }.isNotEmpty() -> HandType.FOUR_OF_A_KIND
                groupings.size == 2 && groupings.values.contains(2) && groupings.values.contains(3) -> HandType.FULL_HOUSE
                groupings.size >= 2 && groupings.values.contains(3) -> HandType.THREE_OF_A_KIND
                groupings.size >= 2 && groupings.values.filter { it == 2 }.size == 2 -> HandType.TWO_PAIR
                groupings.size >= 2 && groupings.values.contains(2) -> HandType.ONE_PAIR
                else -> HandType.HIGH_CARD
            }
        }
    }

    override fun compareTo(other: Day7Hand): Int {
        val compareHandTypes = this.handType.compareTo(other.handType)
        return if (compareHandTypes == 0) {
            var comparedCards = 1
            for (index in 0..this.cards.lastIndex) {
                if (this.cards[index].compareTo(other.cards[index]) == 0) continue
                comparedCards = this.cards[index].compareTo(other.cards[index])
                break
            }
            return comparedCards
        } else compareHandTypes
    }

}

enum class Card(val symbol: Char) {
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K'),
    AS('A');

    companion object {
        fun create(symbol: Char): Card {
            return entries.first { it.symbol == symbol }
        }

    }
}

enum class HandType {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND;

    fun compare(other: HandType): Int {
        return if (this.ordinal < other.ordinal) 1
            else if (this.ordinal == other.ordinal) 0
            else -1
    }
}