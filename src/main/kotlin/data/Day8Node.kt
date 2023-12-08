package data

data class Day8Node(val id: String, val destinations: String) {

    var left: Day8Node? = null
    var right: Day8Node? = null
    companion object{

        private val EQUALS = " = "
        private val LEFT_BRACKET = "("
        private val COMMA = ","
        private val RIGHT_BRACKET = ")"

        fun fromNodeDefinition(nodeDefinition: String): Day8Node {
            val parts = nodeDefinition.split(EQUALS)
            val id = parts[0]
            val destinations = parts[1]

            return Day8Node(id, destinations)
        }
    }

    fun assignDestinations(nodes: List<Day8Node>) {
        val leftId = this.destinations.split(COMMA)[0].substringAfter(LEFT_BRACKET)
        val rightId = this.destinations.split(COMMA)[1].substringBefore(RIGHT_BRACKET).trim()
        this.left = nodes.find { it.id == leftId }
        this.right = nodes.find { it.id == rightId }
    }
}