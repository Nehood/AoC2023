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

    @Test
    fun countStepsToReachDestinationAsGhostExample() {
        val input = """
            LR

            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
        """.trimIndent().split("\n")
        val result = day8HauntedWasteland.countStepsToReachDestinationAsGhost(input)
        assertEquals(6, result)
    }

    @Test
    fun countStepsToReachDestinationAsGhost() {
        val input = readResourceFile()
        val result = day8HauntedWasteland.countStepsToReachDestinationAsGhost(input)
        assertEquals(9606140307013, result)
    }
}