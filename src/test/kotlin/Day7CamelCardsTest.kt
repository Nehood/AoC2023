import org.junit.jupiter.api.Test

import java.io.File
import kotlin.test.assertEquals

class Day7CamelCardsTest {

    private val day7CamelCards = Day7CamelCards()
    private val inputFileName = "Day7Input.txt"
    private val resourcesPath = "src/test/resources"

    private fun readResourceFile(): List<String> {
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun calculateTotalWinningsExample() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent().split("\n")
        val result = day7CamelCards.calculateTotalWinnings(input)
        assertEquals(6440, result)
    }

    @Test
    fun calculateTotalWinnings() {
        val input = readResourceFile()
        val result = day7CamelCards.calculateTotalWinnings(input)
        assertEquals(248217452, result)
    }
}