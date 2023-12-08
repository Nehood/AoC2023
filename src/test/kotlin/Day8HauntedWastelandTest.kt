import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day8HauntedWastelandTest {

    private val day8HauntedWasteland = Day8HauntedWasteland()
    private val inputFileName = "Day8Input.txt"
    private val resourcesPath = "src/test/resources"

    private fun readResourceFile(): List<String> {
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun countStepsToReachDestinationExample() {
        val input = """
            RL

            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent().split("\n")
        val result = day8HauntedWasteland.countStepsToReachDestination(input)
        assertEquals(2, result)
    }

    @Test
    fun countStepsToReachDestinationAnotherExample() {
        val input = """
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent().split("\n")
        val result = day8HauntedWasteland.countStepsToReachDestination(input)
        assertEquals(6, result)
    }

    @Test
    fun countStepsToReachDestination() {
        val input = readResourceFile()
        val result = day8HauntedWasteland.countStepsToReachDestination(input)
        assertEquals(19241, result)
    }
}