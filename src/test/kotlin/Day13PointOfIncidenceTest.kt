import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day13PointOfIncidenceTest {

    private val day13PointOfIncidence = Day13PointOfIncidence()
    private val inputFileName = "Day13Input.txt"
    private val resourcesPath = "src/test/resources"

    private fun readResourceFile(): List<String> {
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun summarizeNotesExample() {
        val input = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.

            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
        """.trimIndent().split("\n")
        val result = day13PointOfIncidence.summarizeNotes(input)
        assertEquals(405, result)
    }

    @Test
    fun summarizeNotes() {
        val input = readResourceFile()
        val result = day13PointOfIncidence.summarizeNotes(input)
        //assertEquals(29478, result) // too low
        //assertEquals(34532, result) // after changing < in reflections lambda to <= too low
        //assertEquals(39888, result) // too high, after always selecting the last index of horizontal
        //assertEquals(39774, result) // that's not the right answer, after changing to finding perfect reflection
        assertEquals(35210, result)
    }
}