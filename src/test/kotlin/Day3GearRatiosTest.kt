import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day3GearRatiosTest {

    private val day3GearRatios = Day3GearRatios()
    private val inputFileName = "Day3Input.txt"
    private val resourcesPath = "src/test/resources"

    private fun readResourceFile(): List<CharArray> {
        return File("$resourcesPath/$inputFileName").readLines().transformInput()
    }

    private fun List<String>.transformInput(): List<CharArray> {
        return this.map { it.toCharArray()}
    }

    @Test
    fun sumSchematicPartNumbersExample() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
        """.trimIndent().split("\n").transformInput()
        val result = day3GearRatios.sumSchematicPartNumbers(input)
        assertEquals(4361, result)
    }

    @Test
    fun sumSchematicPartNumbers() {
        val input = readResourceFile()
        val result = day3GearRatios.sumSchematicPartNumbers(input)
        assertEquals(532428, result)
    }

    @Test
    fun sumGearRatiosExample() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
        """.trimIndent().split("\n").transformInput()
        val result = day3GearRatios.sumGearRatios(input)
        assertEquals(467835, result)
    }

    @Test
    fun sumGearRatios() {
        val input = readResourceFile()
        val result = day3GearRatios.sumGearRatios(input)
        assertEquals(84051670, result)
    }
}