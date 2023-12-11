import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class Day9MirageMaintenanceTest {

    private val day9MirageMaintenance = Day9MirageMaintenance()
    private val inputFileName = "Day9Input.txt"
    private val resourcesPath = "src/test/resources"

    private fun readResourceFile(): List<String> {
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun sumExtrapolationsExample() {
        val input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent().split("\n")
        val result = day9MirageMaintenance.sumReportExtrapolations(input)
        assertEquals(114, result)
    }

    @Test
    fun sumExtrapolations() {
        val input = readResourceFile()
        val result = day9MirageMaintenance.sumReportExtrapolations(input)
        assertEquals(1684566095, result)
    }

    @Test
    fun sumExtrapolationsBackwardsExample() {
        val input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent().split("\n")
        val result = day9MirageMaintenance.sumReportExtrapolationsBackwards(input)
        assertEquals(2, result)
    }

    @Test
    fun sumExtrapolationsBackwards() {
        val input = readResourceFile()
        val result = day9MirageMaintenance.sumReportExtrapolationsBackwards(input)
        assertEquals(1136, result)
    }
}