import org.junit.jupiter.api.Test

import java.io.File
import kotlin.test.assertEquals

class Day6WaitForItTest {

    private val day6WaitForIt = Day6WaitForIt()
    private val inputFileName = "Day6Input.txt"
    private val resourcesPath = "src/test/resources"

    private fun readResourceFile(): List<String> {
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun calculateWaysToBeatRacesExample() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent().split("\n")
        val result = day6WaitForIt.calculateWaysToBeatRaces(input)
        assertEquals(288, result)
    }

    @Test
    fun calculateWaysToBeatRaces() {
        val input = readResourceFile()
        val result = day6WaitForIt.calculateWaysToBeatRaces(input)
        assertEquals(1624896, result)
    }

    @Test
    fun calculateWaysToBeatSingleRaceExample() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent().split("\n")
        val result = day6WaitForIt.calculateWaysToBeatSingleRace(input)
        assertEquals(71503, result)
    }

    @Test
    fun calculateWaysToBeatSingleRace() {
        val input = readResourceFile()
        val result = day6WaitForIt.calculateWaysToBeatSingleRace(input)
        assertEquals(32583852, result)
    }
}