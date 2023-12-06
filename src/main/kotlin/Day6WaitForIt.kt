class Day6WaitForIt {

    private val TIME = "Time:"
    private val DISTANCE = "Distance:"
    private val SPACE = " "

    fun calculateWaysToBeatRaces(races: List<String>): Long {
        val times = races[0].substringAfter(TIME).trim().split(SPACE).filter { it.isNotBlank() }.map { it.toLong() }
        val distances = races[1].substringAfter(DISTANCE).trim().split(SPACE).filter { it.isNotBlank() }.map { it.toLong() }

        var waysToBeatRecord = 1L
        for (index in 0..times.lastIndex) {
            val currentTime = times[index]
            val currentDistance = distances[index]
            var currentWaysToBeatRecord = 0L
            for (velocity in 1..currentTime) {
                if (velocity * (currentTime - velocity) > currentDistance) {
                    currentWaysToBeatRecord++
                }
            }
            waysToBeatRecord *= currentWaysToBeatRecord
        }
        return waysToBeatRecord
    }

    fun calculateWaysToBeatSingleRace(races: List<String>): Long {
        val time = races[0].substringAfter(TIME).trim().replace(SPACE, "").toLong()
        val distance = races[1].substringAfter(DISTANCE).trim().replace(SPACE, "").toLong()

        var waysToBeatRecord = 0L
        for (velocity in 1..time) {
            if (velocity * (time - velocity) > distance) {
                waysToBeatRecord++
            }
        }
        return waysToBeatRecord
    }
}