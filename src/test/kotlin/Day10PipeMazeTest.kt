import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day10PipeMazeTest {

    private val day10PipeMaze = Day10PipeMaze()
    private val inputFileName = "Day10Input.txt"
    private val resourcesPath = "src/test/resources"

    private fun readResourceFile(): List<String> {
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun getDistanceToFurthestPositionExample() {
        val input = """
            .....
            .S-7.
            .|.|.
            .L-J.
            .....
        """.trimIndent().split("\n")
        val result = day10PipeMaze.getDistanceToFurthestPosition(input)
        assertEquals(4, result)
    }

    @Test
    fun getDistanceToFurthestPositionExample2() {
        val input = """
            ..F7.
            .FJ|.
            SJ.L7
            |F--J
            LJ...
        """.trimIndent().split("\n")
        val result = day10PipeMaze.getDistanceToFurthestPosition(input)
        assertEquals(8, result)
    }

    @Test
    fun getDistanceToFurthestPosition() {
        val input = readResourceFile()
        val result = day10PipeMaze.getDistanceToFurthestPosition(input)
        assertEquals(6909, result)
    }
}