package data

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Day5Almanac(
    val seeds: List<Long>,
    val seedRanges: List<LongRange>,
    val maps: Map<MapType, List<Pair<LongRange, Long>>>
) {
    companion object {
        private val SEEDS = "seeds: "
        private val SEED_TO_SOIL = "seed-to-soil map:"
        private val SOIL_TO_FERTILIZER = "soil-to-fertilizer map:"
        private val FERTILIZER_TO_WATER = "fertilizer-to-water map:"
        private val WATER_TO_LIGHT = "water-to-light map:"
        private val LIGHT_TO_TEMPERATURE = "light-to-temperature map:"
        private val TEMPERATURE_TO_HUMIDITY = "temperature-to-humidity map:"
        private val HUMIDITY_TO_LOCATION = "humidity-to-location map:"
        private val SPACE = ' '

        fun fromAlmanac(almanac: List<String>): Day5Almanac {
            val seeds = almanac[0].substringAfter(SEEDS).split(SPACE).map { it.toLong() }
            val seedToSoil = almanac.subList(
                almanac.indexOf(SEED_TO_SOIL) + 1,
                almanac.withIndex()
                    .indexOfFirst { (index, value) -> index > almanac.indexOf(SEED_TO_SOIL) && value.isEmpty() })
            val soilToFertilizer = almanac.subList(
                almanac.indexOf(SOIL_TO_FERTILIZER) + 1,
                almanac.withIndex()
                    .indexOfFirst { (index, value) -> index > almanac.indexOf(SOIL_TO_FERTILIZER) && value.isEmpty() })
            val fertilizerToWater = almanac.subList(
                almanac.indexOf(FERTILIZER_TO_WATER) + 1,
                almanac.withIndex()
                    .indexOfFirst { (index, value) -> index > almanac.indexOf(FERTILIZER_TO_WATER) && value.isEmpty() })
            val waterToLight = almanac.subList(
                almanac.indexOf(WATER_TO_LIGHT) + 1,
                almanac.withIndex()
                    .indexOfFirst { (index, value) -> index > almanac.indexOf(WATER_TO_LIGHT) && value.isEmpty() })
            val lightToTemperature = almanac.subList(
                almanac.indexOf(LIGHT_TO_TEMPERATURE) + 1,
                almanac.withIndex()
                    .indexOfFirst { (index, value) -> index > almanac.indexOf(LIGHT_TO_TEMPERATURE) && value.isEmpty() })
            val temperatureToHumidity = almanac.subList(
                almanac.indexOf(TEMPERATURE_TO_HUMIDITY) + 1,
                almanac.withIndex()
                    .indexOfFirst { (index, value) -> index > almanac.indexOf(TEMPERATURE_TO_HUMIDITY) && value.isEmpty() })
            val humidityToLocation = almanac.subList(almanac.indexOf(HUMIDITY_TO_LOCATION) + 1, almanac.lastIndex)

            val seedToSoilRanges = seedToSoil.toRanges()
            val soilToFertilizeRanges = soilToFertilizer.toRanges()
            val fertilizerToWaterRanges = fertilizerToWater.toRanges()
            val waterToLightRanges = waterToLight.toRanges()
            val lightToTemperatureRanges = lightToTemperature.toRanges()
            val temperatureToHumidityRanges = temperatureToHumidity.toRanges()
            val humidityToLocationRanges = humidityToLocation.toRanges()

            val mapOfMaps = mapOf<MapType, List<Pair<LongRange, Long>>>(
                Pair(MapType.SEED_TO_SOIL, seedToSoilRanges),
                Pair(MapType.SOIL_TO_FERTILIZER, soilToFertilizeRanges),
                Pair(MapType.FERTILIZER_TO_WATER, fertilizerToWaterRanges),
                Pair(MapType.WATER_TO_LIGHT, waterToLightRanges),
                Pair(MapType.LIGHT_TO_TEMPERATURE, lightToTemperatureRanges),
                Pair(MapType.TEMPERATURE_TO_HUMIDITY, temperatureToHumidityRanges),
                Pair(MapType.HUMIDITY_TO_LOCATION, humidityToLocationRanges)
            )

            val seedRanges = mutableListOf<LongRange>()
            for (i in 0..<seeds.lastIndex step 2) {
                seedRanges.add(LongRange(seeds[i], seeds[i] + seeds[i + 1]))
            }
            return Day5Almanac(seeds, seedRanges.toList(), mapOfMaps)
        }

        private fun List<String>.toRanges(): List<Pair<LongRange, Long>> {
            return this.map {
                val parts = it.split(SPACE)
                val destinationRangeStart = parts[0].toLong()
                val sourceRangeStart = parts[1].toLong()
                val rangeLength = parts[2].toLong()

                val sourceRangeEnd = sourceRangeStart + rangeLength
                Pair(LongRange(sourceRangeStart, sourceRangeEnd), sourceRangeStart - destinationRangeStart)
            }
        }
    }

    private fun List<Pair<LongRange, Long>>.matchToDestination(source: Long): Long {
        val matchedRange = this.firstOrNull { it.first.first <= source && it.first.last >= source } ?: return source
        return abs(source - matchedRange.second)
    }

    fun getLocationNumberForSeed(seedNumber: Long): Long {
        var currentMapType = MapType.SEED_TO_SOIL
        var currentNumber = seedNumber
        while (currentMapType != MapType.FINAL) {
            currentNumber = this.maps.get(currentMapType)!!.matchToDestination(currentNumber)
            currentMapType = currentMapType.getNextMapType()
        }
        return currentNumber
    }

    fun matchToRanges(): Long {
        var lowest = Long.MAX_VALUE
        for (seedRange in this.seedRanges) {
            var currentMapType = MapType.SEED_TO_SOIL
            var currentRanges = mutableListOf(LongRange(seedRange.first, seedRange.last))
            while (currentMapType != MapType.FINAL) {
                val matchedRanges = mutableListOf<LongRange>()
                val currentMap = this.maps[currentMapType]!!
                val processedMatches = mutableListOf<LongRange>()
                for (currentRange in currentRanges) {
                    var currentModifiableRange = LongRange(currentRange.first, currentRange.last)
                    for (mapRange in currentMap) {
                        if (mapRange.first.first <= currentModifiableRange.first && mapRange.first.last >= currentModifiableRange.last) {
                            // full match
                            matchedRanges.add(LongRange(currentModifiableRange.first, currentModifiableRange.last))
                            currentModifiableRange = LongRange.EMPTY
                            break
                        } else if (currentModifiableRange.first >= mapRange.first.first && currentModifiableRange.first <= mapRange.first.last || currentModifiableRange.last <= mapRange.first.last && currentModifiableRange.last >= mapRange.first.first) {
                            // partial match
                            val newRangeStart = max(currentModifiableRange.first, mapRange.first.first)
                            val newRangeEnd = min(currentModifiableRange.last, mapRange.first.last)

                            val modifiedRangeStart =
                                if (newRangeStart == currentModifiableRange.first) newRangeEnd else currentModifiableRange.first
                            val modifiedRangeEnd =
                                if (newRangeEnd == currentModifiableRange.last) newRangeStart else currentModifiableRange.last
                            currentModifiableRange = LongRange(modifiedRangeStart, modifiedRangeEnd)
                            matchedRanges.add(LongRange(newRangeStart, newRangeEnd))
                        }
                    }
                    if (currentModifiableRange != LongRange.EMPTY) {
                        // none match, preserve
                        processedMatches.add(currentModifiableRange)
                    }
                }
                currentMapType = currentMapType.getNextMapType()
                for (matchedRange in matchedRanges) {
                    for (mapRange in currentMap) {
                        if (matchedRange.first >= mapRange.first.first && matchedRange.last <= mapRange.first.last) {
                            processedMatches.add(
                                LongRange(
                                    abs(matchedRange.first - mapRange.second), abs(matchedRange.last - mapRange.second)
                                )
                            )
                        }
                    }
                }
                currentRanges = processedMatches.toMutableList()
            }
            val currentLowest = currentRanges.minOfOrNull { it.first }!!
            if (currentLowest < lowest) lowest = currentLowest
        }
        return lowest
    }

}

enum class MapType {
    SEED_TO_SOIL, SOIL_TO_FERTILIZER, FERTILIZER_TO_WATER, WATER_TO_LIGHT, LIGHT_TO_TEMPERATURE, TEMPERATURE_TO_HUMIDITY, HUMIDITY_TO_LOCATION, FINAL;

    fun getNextMapType(): MapType {
        return when (this) {
            SEED_TO_SOIL -> SOIL_TO_FERTILIZER
            SOIL_TO_FERTILIZER -> FERTILIZER_TO_WATER
            FERTILIZER_TO_WATER -> WATER_TO_LIGHT
            WATER_TO_LIGHT -> LIGHT_TO_TEMPERATURE
            LIGHT_TO_TEMPERATURE -> TEMPERATURE_TO_HUMIDITY
            TEMPERATURE_TO_HUMIDITY -> HUMIDITY_TO_LOCATION
            HUMIDITY_TO_LOCATION -> FINAL
            FINAL -> FINAL
        }
    }
}