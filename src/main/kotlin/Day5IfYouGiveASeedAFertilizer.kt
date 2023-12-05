import data.Day5Almanac

class Day5IfYouGiveASeedAFertilizer {

    fun findLowestLocationNumber(almanac: List<String>): Long {
        val translatedAlmanac = Day5Almanac.fromAlmanac(almanac)
        return translatedAlmanac.seeds.minOfOrNull { translatedAlmanac.getLocationNumberForSeed(it) }!!
    }
}