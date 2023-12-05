import org.junit.jupiter.api.Test

import java.io.File
import kotlin.test.assertEquals

class Day5IfYouGiveASeedAFertilizerTest {

    private val day5IfYouGiveASeedAFertilizer = Day5IfYouGiveASeedAFertilizer()
    private val inputFileName = "Day5Input.txt"
    private val resourcesPath = "src/test/resources"

    private fun readResourceFile(): List<String> {
        return File("$resourcesPath/$inputFileName").readLines()
    }

    @Test
    fun findLowestLocationNumberExample() {
        val input = """
            seeds: 79 14 55 13
            
            seed-to-soil map:
            50 98 2
            52 50 48
            
            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15
            
            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4
            
            water-to-light map:
            88 18 7
            18 25 70
            
            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13
            
            temperature-to-humidity map:
            0 69 1
            1 0 69
            
            humidity-to-location map:
            60 56 37
            56 93 4
        """.trimIndent().split("\n")
        val result = day5IfYouGiveASeedAFertilizer.findLowestLocationNumber(input)
        assertEquals(35L, result)
    }
    @Test
    fun findLowestLocationNumber() {
        val input = readResourceFile()
        val result = day5IfYouGiveASeedAFertilizer.findLowestLocationNumber(input)
        assertEquals(382895070L, result)
    }

    @Test
    fun findLowestLocationNumberFromRangesExample() {
        val input = """
            seeds: 79 14 55 13
            
            seed-to-soil map:
            50 98 2
            52 50 48
            
            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15
            
            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4
            
            water-to-light map:
            88 18 7
            18 25 70
            
            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13
            
            temperature-to-humidity map:
            0 69 1
            1 0 69
            
            humidity-to-location map:
            60 56 37
            56 93 4
        """.trimIndent().split("\n")
        val result = day5IfYouGiveASeedAFertilizer.findLowestLocationNumberFromRanges(input)
        assertEquals(46L, result)
    }

    @Test
    fun findLowestLocationNumberFromRanges() {
        val input = readResourceFile()
        val result = day5IfYouGiveASeedAFertilizer.findLowestLocationNumberFromRanges(input)
        assertEquals(17729182L, result)
    }
}