import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day11CosmicExpansionTest {

    private val day11CosmicExpansion = Day11CosmicExpansion()
    private val inputFileName = "Day11Input.txt"
    private val resourcesPath = "src/test/resources"

    private fun readResourceFile(): List<String> {
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun sumShortestPathsBetweenGalaxiesExample() {
        val input = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
        """.trimIndent().split("\n")
        val result = day11CosmicExpansion.sumShortestPathsBetweenGalaxies(input)
        assertEquals(374, result)
    }

    @Test
    fun sumShortestPathsBetweenGalaxies() {
        val input = readResourceFile()
        val result = day11CosmicExpansion.sumShortestPathsBetweenGalaxies(input)
        assertEquals(10228230, result)
    }
}