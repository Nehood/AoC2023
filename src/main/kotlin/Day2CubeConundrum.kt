import data.Day2Game
import data.Day2GameSet

class Day2CubeConundrum {

    private val RED_CUBES_THRESHOLD = 12
    private val GREEN_CUBES_THRESHOLD = 13
    private val BLUE_CUBES_THRESHOLD = 14

    fun sumPossibleGameIds(gameRecords: List<String>): Int {
        val games = gameRecords.map { Day2Game.fromGameRecord(it) }
        return games.filter { it.isGamePossible() }.sumOf { it.id }
    }

    fun sumPowerOfGames(gameRecords: List<String>): Int {
        val games = gameRecords.map { Day2Game.fromGameRecord(it) }
        return games.map { it.getPowerOfGame() }.sum()
    }

    private fun Day2Game.getPowerOfGame(): Int {
        val minimumSetOfCubes = this.getMinimumSetOfCubes()
        return minimumSetOfCubes.blueCubesCount * minimumSetOfCubes.redCubesCount * minimumSetOfCubes.greenCubesCount
    }

    private fun Day2Game.getMinimumSetOfCubes(): Day2GameSet {
        val redCubes = this.gameSets.maxOfOrNull { it.redCubesCount } ?: 0
        val greenCubes = this.gameSets.maxOfOrNull { it.greenCubesCount } ?: 0
        val blueCubes = this.gameSets.maxOfOrNull { it.blueCubesCount } ?: 0
        return Day2GameSet(redCubes, greenCubes, blueCubes)
    }

    private fun Day2Game.isGamePossible(): Boolean {
        return !this.isGameImpossible()
    }

    private fun Day2Game.isGameImpossible(): Boolean {
        return this.gameSets.any { it.blueCubesCount > BLUE_CUBES_THRESHOLD ||
                it.redCubesCount > RED_CUBES_THRESHOLD ||
                it.greenCubesCount > GREEN_CUBES_THRESHOLD }
    }


}