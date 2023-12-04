package data

import kotlin.math.pow

data class Day4ScratchCard(val id: Int, val winningNumbers: Set<Int>, val scratchedNumbers: Set<Int>) {
    companion object {
        private val COLON = ':'
        private val BAR = '|'
        private val SPACE = ' '

        fun fromScratchCard(scratchCard: String): Day4ScratchCard {
            val parts = scratchCard.split(COLON)
            val numbers = parts[1].split(BAR)
            val winningNumbers = numbers[0].extractNumbers()
            val scratchedNumbers = numbers[1].extractNumbers()

            val id = parts[0].split(SPACE).filter { el -> el.isNotBlank() }[1].toInt()

            return Day4ScratchCard(id, winningNumbers, scratchedNumbers)
        }

        private fun String.extractNumbers(): Set<Int> {
            return this.split(SPACE).filter { el -> el.isNotBlank() }.map {number -> number.toInt() }.toSet()
        }

    }

    fun getScratchedWinningNumbers(): Set<Int> {
        return winningNumbers.filter { number -> number in scratchedNumbers }.toSet()
    }

    fun getPoints(): Int {
        val scratchedWinningNumbers = getScratchedWinningNumbers()
        return if (scratchedWinningNumbers.size > 1) 2.0.pow(scratchedWinningNumbers.size - 1).toInt() else scratchedWinningNumbers.size
    }
}