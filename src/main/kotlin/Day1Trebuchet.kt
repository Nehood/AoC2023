class Day1Trebuchet {

    private val numberStrings = mapOf(
        "one" to '1',
        "two" to '2',
        "three" to '3',
        "four" to '4',
        "five" to '5',
        "six" to '6',
        "seven" to '7',
        "eight" to '8',
        "nine" to '9'
    )

    fun sumCalibrationValues(calibrationDocument: List<String>, withWords: Boolean = false): Int {
        return if (withWords) {
            calibrationDocument.sumOf { line ->
                val firstDigit =
                    if (line.contains("[0-9]".toRegex()) && line.indexOfFirst { it.isDigit() } < line.findFirstWord().first) line.first { it.isDigit() }
                    else numberStrings[line.findFirstWord().second]
                val lastDigit =
                    if (line.contains("[0-9]".toRegex()) && line.indexOfLast { it.isDigit() } > line.findLastWord().first) line.last { it.isDigit() }
                    else numberStrings[line.findLastWord().second]
                (firstDigit.toString() + lastDigit).toInt()
            }
        } else {
            calibrationDocument.sumOf { line ->
                (line.first { it.isDigit() }.toString() + line.last { it.isDigit() }).toInt()
            }
        }
    }

    private fun String.findFirstWord(): Pair<Int, String> {
        val wordNumbers = numberStrings.map {
            Pair(indexOf(it.key), it.key)
        }.filter { it.first != -1 }
        return if (wordNumbers.isNotEmpty()) {
            wordNumbers.minBy { it.first }
        } else {
            Pair(Int.MAX_VALUE, "")
        }
    }

    private fun String.findLastWord(): Pair<Int, String> {
        val wordNumbers = numberStrings.map {
            Pair(lastIndexOf(it.key), it.key)
        }.filter { it.first != -1 }
        return if (wordNumbers.isNotEmpty()) {
            wordNumbers.maxBy { it.first }
        } else {
            Pair(Int.MIN_VALUE, "")
        }
    }
}