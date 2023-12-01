import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day1TrebuchetTest {

    private val day1Trebuchet = Day1Trebuchet()
    private val inputFileName = "Day1Input.txt"
    private val resourcesPath = "src/test/resources"

    private fun readResourceFile(): List<String> {
        return File("$resourcesPath/$inputFileName").readLines()
    }
    @Test
    fun sumCalibrationValuesExample() {
        val input = """1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet""".trimIndent().split("\n")
        val summedCalibrationValues = day1Trebuchet.sumCalibrationValues(input)
        assertEquals(142, summedCalibrationValues)
    }

    @Test
    fun sumCalibrationValues() {
        val input = readResourceFile()
        val summedCalibrationValues = day1Trebuchet.sumCalibrationValues(input)
        assertEquals(55621, summedCalibrationValues)
    }

    @Test
    fun sumCalibrationValuesWithWordsExample() {
        val input = """two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen""".trimIndent().split("\n")
        val summedCalibrationValues = day1Trebuchet.sumCalibrationValues(input, true)
        assertEquals(281, summedCalibrationValues)
    }

    @Test
    fun sumCalibrationValuesWithWords() {
        val input = readResourceFile()
        val summedCalibrationValues = day1Trebuchet.sumCalibrationValues(input, true)
        assertEquals(53592, summedCalibrationValues)
    }
}