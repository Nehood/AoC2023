import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day4ScratchcardsTest {

    private val day4Scratchcards = Day4Scratchcards()
    private val inputFileName = "Day4Input.txt"
    private val resourcesPath = "src/test/resources"

    private fun readResourceFile(): List<String> {
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun sumPointsExample() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().split("\n")
        val result = day4Scratchcards.sumPoints(input)
        assertEquals(13, result)
    }

    @Test
    fun sumPoints() {
        val input = readResourceFile()
        val result = day4Scratchcards.sumPoints(input)
        assertEquals(24542, result)
    }

    @Test
    fun sumScratchCardsExample() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().split("\n")
        val result = day4Scratchcards.sumScratchCards(input)
        assertEquals(30, result)
    }

    @Test
    fun sumScratchCards() {
        val input = readResourceFile()
        val result = day4Scratchcards.sumScratchCards(input)
        assertEquals(8736438, result)
    }

}