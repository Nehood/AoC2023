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

    @Test
    fun countSpacesInsideLoopExample() {
        val input = """
            ...........
            .S-------7.
            .|F-----7|.
            .||.....||.
            .||.....||.
            .|L-7.F-J|.
            .|..|.|..|.
            .L--J.L--J.
            ...........
        """.trimIndent().split("\n")
        val result = day10PipeMaze.countSpacesInsideLoop(input)
        assertEquals(4, result)
    }

    @Test
    fun countSpacesInsideLoopExample2() {
        val input = """
            ..........
            .S------7.
            .|F----7|.
            .||....||.
            .||....||.
            .|L-7F-J|.
            .|..||..|.
            .L--JL--J.
            ..........
        """.trimIndent().split("\n")
        val result = day10PipeMaze.countSpacesInsideLoop(input)
        assertEquals(4, result)
    }

    @Test
    fun countSpacesInsideLoopExample3() {
        val input = """
            .F----7F7F7F7F-7....
            .|F--7||||||||FJ....
            .||.FJ||||||||L7....
            FJL7L7LJLJ||LJ.L-7..
            L--J.L7...LJS7F-7L7.
            ....F-J..F7FJ|L7L7L7
            ....L7.F7||L7|.L7L7|
            .....|FJLJ|FJ|F7|.LJ
            ....FJL-7.||.||||...
            ....L---J.LJ.LJLJ...
        """.trimIndent().split("\n")
        val result = day10PipeMaze.countSpacesInsideLoop(input)
        assertEquals(8, result)
    }

    @Test
    fun countSpacesInsideLoopExample4() {
        val input = """
                OF----7F7F7F7F-7OOOO
                O|F--7||||||||FJOOOO
                O||OFJ||||||||L7OOOO
                FJL7L7LJLJ||LJIL-7OO
                L--JOL7IIILJS7F-7L7O
                OOOOF-JIIF7FJ|L7L7L7
                OOOOL7IF7||L7|IL7L7|
                OOOOO|FJLJ|FJ|F7|OLJ
                OOOOFJL-7O||O||||OOO
                OOOOL---JOLJOLJLJOOO
        """.trimIndent().split("\n")
        val result = day10PipeMaze.countSpacesInsideLoop(input)
        assertEquals(4, result)
    }

    @Test
    fun countSpacesInsideLoop() {
        val input = readResourceFile()
        val result = day10PipeMaze.countSpacesInsideLoop(input)
        assertEquals(41, result)  //wrong
    }
}