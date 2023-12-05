package data

data class Day5Almanac(val seeds: List<Long>, val maps: Map<MapType, List<Pair<LongRange, LongRange>>>) {
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
            val seedToSoil = almanac.subList(almanac.indexOf(SEED_TO_SOIL) + 1, almanac.withIndex().indexOfFirst { (index, value) -> index > almanac.indexOf(SEED_TO_SOIL) && value.isEmpty() })
            val soilToFertilizer = almanac.subList(almanac.indexOf(SOIL_TO_FERTILIZER) + 1, almanac.withIndex().indexOfFirst { (index, value) -> index > almanac.indexOf(SOIL_TO_FERTILIZER) && value.isEmpty() })
            val fertilizerToWater = almanac.subList(almanac.indexOf(FERTILIZER_TO_WATER) + 1, almanac.withIndex().indexOfFirst { (index, value) -> index > almanac.indexOf(FERTILIZER_TO_WATER) && value.isEmpty() })
            val waterToLight = almanac.subList(almanac.indexOf(WATER_TO_LIGHT) + 1, almanac.withIndex().indexOfFirst { (index, value) -> index > almanac.indexOf(WATER_TO_LIGHT) && value.isEmpty() })
            val lightToTemperature = almanac.subList(almanac.indexOf(LIGHT_TO_TEMPERATURE) + 1, almanac.withIndex().indexOfFirst { (index, value) -> index > almanac.indexOf(LIGHT_TO_TEMPERATURE) && value.isEmpty() })
            val temperatureToHumidity = almanac.subList(almanac.indexOf(TEMPERATURE_TO_HUMIDITY) + 1, almanac.withIndex().indexOfFirst { (index, value) -> index > almanac.indexOf(TEMPERATURE_TO_HUMIDITY) && value.isEmpty() })
            val humidityToLocation = almanac.subList(almanac.indexOf(HUMIDITY_TO_LOCATION) + 1, almanac.lastIndex)

            val seedToSoilRanges = seedToSoil.toRanges()
            val soilToFertilizeRanges = soilToFertilizer.toRanges()
            val fertilizerToWaterRanges = fertilizerToWater.toRanges()
            val waterToLightRanges = waterToLight.toRanges()
            val lightToTemperatureRanges = lightToTemperature.toRanges()
            val temperatureToHumidityRanges = temperatureToHumidity.toRanges()
            val humidityToLocationRanges = humidityToLocation.toRanges()

            val mapOfMaps =  mapOf<MapType, List<Pair<LongRange, LongRange>>>(
                Pair(MapType.SEED_TO_SOIL, seedToSoilRanges), Pair(MapType.SOIL_TO_FERTILIZER, soilToFertilizeRanges),
                Pair(MapType.FERTILIZER_TO_WATER, fertilizerToWaterRanges), Pair(MapType.WATER_TO_LIGHT, waterToLightRanges),
                Pair(MapType.LIGHT_TO_TEMPERATURE, lightToTemperatureRanges), Pair(MapType.TEMPERATURE_TO_HUMIDITY, temperatureToHumidityRanges),
                Pair(MapType.HUMIDITY_TO_LOCATION, humidityToLocationRanges))

            return Day5Almanac(seeds, mapOfMaps)
        }

        private fun List<String>.toRanges(): List<Pair<LongRange, LongRange>> {
            return this.map {
                val parts = it.split(SPACE)
                val destinationRangeStart = parts[0].toLong()
                val sourceRangeStart = parts[1].toLong()
                val rangeLength = parts[2].toLong()

                val destinationRangeEnd = destinationRangeStart + rangeLength
                val sourceRangeEnd = sourceRangeStart + rangeLength
                Pair(LongRange(sourceRangeStart, sourceRangeEnd), LongRange(destinationRangeStart, destinationRangeEnd))
            }
        }
    }

    private fun List<Pair<LongRange, LongRange>>.matchToDestination(source: Long): Long {
        val matchedRange = this.firstOrNull { it.first.first <= source && it.first.last >= source } ?: return source
        val diff = source - matchedRange.first.first
        return matchedRange.second.first + diff
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

}

enum class MapType {
    SEED_TO_SOIL,
    SOIL_TO_FERTILIZER,
    FERTILIZER_TO_WATER,
    WATER_TO_LIGHT,
    LIGHT_TO_TEMPERATURE,
    TEMPERATURE_TO_HUMIDITY,
    HUMIDITY_TO_LOCATION,
    FINAL;

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