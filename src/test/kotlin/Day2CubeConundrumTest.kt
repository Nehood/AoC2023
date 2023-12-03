import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day2CubeConundrumTest {

    private val day2CubeConundrum = Day2CubeConundrum()
    private val inputFileName = "Day2Input.txt"
    private val resourcesPath = "src/test/resources"

    private fun readResourceFile(): List<String> {
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun sumPossibleGameIdsExample() {
        val input = """
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent().split("\n")
        val result = day2CubeConundrum.sumPossibleGameIds(input)
        assertEquals(8, result)
    }

    @Test
    fun sumPossibleGameIds() {
        val input = readResourceFile()
        val result = day2CubeConundrum.sumPossibleGameIds(input)
        assertEquals(2239, result)
    }

    @Test
    fun sumPowerOfGamesExample() {
        val input = """
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent().split("\n")
        val result = day2CubeConundrum.sumPowerOfGames(input)
        assertEquals(2286, result)
    }

    @Test
    fun sumPowerOfGames() {
        val input = readResourceFile()
        val result = day2CubeConundrum.sumPowerOfGames(input)
        assertEquals(83435, result)
    }
}