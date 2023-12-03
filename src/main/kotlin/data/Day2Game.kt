package data

data class Day2Game(val id: Int, val gameSets: List<Day2GameSet>) {
    //Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
    companion object {
        private val GAME_PARTS_SEPARATOR = ':'
        private val SPACE = ' '
        private val SET_SEPARATOR = ';'
        private val COMMA = ','
        private val RED = "red"
        private val GREEN = "green"
        private val BLUE = "blue"
        fun fromGameRecord(gameRecord: String): Day2Game {
            val gameParts = gameRecord.split(GAME_PARTS_SEPARATOR)
            val id = gameParts[0].substringAfter(SPACE).toInt()
            val games = gameParts[1].split(SET_SEPARATOR)
            val gameSets = mutableListOf<Day2GameSet>()
            for (game in games) {
                var redCubes = 0
                var greenCubes = 0
                var blueCubes = 0
                val sameColoredCubes = game.split(COMMA)
                for (sameColoredCube in sameColoredCubes) {
                    val sameColoredCubeParts = sameColoredCube.trim().split(SPACE)
                    val cubeCount = sameColoredCubeParts[0].toInt()
                    when(sameColoredCubeParts[1]) {
                        RED -> redCubes = cubeCount
                        BLUE -> blueCubes = cubeCount
                        GREEN -> greenCubes = cubeCount
                    }
                }
                gameSets.add(Day2GameSet(redCubes, greenCubes, blueCubes))
            }
            val game = Day2Game(id, gameSets)
            return game
        }
    }
}